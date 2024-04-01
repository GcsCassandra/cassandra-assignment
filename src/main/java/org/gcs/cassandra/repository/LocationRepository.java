package org.gcs.cassandra.repository;

import java.util.UUID;

import org.gcs.cassandra.entity.ScanLocation;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<ScanLocation, UUID>{

	ScanLocation findByLocationId(UUID theId);	
	
}
