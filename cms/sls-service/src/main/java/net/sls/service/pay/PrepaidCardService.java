package net.sls.service.pay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.framework.BASE64Util;
import net.sls.component.pay.CardGen;
import net.sls.component.pay.IPrepaidCardComponent;
import net.sls.dto.pay.PrepaidCard;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

@Service
public class PrepaidCardService implements IPrepaidCardService {

	@Autowired
	private IPrepaidCardComponent comp ;	
	
	/**
	 * 
	 * 1. query the max id of the Prepaid Card.
	 * 2. generate the card number and password pair, all are distinct.
	 * 3. set calculate.
	 * 4. gen the next List<PrepaidCard>
	 * 5. insert the prepaid cards and return the inserted collection.
	 * 
	 * Note: For new generate prepaid card, it's 
	 * cardStatus is null which means nothing about the card,
	 * and the check status is 1 means a new one. 
	 */
	@Override
	public ResBo<List<PrepaidCard>> insertPrepaidCard(ReqBo rb) {
		PrepaidCard pc = (PrepaidCard) rb.getParam("prepaidcard");
		// the count of the batch
		int cnt = rb.getParamInt("count");		
		Long maxId = comp.getMaxId();
		if (maxId == null)
			maxId = 0L;
		List<PrepaidCard> batch = new ArrayList<PrepaidCard>();
		for(int i = 0 ; i<cnt;i++ ){			
			PrepaidCard pCard = CardGen.getInstance().nextPrepaidCard(maxId);			
			pCard.setBatch(pc.getBatch());	
			// Note reset the card Number with batch			
			pCard.setCardNum(pc.getBatch()+pCard.getCardNum().substring(pc.getBatch().length()));
			pCard.setParValue(pc.getParValue());
			pCard.setValidityStart(pc.getValidityStart());
			pCard.setValidityEnd(pc.getValidityEnd());			
			pCard.setRemark(pc.getRemark());			
			batch.add(pCard);
			maxId ++;
		}	
		
		// Insert to db now.
		int eff = comp.insertPrepaidCard(batch);
		if(eff != cnt){
			throw new RuntimeException("Generate prepaid card failed."
					+ "\r\nPlease retry again.");
		}		
		ResBo<List<PrepaidCard>> rbRtn = new ResBo<List<PrepaidCard>>(batch);
		return rbRtn;
	}

	/**
	 * Three params wrapped by ReqBo
	 * 1 : a PrepaidCard object which with some property. 
	 * 2 : page index	--< "pageIdx"		int
	 * 3 : page size	--< "pageSize"		int  
	 * 
	 * 
	 */
	@Override
	public ResBo<Pager<List<PrepaidCard>>> selectPrepaidCard(ReqBo rb) {
		
		return new ResBo<Pager<List<PrepaidCard>>>(
				comp.selectPrepaidCardList(
						(PrepaidCard)rb.getReqModel(),
						rb.getParamInt("IdStart"),
						rb.getParamInt("IdEnd"),
						rb.getParamInt("pageIdx"),
						rb.getParamInt("pageSize"))
				);	
	}
	
	
	@Override
	public ResBo<Pager<List<PrepaidCard>>> selectBatch(ReqBo rb) {
		
		return new ResBo<Pager<List<PrepaidCard>>>(
				comp.selectBatchList(
						(PrepaidCard)rb.getReqModel(),
						rb.getParamInt("pageIdx"),
						rb.getParamInt("pageSize"))
				);	
	}	

	/**
	 * return the count effected objects.
	 */
	@Override
	public ResBo<?> updatePrepaidCardCheckStatus(ReqBo rb) {
		String bth = rb.getParamStr("batch");		
		Long[] ids = (Long[]) rb.getParam("ids");
		byte status = rb.getParamInt("chkStatus").byteValue();		
		Integer changed = comp.updatePrepaidCardCheckStatus(bth, ids, status);		
		return new ResBo<Integer>(changed);
	}

	/**
	 * return how many objects is changed by the command.
	 */
	@Override
	public ResBo<?> updatePrepaidCardStatus(ReqBo rb) {
		// TODO Auto-generated method stub
		long id = rb.getParamLong("id");
		byte status = rb.getParamInt("status").byteValue();
		Integer changed = comp.updatePrepaidCardStatus(id, status);
		return new ResBo<Integer>(changed);
	}
	
	/**
	 * 
	 */
	public ResBo<?> exportExcel(ReqBo rb){			
		String batch = rb.getParamStr("batch");
		Integer idStart = rb.getParamInt("IdStart");
		Integer idEnd = rb.getParamInt("IdEnd");
			
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet();
		int i = 0;
		Row row = sheet.createRow(i);
		row.createCell(0, Cell.CELL_TYPE_STRING).setCellValue("批次");
		row.createCell(1, Cell.CELL_TYPE_STRING).setCellValue("卡号");
		row.createCell(2, Cell.CELL_TYPE_STRING).setCellValue("密码");
		row.createCell(3, Cell.CELL_TYPE_STRING).setCellValue("面值");
		row.createCell(4, Cell.CELL_TYPE_STRING).setCellValue("有效期开始");
		row.createCell(5, Cell.CELL_TYPE_STRING).setCellValue("有效期结束");
		row.createCell(6, Cell.CELL_TYPE_STRING).setCellValue("备注");
		
		CreationHelper createHelper = wb.getCreationHelper();  
	    CellStyle dateStyle = wb.createCellStyle();  
	    dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd")); 
		
		
		List<PrepaidCard> lst = null;
		if(batch != null && batch!=""){ // export by batch
			PrepaidCard card = new PrepaidCard();
			card.setBatch(batch);			
			lst = comp.selectPrepaidCardList(card,null,null, 1, Integer.MAX_VALUE).getEntry();
			
		}
		else if(idStart!=null && idEnd!=null) // export data from idStart to idEnd by id.
		{
			lst = comp.selectPrepaidCardList(new PrepaidCard(),idStart,idEnd, 1, Integer.MAX_VALUE).getEntry();
		}		
		
		for(PrepaidCard pc : lst){
			i = i + 1;
			row = sheet.createRow(i);
			row.createCell(0, Cell.CELL_TYPE_STRING).setCellValue(pc.getBatch());
			row.createCell(1, Cell.CELL_TYPE_STRING).setCellValue(pc.getCardNum());
			row.createCell(2, Cell.CELL_TYPE_STRING).setCellValue(BASE64Util.decryptBASE64(pc.getPassword()));
			row.createCell(3, Cell.CELL_TYPE_NUMERIC).setCellValue(pc.getParValue());
			Cell c4 = row.createCell(4);
			c4.setCellStyle(dateStyle);
			c4.setCellValue(pc.getValidityStart());
			Cell c5 = row.createCell(5);
			c5.setCellStyle(dateStyle);
			c5.setCellValue(pc.getValidityEnd());
			row.createCell(6, Cell.CELL_TYPE_STRING).setCellValue(pc.getRemark());			
		}		
		
		return new ResBo<Workbook>(wb);			
	}

	@Override
	public ResBo<?> getNextBatchNum() {		//yyMMddX
		SimpleDateFormat fmt = new SimpleDateFormat("yyMMdd");
		String dt = fmt.format(new Date());
		String s = comp.selectBatchMaxNum(dt);
		String rtn = null;
		if (s!=null){
			String mask="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			int idx = mask.indexOf(s);
			idx ++;
			if(idx>0x23)
				idx = 0;
			char c = mask.charAt(idx);
			rtn = dt+c;
		}
		else
			rtn = dt+"0";
		ResBo<String> rb = new ResBo<String>();
		rb.setResult(rtn);
		return rb;
	}

	@Override
	public ResBo<?> statistics() {
		return new ResBo<Object>(comp.selectPrepaidCardStatistics());
	}

	@Override
	public ResBo<?> rechargedInfo(ReqBo reqBo) {
		return new ResBo<Object>(comp.selectPrepaidCardRechargedInfo(reqBo.getParamDate("startDate",null,"yyyy-MM-dd"), reqBo.getParamDate("endDate",null,"yyyy-MM-dd"), reqBo.getParamInt("page"), reqBo.getParamInt("rows")));
	}
}

