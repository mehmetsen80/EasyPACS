package org.easy.rest;

import org.easy.entity.Series;

public class AjaxSeries {

	private Boolean success;
	private Series series;
	
	public AjaxSeries(Boolean success, Series series){
		this.success = success;
		this.series = series;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Series getSeries() {
		return series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}
}
