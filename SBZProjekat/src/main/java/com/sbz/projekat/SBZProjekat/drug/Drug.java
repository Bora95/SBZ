package com.sbz.projekat.SBZProjekat.drug;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.sbz.projekat.SBZProjekat.substance.Substance;

@Entity
public class Drug {
	
	@Id
	@GeneratedValue
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
	@Version
	private Long version;
	
	@Column(unique=true)
	@NotBlank
	private String name;
	
	@NotNull
	private Type type;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Substance> substances;
	
	public Drug() {}

	public Drug(String name, Type type, Set<Substance> substances) {
		this.name = name;
		this.type = type;
		this.substances = substances;
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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Set<Substance> getSubstances() {
		if(substances == null)
			substances = new HashSet<>();
		return substances;
	}

	public void setSubstances(Set<Substance> substances) {
		this.substances = substances;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Drug other = (Drug) obj;
        if(getId() != other.getId())
        	return false;
        return true;
	}
	
	@Override
	public int hashCode() {
		return Math.toIntExact(getId());
	}

	public enum Type {
		ANTIBIOTIC, ANALGETIC, OTHER;
	}
	
}
