package cn.hrg.cd.util;

/**
 * 用于封装ajx响应内容的对象
 * @author Administrator
 *
 */
public class AjaxResult {
	
	private String msg;
	
	private boolean success = true;
	
	private int errorCode=-99;
	
	public int getErrorCode() {
		return errorCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public AjaxResult(String msg, boolean success) {
		super();
		this.msg = msg;
		this.success = success;
	}

	public AjaxResult(String msg) {
		super();
		this.msg = msg;
//		this.success = true;
	}

	public AjaxResult(String msg, int errorCode) {
		super();
		this.msg = msg;
		this.errorCode = errorCode;
		this.success = false;
	}

	public AjaxResult() {
		super();
	}
	
	
}
