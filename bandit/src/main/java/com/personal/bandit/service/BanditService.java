package com.personal.bandit.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import com.personal.bandit.model.Bandit;
import com.personal.bandit.repository.BanditRepository;
import com.personal.bandit.util.TenantContext;

@Service
public class BanditService{// implements ApplicationRunner {

	private static final Logger log = LoggerFactory.getLogger(BanditService.class);

	@Autowired
	private final BanditRepository banditRepository;

	@PersistenceContext
	public EntityManager entityManager;

	@Autowired
	public BanditService(BanditRepository banditRepository) {
		this.banditRepository = banditRepository;
	}

	@Transactional
	public Bandit createEmployee(Bandit bandit) {
		return banditRepository.save(bandit);
	}

	/*
	 * @Override public void run(ApplicationArguments args) throws Exception {
	 * 
	 * TenantContext.setCurrentTenant("tenant1"); Bandit ten1 =
	 * banditRepository.save(new
	 * Bandit("[B110:try_except_pass] Try, Except, Pass detected.", "High",
	 * "Medium", "fportaintier\\vulpy\\good\\libsession.py:22",
	 * "https://bandit.readthedocs.io/en/latest/plugins/b110_try_except_pass.html",
	 * "tenant1")); TenantContext.setCurrentTenant("tenant2"); Bandit ten2 =
	 * banditRepository.save(new
	 * Bandit("[B110:try_except_pass] Try, Except, Pass detected.", "High",
	 * "Medium", "fportaintier\\vulpy\\good\\libsession.py:22",
	 * "https://bandit.readthedocs.io/en/latest/plugins/b110_try_except_pass.html",
	 * "tenant2")); TenantContext.clear(); log.info("For tenant 1 :" +
	 * ten1.getTenantId()); log.info("For tenant 2 :" + ten2.getTenantId());
	 * 
	 * }
	 */

}
