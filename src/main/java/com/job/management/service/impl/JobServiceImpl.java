package com.job.management.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.job.management.dao.JobDao;
import com.job.management.dao.impl.JobDaoImpl;
import com.job.management.entity.Job;
import com.job.management.entity.Job.JOB_STATUS;
import com.job.management.service.JobService;

@Service
public class JobServiceImpl implements JobService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobDaoImpl.class);
	
	public JobServiceImpl() {
		this.jobDao = new JobDaoImpl();
	}

	private JobDao jobDao;

	public void setJobDao(JobDao jobDao) {
		this.jobDao = jobDao;
	}

	@Override
	public List<Job> getJobs() {
		return jobDao.getJobs();
	}

	@Override
	public void addJob(Job job) {
		jobDao.addJob(job);
		this.printCurrentJobStatus();
	}

	@Override
	public void updateJob(Job job) {
		jobDao.updateJob(job);
		this.printCurrentJobStatus();
	}

	@Override
	public void deleteJob(Job job) {
		jobDao.deleteJob(job);
		this.printCurrentJobStatus();
	}
	
	@Override
	public void updateJobStatus(Job job, JOB_STATUS jobStatus) {
		job.setState(jobStatus.toString());
		jobDao.updateJob(job);
		this.printCurrentJobStatus();
	}
	
	private void printCurrentJobStatus() {
		LOGGER.info("**************************************************************************");
		LOGGER.info(String.format("| %30s | %30s |", "Job", "Status"));
		LOGGER.info("**************************************************************************");
		List<Job> jobs = this.getJobs();
		if ( jobs != null && jobs.size() > 0 ) {
			jobs.stream().forEach(job -> job.prettyPrint());
		}
		LOGGER.info("**************************************************************************");
	}

}
