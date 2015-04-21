package org.easy.rest;

import org.easy.entity.Patient;

public class AjaxPatient {

	private Boolean success;
	private Patient patient;
	
	public AjaxPatient(Boolean success, Patient patient){
		this.success = success;
		this.patient = patient;
	}
	
	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}
