package com.thinkgem.jeesite.common.utils;

import com.thinkgem.jeesite.common.utils.exception.ProduceException;


/**
 * 缓存对象产生器
 * The Class ExpireCacheDataProducer.<br>
 * 
 * @author "FNST)WeiCL"
 * @version 0.1
 */
public interface ExpireCacheDataProducer<T> {

	/**
	 * 产生对象
	 * 
	 * @param key
	 * @return
	 */
	T produce(Object key) throws ProduceException;
	
	/**
	 * 获取设定的超时时间
	 * 
	 * @return
	 */
	long getExpirePeriod();
}
