package com.thinkgem.jeesite.modules.sys.front.service.gbj;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.paimai.bean.GbjBuyEntity;
import com.thinkgem.jeesite.modules.sys.dao.gbj.FrontGbjBuyDao;
import com.thinkgem.jeesite.modules.sys.dao.gbj.GbjBuyDao;
import com.thinkgem.jeesite.modules.sys.entity.gbj.GbjBuy;




@Service
@Transactional(readOnly = true)
public class FrontGbjBuyService extends CrudService<FrontGbjBuyDao, GbjBuyEntity>{

	 
	/*public GbjBuy get(String id) {
		return super.get(id);
	}
	*/
	
	public GbjBuyEntity get(String id) {
		return super.get(id);
	}
	
	
	public List<GbjBuyEntity> findList(GbjBuyEntity gbjBuyEntity){
		return super.findList(gbjBuyEntity);
	}
	
	
	public Page<GbjBuyEntity> findPage(Page<GbjBuyEntity> page, GbjBuyEntity gbjBuyEntity){
		return super.findPage(page, gbjBuyEntity);
	}
	
	@Transactional(readOnly = false)
	public void save(GbjBuyEntity gbjBuyEntity) {
		super.save(gbjBuyEntity);
	}
	
	@Transactional(readOnly = false)
	public void delete(GbjBuyEntity gbjBuyEntity) {
		super.delete(gbjBuyEntity);
	}
	
	/*public List<GbjBuy> findList(GbjBuy gbjBuy) {
		return super.findList(gbjBuy);
	}
	
	public Page<GbjBuy> findPage(Page<GbjBuy> page, GbjBuy gbjBuy) {
		return super.findPage(page, gbjBuy);
	}
	
	@Transactional(readOnly = false)
	public void save(GbjBuy gbjBuy) {
		super.save(gbjBuy);
	}
	
	@Transactional(readOnly = false)
	public void delete(GbjBuy gbjBuy) {
		super.delete(gbjBuy);
	}*/
}
