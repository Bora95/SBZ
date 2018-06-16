package com.sbz.projekat.SBZProjekat.disies;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.sbz.projekat.SBZProjekat.symptom.Symptom;

@Entity
public class Disies {
	
	@Id
	@GeneratedValue
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
	@Version
	private Long version;
	
	@Column(unique=true)
	@NotBlank
	private String name;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@NotEmpty
	private Set<Symptom> symptoms;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Symptom> specificSymptoms;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private Type type;
	
	public Disies() {}

	public Disies(String name, Set<Symptom> symptoms, Set<Symptom> specificSymptoms, Type type) {
		super();
		this.name = name;
		this.symptoms = symptoms;
		this.specificSymptoms = specificSymptoms;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Symptom> getSymptoms() {
		if(symptoms == null)
			symptoms = new HashSet<>();
		return symptoms;
	}

	public void setSymptoms(Set<Symptom> symptoms) {
		this.symptoms = symptoms;
	}

	public Set<Symptom> getSpecificSymptoms() {
		if(specificSymptoms == null)
			specificSymptoms = new HashSet<>();
		return specificSymptoms;
	}

	public void setSpecificSymptoms(Set<Symptom> specificSymptoms) {
		this.specificSymptoms = specificSymptoms;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Disies other = (Disies) obj;
        if(getId() != other.getId())
        	return false;
        return true;
	}
	
	@Override
	public int hashCode() {
		return Math.toIntExact(getId());
	}

	public enum Type {
		TYPE1, TYPE2, TYPE3
	}
}
