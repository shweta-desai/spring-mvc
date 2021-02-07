package com.job.management.process;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.job.management.entity.Job;
import com.job.management.entity.Job.JOB_STATUS;
import com.job.management.launcher.JobLauncher;
import com.job.management.service.JobService;
import com.job.management.service.impl.JobServiceImpl;
import com.job.management.utils.JobRuleEngine;

public class JobProcessor {

	public JobProcessor() {
		this.jobService = new JobServiceImpl();
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(JobProcessor.class);

	private static final int MAX_THREADS = 5;

	private JobService jobService;

	public void processJobs() throws Exception {
		LOGGER.debug("Job Processort Started Execution");
		List<Job> jobs = jobService.getJobs();
		LOGGER.debug("No of Jobs Identified: {}", jobs.size());
		List<Job> validJobs = jobs.stream().filter(JobRuleEngine::isJobValid).collect(Collectors.toList());
		LOGGER.debug("No of Valid Jobs : {}", validJobs.size());
		if (validJobs != null && validJobs.size() > 0) {
			jobProcessor(validJobs);
		}
		LOGGER.debug("Job Processort Completed Execution");
	}

	private void jobProcessor(List<Job> jobs) throws Exception {
		ExecutorService executerService = Executors.newFixedThreadPool(MAX_THREADS);
		try {
			jobs.stream().sorted(Comparator.comparing(Job::getJobPriority)).forEach(job -> {
				executerService.execute(new JobLauncher(job));
				jobService.updateJobStatus(job, JOB_STATUS.QUEUED);
				
			});
		} catch (Exception ex) {
			LOGGER.info("Exception while processing the Job ", ex);
		} finally {
			executerService.shutdown();
		}
	}

}
