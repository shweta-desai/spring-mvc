package com.job.management.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.job.management.dao.impl.JobDaoImpl;

public class Job {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobDaoImpl.class);
	
	public static enum SCHEDULE_TYPE {
		ONCE, REPEATED
	}

	public enum JOB_STATUS {
		QUEUED, RUNNING, SUCCESS, FAILED, NOT_RUNNING
	}
	
	public Job() {}

	public Job(String jobName, String jobTitle, String jobAction, String jobArguments, String scheduleType,
			int activeFlg, int jobPriority) {
		super();
		this.jobName = jobName;
		this.jobTitle = jobTitle;
		this.jobAction = jobAction;
		this.jobArguments = jobArguments;
		this.scheduleType = scheduleType;
		this.activeFlg = activeFlg;
		this.jobPriority = jobPriority;
	}

	private String jobName;
	private String jobTitle;
	private String jobAction;
	private String jobArguments;
	private String scheduleType;
	private JobParam jobParams;
	private int activeFlg;
	private String state = "NOT RUNNING";
	private int jobPriority;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobAction() {
		return jobAction;
	}

	public void setJobAction(String jobAction) {
		this.jobAction = jobAction;
	}

	public String getJobArguments() {
		return jobArguments;
	}

	public void setJobArguments(String jobArguments) {
		this.jobArguments = jobArguments;
	}

	public String getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

	public JobParam getJobParams() {
		return jobParams;
	}

	public void setJobParams(JobParam jobParams) {
		this.jobParams = jobParams;
	}

	public int getActiveFlg() {
		return activeFlg;
	}

	public void setActiveFlg(int activeFlg) {
		this.activeFlg = activeFlg;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getJobPriority() {
		return jobPriority;
	}

	public void setJobPriority(int jobPriority) {
		this.jobPriority = jobPriority;
	}
	
	public void prettyPrint() {
		LOGGER.info(String.format("| %30s | %30s |", this.jobName, this.state));
	}

}
