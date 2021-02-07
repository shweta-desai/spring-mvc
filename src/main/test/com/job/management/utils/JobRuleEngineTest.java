package com.job.management.utils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.job.management.entity.Job;
import com.job.management.entity.JobParam;

class JobRuleEngineTest {

	private Job job;

	@BeforeEach
	public void init() {
		job = new Job("Test Job", "Testing the job rules", "No action", "Test Job", "ONCE", 1, 4);
		job.setJobParams(new JobParam());
	}

	@Test
	@DisplayName("When Job status is active, it should return true")
	void testIsJobValid_forJobActiveStatus() {
		job.setActiveFlg(1);
		Assertions.assertTrue(Arrays.asList(job).stream().anyMatch(JobRuleEngine::isJobValid));
	}

	@Test
	@DisplayName("When Job status is inactive, it should return false")
	void testIsJobValid_forJobInactiveStatus() {
		job.setActiveFlg(0);
		Assertions.assertFalse(Arrays.asList(job).stream().anyMatch(JobRuleEngine::isJobValid));
	}

	@Test
	@DisplayName("When Job Paramter's Month is valid , it should return true")
	void testIsJobValid_forValidMonth() {
		job.setActiveFlg(1);
		job.setJobParams(new JobParam(
				Calendar.getInstance().getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault()), "", ""));
		Assertions.assertTrue(Arrays.asList(job).stream().anyMatch(JobRuleEngine::isJobValid));
	}

	@Test
	@DisplayName("When Job Paramter's Month is Invalid , it should return false")
	void testIsJobValid_forInvalidMonth() {
		job.setActiveFlg(1);
		job.setJobParams(new JobParam("January", "", ""));
		Assertions.assertFalse(Arrays.asList(job).stream().anyMatch(JobRuleEngine::isJobValid));
	}

	@Test
	@DisplayName("When Job Paramter's Day is valid , it should return true")
	void testIsJobValid_forValidDay() {
		job.setActiveFlg(1);
		job.setJobParams(new JobParam("",
				Calendar.getInstance().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()), ""));
		Assertions.assertTrue(Arrays.asList(job).stream().anyMatch(JobRuleEngine::isJobValid));
	}

	@Test
	@DisplayName("When Job Paramter's Day is invalid , it should return false")
	void testIsJobValid_forInValidDay() {
		job.setActiveFlg(1);
		job.setJobParams(new JobParam("", "Monday", ""));
		Assertions.assertFalse(Arrays.asList(job).stream().anyMatch(JobRuleEngine::isJobValid));
	}

	@Test
	@DisplayName("When Job Paramter's Hour is valid , it should return true")
	void testIsJobValid_forValidHour() {
		job.setActiveFlg(1);
		job.setJobParams(new JobParam("", "", String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))));
		Assertions.assertTrue(Arrays.asList(job).stream().anyMatch(JobRuleEngine::isJobValid));
	}

	@Test
	@DisplayName("When Job Paramter's Hour is invalid , it should return false")
	void testIsJobValid_forInValidHour() {
		job.setActiveFlg(1);
		job.setJobParams(new JobParam("", "", "30"));
		Assertions.assertFalse(Arrays.asList(job).stream().anyMatch(JobRuleEngine::isJobValid));
	}

}
