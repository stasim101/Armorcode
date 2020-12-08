package com.personal.bandit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.personal.bandit.model.Tenant;

@Repository
public interface TenantRepository extends CrudRepository<Tenant, Integer> {

}
