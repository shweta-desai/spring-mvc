package com.job.management.job;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.job.management.entity.Job;
import com.job.management.invoker.JobInvoker;

public class JobAction {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobInvoker.class);

	public static void main(String[] args) {
		String csvFile = "resources/jobDetails.json";
		try {
			LOGGER.info("Job Action execution started : job[{}]", args.length > 0 ? args[0] : null);
			Gson gsonParser = new Gson();

			String content = new String(Files.readAllBytes(Paths.get(csvFile)));
			LOGGER.debug("*****************************************************************************");
			LOGGER.debug(content);
			LOGGER.debug("*****************************************************************************");
			List<Job> jobs = gsonParser.fromJson(content, new TypeToken<ArrayList<Job>>() {
			}.getType());
			LOGGER.info("Number of jobs loaded into memory: {} ", jobs.size());

			LOGGER.info("Job Action execution completed : job[{}]", args.length > 0 ? args[0] : null);
		} catch (IOException ex) {
			LOGGER.error("ERROR:", ex);
		}

	}

}
