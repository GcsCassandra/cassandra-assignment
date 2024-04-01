package org.gcs.cassandra.repository;

import java.util.UUID;

import org.gcs.cassandra.entity.Bird;
import org.springframework.data.repository.CrudRepository;

public interface BirdRepository extends CrudRepository<Bird, UUID> {

	Bird findByBirdId(UUID theId);
	

}