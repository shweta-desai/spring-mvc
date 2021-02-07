package com.job.management.dao.impl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.job.management.dao.JobDao;
import com.job.management.entity.Job;

@Repository
public class JobDaoImpl implements JobDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JobDaoImpl.class);

	private static Map<String, Job> inMemoryJobRepo = new HashMap<>();

	static {
		try {
			LOGGER.info("Job initial loading started");
			Gson gsonParser = new Gson();
			String content = new String(Files.readAllBytes(Paths.get("resources/jobDetails.json")));
			List<Job> jobs = gsonParser.fromJson(content, new TypeToken<ArrayList<Job>>() {
			}.getType());
			jobs.forEach(job -> inMemoryJobRepo.put(job.getJobName(), job));
			LOGGER.info("Job initial loading completed");
		} catch (Exception ex) {
			LOGGER.error("ERROR:" , ex);
		}

	}

	@Override
	public List<Job> getJobs() {
		return new ArrayList<>(inMemoryJobRepo.values());
	}

	@Override
	public void addJob(Job job) {
		inMemoryJobRepo.put(job.getJobName(), job);

	}

	@Override
	public void updateJob(Job job) {
		inMemoryJobRepo.put(job.getJobName(), job);
	}

	@Override
	public void deleteJob(Job job) {
		inMemoryJobRepo.remove(job.getJobName());
	}

}
