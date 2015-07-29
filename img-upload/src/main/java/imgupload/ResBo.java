package imgupload;

/**
 * 
 * 封装Service层方法返回结果
 * 
 * */
public class ResBo<T> {

	private boolean success = true;
	private String errCode;
	private String errMsg;
	private T result;
	
	public ResBo(){
		super();
	}
	
	public ResBo(T result){
		this.setResult(result);
	}
	
	public ResBo(String errCode,String errMsg){
		this.success = false;
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
}
