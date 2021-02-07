package com.job.management.launcher;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.job.management.entity.Job;
import com.job.management.entity.Job.JOB_STATUS;
import com.job.management.entity.Job.SCHEDULE_TYPE;
import com.job.management.service.JobService;
import com.job.management.service.impl.JobServiceImpl;

public class JobLauncher implements Runnable {
	
	public JobLauncher() {
		this.jobService = new JobServiceImpl();
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(JobLauncher.class); 
	
	private JobService jobService;

	private Job job;

	public JobLauncher(Job job) {
		this.job = job;
		this.jobService = new JobServiceImpl();
	}

	@Override
	public void run() {

		try {
			jobService.updateJobStatus(job, JOB_STATUS.RUNNING);
			Class<?> cls = Class.forName(job.getJobAction());
			Method mainMethod = cls.getMethod("main", String[].class);
			String[] params = job.getJobArguments().split(",");
			mainMethod.invoke(null, (Object) params);
			jobService.updateJobStatus(job, JOB_STATUS.SUCCESS);
			LOGGER.info("Job completed Successfully ");
		} catch (Exception ex) {
			jobService.updateJobStatus(job, JOB_STATUS.FAILED);
			LOGGER.error("Exception when job is FAILED ", ex);
			
		} finally {
			if (SCHEDULE_TYPE.ONCE.toString().equals(job.getScheduleType())) {
				jobService.deleteJob(job);
				LOGGER.info("DELETING THE JOB ");
			}
		}

	}

}
