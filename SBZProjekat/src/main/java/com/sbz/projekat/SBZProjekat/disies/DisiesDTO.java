package com.sbz.projekat.SBZProjekat.disies;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.sbz.projekat.SBZProjekat.disies.Disies.Type;

public class DisiesDTO {
	
	
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotEmpty
	private Set<Long> symptoms;
	
	private Set<Long> specificSymptoms;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private Type type;
	
	public DisiesDTO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Long> getSymptoms() {
		if(symptoms == null)
			symptoms = new HashSet<>();
		return symptoms;
	}

	public void setSymptoms(Set<Long> symptoms) {
		this.symptoms = symptoms;
	}

	public Set<Long> getSpecificSymptoms() {
		if(specificSymptoms == null)
			specificSymptoms = new HashSet<>();
		return specificSymptoms;
	}

	public void setSpecificSymptoms(Set<Long> specificSymptoms) {
		this.specificSymptoms = specificSymptoms;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
}
