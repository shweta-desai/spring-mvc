package com.job.management.utils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.job.management.entity.Job;
import com.job.management.entity.JobParam;

public class JobRuleEngine {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JobRuleEngine.class);

	private static final GregorianCalendar CURR_CALENDAR = new GregorianCalendar();

	/**
	 * Check whether Job status is active and when End Date is not empty, Current
	 * Date should be before end Date
	 * 
	 */
	private static Function<Job, Boolean> fnCheckStatus = (Job job) -> {
		return job.getActiveFlg() == 1;
	};

	/**
	 * Check whether When Job Param byHour is not empty and current hour should
	 * exists in given byHour lists
	 * 
	 */
	private static Function<Job, Boolean> fnCheckHour = (Job job) -> {
		boolean isValid = true;
		JobParam jobParam = job.getJobParams();
		if (StringUtils.isNotEmpty(jobParam.getByHour())
				&& !Arrays.asList(jobParam.getByHour().split(",")).contains(String.valueOf(CURR_CALENDAR.get(Calendar.HOUR_OF_DAY)))) {
			isValid = false;
			LOGGER.debug("Hour Validation Failed..!");
		}
		return isValid;
	};

	/**
	 * Check whether when Job param byDay is not empty and Current Day should exists
	 * in byDay lists
	 * 
	 */
	private static Function<Job, Boolean> fnCheckDay = (Job job) -> {
		boolean isValid = true;
		JobParam jobParam = job.getJobParams();
		if (StringUtils.isNotEmpty(jobParam.getByDay())
				&& !Arrays.asList(jobParam.getByDay().toUpperCase().split(",")).contains(CURR_CALENDAR
						.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()).toUpperCase())) {
			isValid = false;
			LOGGER.debug("Day Validation Failed..!");
		}

		return isValid;
	};

	/**
	 * Checks whether when Job param byMonth field is not empty and current month
	 * should exists in byMonth lists
	 * 
	 */

	private static Function<Job, Boolean> fnCheckMonth = (Job job) -> {
		boolean isValid = true;
		JobParam jobParam = job.getJobParams();
		if (StringUtils.isNotEmpty(jobParam.getByMonth())
				&& !Arrays.asList(jobParam.getByMonth().toUpperCase().split(",")).contains(CURR_CALENDAR
						.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault()).toUpperCase())) {
			isValid = false;
			LOGGER.debug("Month Validation Failed..!");
		}
		return isValid;
	};

	public static boolean isJobValid(Job job) {
		CURR_CALENDAR.setTime(new Date());
		return fnCheckStatus.apply(job) && fnCheckMonth.apply(job) && fnCheckDay.apply(job) && fnCheckHour.apply(job);
	}

}
