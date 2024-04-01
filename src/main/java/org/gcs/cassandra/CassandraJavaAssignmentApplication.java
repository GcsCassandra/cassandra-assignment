package org.gcs.cassandra;

import org.gcs.cassandra.controller.BirdController;
import org.gcs.cassandra.controller.LocationController;
import org.gcs.cassandra.controller.SearchController;
import org.gcs.cassandra.service.BirdService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "org.gcs.cassandra.cassandraconfig" })
@ComponentScan(basePackageClasses = { LocationController.class, BirdController.class, SearchController.class,
		BirdService.class })
public class CassandraJavaAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CassandraJavaAssignmentApplication.class, args);
		
	}

}
