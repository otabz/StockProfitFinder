package com.genoapay;

import java.util.Date;

public interface TimeUtils {

	public Date getCurrentTime();

	public boolean isMinuteElapsed(Date since);

	public static interface SystemDate {
		public long getCurrentTimeMillis();
	};

	public static class TimeUtilsJavaSystem implements TimeUtils {

		private SystemDate system;

		public TimeUtilsJavaSystem() {
			this.system = new SystemDate() {

				@Override
				public long getCurrentTimeMillis() {
					return System.currentTimeMillis();
				}
			};
		}

		public TimeUtilsJavaSystem(com.genoapay.TimeUtils.SystemDate systemDate) {
			this.system = systemDate;
		}

		@Override
		public Date getCurrentTime() {
			return new Date(this.system.getCurrentTimeMillis());
		}

		@Override
		public boolean isMinuteElapsed(Date t1) {
			Date t2 = new Date(this.system.getCurrentTimeMillis());
			long difference = ((t2.getTime() - t1.getTime()) / 1000) / 60;
			return difference > 0;
		}
	}
}
