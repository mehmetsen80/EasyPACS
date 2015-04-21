package org.easy.rest;

import org.easy.entity.Study;

public class AjaxStudy {

	private Boolean success;
	private Study study;
	
	public AjaxStudy(Boolean success, Study study){
		this.success = success;
		this.study = study;
	}
	
	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Study getStudy() {
		return study;
	}

	public void setStudy(Study study) {
		this.study = study;
	}	
}
