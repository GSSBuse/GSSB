package com.thinkgem.jeesite.modules.sys.utils;

public abstract class NewsUpdateFlagUtil {
	
	private static long updateTimestamp;
	
	public static Object updateLock = new Object();

	public static long getUpdateTimestamp() {
		return updateTimestamp;
	}

	public static void setUpdateTimestamp() {
		updateTimestamp = System.currentTimeMillis();
		notifyLock();
	}
	
	public static void notifyLock() {
		synchronized (updateLock) {
	        updateLock.notifyAll();
        }
	}
	
	public static boolean isNeedUpdate(String timestamp) {
		try {
			if (Long.parseLong(timestamp) == updateTimestamp) {
				return false;
			}
		} catch (Exception e) {
			// DO NOTHING
		}
		return true;
	}
}
