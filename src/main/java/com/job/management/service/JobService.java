package com.job.management.service;

import java.util.List;

import com.job.management.entity.Job;
import com.job.management.entity.Job.JOB_STATUS;


public interface JobService {

	List<Job> getJobs();
	
	void addJob(Job job);
	
	void updateJob(Job job);
	
	void deleteJob(Job job);
	
	void updateJobStatus(Job job, JOB_STATUS jobStatus);

}
