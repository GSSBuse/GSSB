package com.thinkgem.jeesite.modules.sys.listener;

import java.util.Properties;

import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.CacheEventListenerFactory;

public class DataCacheEventListenerFactory extends CacheEventListenerFactory {

	@Override
    public CacheEventListener createCacheEventListener(Properties properties) {
	    return new DataCacheEventListener();
    }

}
