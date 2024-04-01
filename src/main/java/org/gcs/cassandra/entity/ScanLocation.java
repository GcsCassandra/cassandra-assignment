package org.gcs.cassandra.entity;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("locations")
public class ScanLocation implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	private UUID locationId;
	private String location;
	private String name;

	public ScanLocation() {
		this.locationId = UUID.randomUUID();
	}

	public UUID getLocationId() {
		return locationId;
	}

	public void setLocationId(UUID locationId) {
		this.locationId = locationId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ScanLocation [locationId=" + locationId + ", location=" + location + ", name=" + name + "]";
	}
	
}
