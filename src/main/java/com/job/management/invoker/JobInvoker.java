package com.job.management.invoker;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.job.management.process.JobProcessor;

public class JobInvoker {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobInvoker.class);
	private static final long REPEAT_INTERVAL_MIN = 5;

	public static void main(String[] args) {
		try {
			LOGGER.info("Job Invoker Launched");
			JobProcessor jobProcessor = new JobProcessor();

			Timer timer = new Timer();
			timer.schedule(new TimerTask() {

				@Override
				public void run() {
					try {
						jobProcessor.processJobs();
					} catch (Exception ex) {
						LOGGER.error("ERROR:", ex);
					}

				}
			}, 0, REPEAT_INTERVAL_MIN * 60 * 1000);

			LOGGER.info("Job Invoker Completed Execution..!");
		} catch (Exception ex) {
			LOGGER.error("Exception ", ex);
		}
	}
}
