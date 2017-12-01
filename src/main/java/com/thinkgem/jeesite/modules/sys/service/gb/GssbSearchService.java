package com.thinkgem.jeesite.modules.sys.service.gb;

import com.thinkgem.jeesite.common.service.ServiceException;
import com.thinkgem.jeesite.modules.sys.entity.gb.GbBuy;

public class GssbSearchService {
	
	/**
	 * 提交域名，需同时完成域名信息的插入及保证金的扣除,资金流的插入，状态改为01，但金额不够时状态改为00，以后可以再次提交
	 * @param domainInfo
	 * @param client
	 * @return true 表示资金充足，false表示资金不够，需提示用充值
	 * @throws ServiceException 回滚
	 */
	public void submitDomain(GbBuy gbBuy) {
		// TODO Auto-generated method stub
		
	}

}
