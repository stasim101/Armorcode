package com.personal.ingest.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.personal.ingest.model.ScanTool;

@Repository
public interface ScanToolRepository extends CrudRepository<ScanTool, Integer> {

	Optional<ScanTool> findByToolName(String toolName);

}
