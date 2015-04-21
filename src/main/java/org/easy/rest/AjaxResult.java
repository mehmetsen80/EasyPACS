package org.easy.rest;

public class AjaxResult {

	private Boolean success;
	private String message;
	
	public AjaxResult(Boolean success, String message){
		this.success = success;
		this.message = message;		
	}
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
