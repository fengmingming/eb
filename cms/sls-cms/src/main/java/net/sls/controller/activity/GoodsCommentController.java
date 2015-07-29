package net.sls.controller.activity;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sls.dto.ext.product.GoodsCommentDto;
import net.sls.service.activity.IGoodsCommentService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import util.framework.FindServiceUtil;
import framework.web.Pager;
import framework.web.ReqBo;
import framework.web.ResBo;

/**
 * 
 * 商品评论
 * 
 * */
@Controller
@RequestMapping("goodsComment")
public class GoodsCommentController {
	
	@RequestMapping("selectComments.htm")
	public String selectComments(){
		return "goodsComment/selectComments";
	}
	
	@RequestMapping("queryComments.htm")
	@ResponseBody
	public ResBo<Pager<List<GoodsCommentDto>>> query(@RequestParam("page") int page,@RequestParam("rows") int rows,HttpServletRequest request){
		ReqBo reqBo = new ReqBo(request);
		reqBo.setParam("page", page);
		reqBo.setParam("rows", rows);
		IGoodsCommentService goodsCommentService = FindServiceUtil.findService(IGoodsCommentService.class);
		return goodsCommentService.selectGoodsComments(reqBo);
	}
	
	/**
	 * 批量审核
	 * @param ids
	 * @return
	 */
	@RequestMapping("batchCheck.htm")
	@ResponseBody
	public ResBo<?> batchCheck(@RequestParam("ids") String ids,@RequestParam("isChecked") String isChecked){
		//转化ids
		Long[] IDs = converIds(ids);
		ReqBo rb = new ReqBo();		
		rb.setParam("ids", IDs);
		rb.setParam("isChecked", isChecked);
		IGoodsCommentService igc = FindServiceUtil.findService(IGoodsCommentService.class);
		return igc.updateBatchStatus(rb);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("batchDelete.htm")
	@ResponseBody
	public ResBo<?> batchDelete(@RequestParam("ids") String ids,@RequestParam("isChecked") String isChecked){
		//转化ids
		Long[] IDs = converIds(ids);
		ReqBo rb = new ReqBo();		
		rb.setParam("ids", IDs);
		rb.setParam("isChecked", isChecked);
		IGoodsCommentService igc = FindServiceUtil.findService(IGoodsCommentService.class);
		return igc.deleteBatchStatus(rb);
	}
	
	/**
	 * 回复
	 * @param ids
	 * @return
	 */
	@RequestMapping("updateReply.htm")
	@ResponseBody
	public ResBo<?> updateReply(HttpServletRequest request){
		ReqBo rb = new ReqBo(request);		
		IGoodsCommentService igc = FindServiceUtil.findService(IGoodsCommentService.class);
		return igc.updateReply(rb);
	}

	private Long[] converIds(String ids) {
		String[] ss = ids.split(",");
		int len = ss.length;
		Long[] IDs = new Long [len];
		for(int i = 0;i<len;i++){
			IDs[i] = Long.parseLong(ss[i]);
		}
		return IDs;
	}
}
