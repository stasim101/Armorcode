package com.personal.ingest.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.personal.ingest.model.TopicDetails;

@Repository
public interface TopicDetailsRepository extends CrudRepository<TopicDetails, Integer> {
	Optional<TopicDetails> findByTopicName(String topicName);
}
