package com.thinkgem.jeesite.modules.sys.listener;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListenerAdapter;

import org.apache.log4j.Logger;

public class DataCacheEventListener extends CacheEventListenerAdapter {
	Logger logger = Logger.getLogger(getClass());

	@Override
	public void notifyElementPut(Ehcache cache, Element element) throws CacheException {
		if (isHandlable(cache)) {
	    	handleCacheChanged("put", element);
	    }
	    super.notifyElementPut(cache, element);
	}
	
	@Override
	public void notifyElementRemoved(Ehcache cache, Element element) throws CacheException {
		if (isHandlable(cache)) {
	    	handleCacheChanged("removed", element);
	    }
	    super.notifyElementRemoved(cache, element);
	}
	
	@Override
	public void notifyElementUpdated(Ehcache cache, Element element) throws CacheException {
	    if (isHandlable(cache)) {
	    	handleCacheChanged("updated", element);
	    }
	    super.notifyElementUpdated(cache, element);
	}
	

	public boolean isHandlable(Ehcache cache) {
		return "pageDataCache".equals(cache.getName());
	}

	public void handleCacheChanged(String eventType, Element e) {
		logger.debug("ehcache event: " + eventType);
		logger.debug("ehcache key: " + e.getObjectKey());
	}
}
