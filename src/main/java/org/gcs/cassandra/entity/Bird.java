package org.gcs.cassandra.entity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("birds")
public class Bird implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	private UUID birdId = UUID.randomUUID();
	private String location;
	private String scanDay;
	private String birdSpecies;
	private List<String> birdTraits;

	public Bird() {
		this.birdId = UUID.randomUUID();
	}

	public UUID getBirdId() {
		return birdId;
	}

	public void setBirdId(UUID birdId) {
		this.birdId = birdId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getScanDay() {
		return scanDay;
	}

	public void setScanDay(String scanDay) {
		this.scanDay = scanDay;
	}

	public String getBirdSpecies() {
		return birdSpecies;
	}

	public void setBirdSpecies(String birdSpecies) {
		this.birdSpecies = birdSpecies;
	}

	public List<String> getBirdTraits() {
		return birdTraits;
	}

	public void setBirdTraits(List<String> birdTraits) {
		this.birdTraits = birdTraits;
	}

	@Override
	public String toString() {
		return "Bird [birdId=" + birdId + ", location=" + location + ", scanDay=" + scanDay + ", birdSpecies="
				+ birdSpecies + ", birdTraits=" + birdTraits + "]";
	}

}
