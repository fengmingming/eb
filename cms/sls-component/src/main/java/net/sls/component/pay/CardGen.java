package net.sls.component.pay;

import java.util.Calendar;
import java.util.Random;

import net.sls.dto.pay.PrepaidCard;


public class CardGen {	
	
	private static final int CARD_NUM_LEN = 0x10;	// 15xxxxxxxxxxxxxxx{0~9}
	private static final int CARD_PSWD_LEN = 0x10;
	private static final int CARD_ID_PART_LEN = 0x08;
	
	private static final int MAX_SN = (int) Math.pow(0xa, CARD_ID_PART_LEN-1);
	
	private static final CardGen INSTANCE = new CardGen();
	
	protected CardGen(){		
	}
	
	public static CardGen getInstance(){		
		return INSTANCE;
	}
	
	public PrepaidCard nextPrepaidCard(Long id){
		PrepaidCard pc = new PrepaidCard();
		if (id==null) // when not data in db.
			id = 0L;
		Long idx = id + 1;
		//pc.setId(idx.intValue());
		pc.setCardNum(genCardNum(idx));
		pc.setPassword(genCardPswd(idx));
		pc.setCardStatus((byte)0);
		pc.setCheckStatus((byte)1);  // default check status is 1.
		return pc;
	}
	
	public String genCardNum(Long id){	
		String mask = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";	
		Calendar c = Calendar.getInstance();
		int y = c.get(Calendar.YEAR)%0x64;
		int m = c.get(Calendar.MONTH)+1;
		String mth = m>9?String.valueOf(m):"0"+m;		
		int rdmLen = CARD_NUM_LEN - 6 - id.toString().length();	// 15x07xxxxxxxxxxxxx[A~Z]{id}
		StringBuffer sb = new StringBuffer();	
		sb.append(y).append(System.currentTimeMillis()%0x0a).append(mth);
		
		for(int i = 0;i<rdmLen; i++){
			int idx = new Random().nextInt(0x24);	//(int) System.currentTimeMillis()%0x24;		
			String s = mask.substring(idx,idx+1);
			sb.append(s);
		}
		long idxL = System.currentTimeMillis()%0x10L + 10L;		
		int idx = (int)idxL;
		sb.append(mask.substring(idx,idx+1)).append(id);		
		/*
		int key =  (int)(len % 0x1A);
		int asc = 0x41 + key;
		char fmt = (char)asc;		
		String pattern = "%0"+rdmLen+"d";
		String fmtStr = String.format(pattern, id.intValue());
		fmtStr = new StringBuilder(fmtStr).reverse().toString();
		*/	
				
		return sb.toString();
	}

	public String genCardPswd(Long id){		
		String rdm = String.format("%04d", new Random().nextInt(10000));	
		String sid=genIDString(id);		
		String s= String.format("%04d",System.currentTimeMillis()%10000) + sid + rdm;
		try {
			s = encryptBASE64(s.getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	@SuppressWarnings("restriction")
	public static String encryptBASE64(byte[] key) throws Exception{
		 return (new sun.misc.BASE64Encoder()).encodeBuffer(key);  
	}
	
	@SuppressWarnings("restriction")
	public static  String decryptBASE64(String s ) {		
		if (s == null) return null;		
		try {
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			return null;
		}			
		
	}
	
	public String genIDString(Long id){
		return genIDStr(CARD_ID_PART_LEN,id);
	}
	
	// gen the ID String of a object with specific length.
	private String genIDStr(int len,Long id){		
		if(id>MAX_SN)
			id = id % MAX_SN;
		return String.format( "%0"+len+"d", id);
	}
	
	/**
	public static void main(String[] args){
		for(Long i = 0L;i<0xfff;i++){
			System.out.print(CardGen.getInstance().nextPrepaidCard(i).toString());
		}
	}
	**/
}
