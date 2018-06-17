package com.sbz.projekat.SBZProjekat.diagnose;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.sbz.projekat.SBZProjekat.disies.Disies;
import com.sbz.projekat.SBZProjekat.drug.Drug;
import com.sbz.projekat.SBZProjekat.user.User;

@Entity
public class Diagnose {
	
	@Id
	@GeneratedValue
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
	@Version
	private Long version;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull
	private Disies disies;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@NotEmpty
	private Set<Drug> drugs;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull
	private User doctor;
	
	@NotNull
	private Long timeStamp;

	public Diagnose() {}

	public Diagnose(Disies disies, Set<Drug> drugs, User doctor) {
		super();
		this.disies = disies;
		this.drugs = drugs;
		this.doctor = doctor;
		this.timeStamp = System.currentTimeMillis();
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

	public Disies getDisies() {
		return disies;
	}

	public void setDisies(Disies disies) {
		this.disies = disies;
	}

	public Set<Drug> getDrugs() {
		if(drugs == null)
			drugs = new HashSet<>();
		return drugs;
	}

	public void setDrugs(Set<Drug> drugs) {
		this.drugs = drugs;
	}

	public User getDoctor() {
		return doctor;
	}

	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Diagnose other = (Diagnose) obj;
        if(getId() != other.getId())
        	return false;
        return true;
	}
	
	@Override
	public int hashCode() {
		return Math.toIntExact(getId());
	}
	
}
