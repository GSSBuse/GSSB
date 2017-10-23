package com.thinkgem.jeesite.modules.paimai.front.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.modules.paimai.front.web.FrontCommonController;
import com.thinkgem.jeesite.modules.paimai.front.web.FrontIbuyController;
import com.thinkgem.jeesite.modules.paimai.front.web.FrontIndexController;
import com.thinkgem.jeesite.modules.wx.utils.DySysUtils;

@Component
@Aspect
public class PcLoginCheckAspect {
	private final static Logger logger = Logger.getLogger(PcLoginCheckAspect.class);
	
	//配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
	@Pointcut("execution(* com.thinkgem.jeesite.modules.paimai.front.web..*(..))")
	public void aspect(){	}
	
	@Before("aspect()")
	public void before(JoinPoint joinPoint) throws NotLoginException{
//		System.out.println(joinPoint.toShortString());
//		System.out.println(joinPoint.getTarget());
//		System.out.println(joinPoint.getSignature().getName());
		Object target = joinPoint.getThis();
		if (target instanceof FrontIbuyController) {
			
		} else if (target instanceof FrontIndexController) {
			
		} else if (target instanceof FrontCommonController) {
			
		} else {
			if (DySysUtils.getCurrentDyClient() == null) {
				throw new NotLoginException("");
			}
		}
	}
}
