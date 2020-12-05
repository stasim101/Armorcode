package com.personal.bandit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.personal.bandit.model.Bandit;

@Repository
public interface BanditRepository extends CrudRepository<Bandit,Integer> {

}
