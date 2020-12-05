package com.personal.bandit.service.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;

import com.personal.bandit.service.BanditService;
import com.personal.bandit.util.TenantContext;

public class BanditServiceAspect {
	@Before("execution(*com.personal.bandit.service.BanditService.*(..)) && !execution(*com.personal.bandit.service.BanditService.run(..)) && target(banditService)")
	public void aroundExecution(JoinPoint pjp, BanditService banditService) throws Throwable {
		org.hibernate.Filter filter = banditService.entityManager.unwrap(Session.class).enableFilter("tenantFilter");
		filter.setParameter("tenantId", TenantContext.getCurrentTenant());
		filter.validate();
	}
}
