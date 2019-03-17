package com.genoapay;

import static org.junit.Assert.*;
import java.util.Calendar;
import java.util.Date;
import org.junit.Test;

public class TimeUtilsTest {

	@Test
	public void test1MinuteNotElapsed() {
		TimeUtils utils = new TimeUtils.TimeUtilsJavaSystem();
		Date since = new Date(System.currentTimeMillis());
		assertFalse("1 minute is not passed yet!", utils.isMinuteElapsed(since));
	}

	@Test
	public void test1MinuteIsElapsed() {
		final long t = System.currentTimeMillis();
		TimeUtils utils = new TimeUtils.TimeUtilsJavaSystem(
				new com.genoapay.TimeUtils.SystemDate() {

					@Override
					public long getCurrentTimeMillis() {
						Calendar cal = Calendar.getInstance();
						cal.add(Calendar.MINUTE, 1);
						return cal.getTime().getTime();
					}
				});
		Date since = new Date(t);

		assertTrue("1 minute must pass!", utils.isMinuteElapsed(since));
	}

}
