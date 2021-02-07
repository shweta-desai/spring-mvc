package com.job.management.entity;

public class JobParam {
	private String byMonth;
	private String byDay;
	private String byHour;

	public JobParam() {}
	
	public String getByMonth() {
		return byMonth;
	}

	public void setByMonth(String byMonth) {
		this.byMonth = byMonth;
	}

	public JobParam(String byMonth, String byDay, String byHour) {
		super();
		this.byMonth = byMonth;
		this.byDay = byDay;
		this.byHour = byHour;
	}

	public String getByDay() {
		return byDay;
	}

	public void setByDay(String byDay) {
		this.byDay = byDay;
	}

	public String getByHour() {
		return byHour;
	}

	public void setByHour(String byHour) {
		this.byHour = byHour;
	}

}
