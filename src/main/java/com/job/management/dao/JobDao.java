package com.job.management.dao;

import java.util.List;

import com.job.management.entity.Job;

public interface JobDao {

	List<Job> getJobs();
	
	void addJob(Job job);
	
	void updateJob(Job job);
	
	void deleteJob(Job job);

}
