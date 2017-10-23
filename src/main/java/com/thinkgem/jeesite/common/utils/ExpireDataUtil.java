package com.thinkgem.jeesite.common.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.log4j.Logger;

import com.thinkgem.jeesite.common.utils.exception.ProduceException;


public class ExpireDataUtil {
	private static Logger LOGGER = Logger.getLogger(ExpireDataUtil.class);

	private static Timer timer;
	private static ReadWriteLock locks;
	static {
		// 启动过期销毁程序
		timer = new Timer("ExpireDataSwaper", true);
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				if (locks == null || cache == null) {
					return;
				}
				try {
					// LOGGER.debug("try lock");
					if (locks.writeLock().tryLock(10, TimeUnit.SECONDS)) {
						try {
							// LOGGER.debug("do swapping.");
							Iterator<Object> it = cache.keySet().iterator();
							while (it.hasNext()) {
								Object key = it.next();
								if (cache.get(key).expireTime < System.currentTimeMillis()) {
									LOGGER.debug("remove key:" + key);
									it.remove();
								}
							}
						} finally {
							// LOGGER.debug("unlock");
							// LOGGER.debug("now caching:" + cache.size());
							locks.writeLock().unlock();
						}
					} else {
						LOGGER.debug("can't get a lock for swapping");
						// if can't get a lock in a time
						// do nothing
					}
				} catch (InterruptedException e) {
					LOGGER.error("过期销毁程序发生异常", e);
				} catch (Exception e) {
					LOGGER.error("过期销毁程序发生异常", e);
				}
			}
			// 5分钟后执行， 每分钟检查一次
		}, 5 * 50 * 1000, 60 * 1000);

		locks = new ReentrantReadWriteLock();
	}

	public static class EData {
		Object key;
		Object data;
		/** 下次过期时间 */
		long expireTime;
		/** 过期间隔（单位：毫秒） */
		long expiredPeriod;
	}

	private static Map<Object, EData> cache = new HashMap<Object, EData>();

	/**
	 * 缓存对象一定时间
	 * 
	 * @param key
	 *            缓存Key
	 * @param data
	 *            缓存对象
	 * @param expirePeriodMills
	 *            过期间隔（单位：毫秒）
	 * @return EData对象
	 */
	public static EData put(Object key, Object data, long expirePeriodMills) {
		try {
			locks.writeLock().lock();
			LOGGER.debug("save data: " + key + " , " + data + " , " + expirePeriodMills);
			EData e = new EData();
			e.key = key;
			e.data = data;
			e.expireTime = System.currentTimeMillis() + expirePeriodMills;
			e.expiredPeriod = expirePeriodMills;
			return cache.put(key, e);
		} finally {
			locks.writeLock().unlock();
		}
	}

	/**
	 * 获取缓存的对象,并 不 刷新过期时间
	 * 
	 * @param key
	 *            缓存Key
	 * @return 缓存对象。如果当前时间超过了过期时间，也会返回null;
	 */
	public static <T> T get(Object key) {
		return get(key, false);
	}

	/**
	 * 获取缓存的对象
	 * 
	 * @param key
	 *            缓存Key
	 * @param refresh
	 *            是否刷新过期时间
	 * @return 缓存对象。如果当前时间超过了过期时间，也会返回null;
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(Object key, boolean refresh) {
		try {
			locks.readLock().lock();
			LOGGER.debug("get data: " + key);
			if (!cache.containsKey(key)) {
				LOGGER.debug("data dose not exist: " + key);
				return null;
			}

			EData e = cache.get(key);
			if (e.expireTime < System.currentTimeMillis()) {
				LOGGER.debug("data expired: " + key);
				cache.remove(key);
				return null;
			} else if (refresh) {
				e.expireTime = System.currentTimeMillis() + e.expiredPeriod;
			}

			return (T) e.data;
		} finally {
			locks.readLock().unlock();
		}
	}

	/**
	 * 获取缓存的对象
	 * 
	 * @param key
	 *            缓存Key
	 * @return 缓存对象。如果当前时间超过了过期时间，会根据生成器 产生新的对象
	 */
	public static <T> T get(Object key, ExpireCacheDataProducer<T> producer) throws ProduceException {
		T t = get(key);
		if (t == null) {
			LOGGER.debug("produce data: " + key + " , " + producer.getExpirePeriod());
			LOGGER.debug("producer : " + producer.getClass().getName());
			int retry = 3;
			while (retry-- != 0) {
				try {
					t = producer.produce(key);
				} catch (ProduceException e) {
					if (retry == 0) {
						throw e;
					}
					LOGGER.error("生成缓存对象出错，进行重试:" + retry, e);
					continue;
				}
				break;
			}

			put(key, t, producer.getExpirePeriod());
		}
		return t;
	}

	public static <T> T remove(Object key) {
		T t = get(key);
		if (t != null) {
			LOGGER.debug("remove data: " + key);
			cache.remove(key);
		}
		return t;
	}

	public static <T> void main(String[] args) throws ProduceException {
		String res = get("test", new ExpireCacheDataProducer<String>() {

			@Override
			public String produce(Object key) throws ProduceException {
				return key + "_data";
			}

			@Override
			public long getExpirePeriod() {
				return 2000;
			}
		});
		System.out.println(System.currentTimeMillis());
		System.out.println(res);

		res = get("test");
		System.out.println(System.currentTimeMillis());
		System.out.println(res);

		try {
			Thread.sleep(5001);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		res = get("test");
		System.out.println(System.currentTimeMillis());
		System.out.println(res);

		res = get("test");
		System.out.println(System.currentTimeMillis());
		System.out.println(res);
	}
}
