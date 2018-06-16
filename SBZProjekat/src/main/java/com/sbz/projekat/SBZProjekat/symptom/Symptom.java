package com.sbz.projekat.SBZProjekat.symptom;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Version;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.sbz.projekat.SBZProjekat.disies.Disies;
import com.sbz.projekat.SBZProjekat.drug.Drug;

@Entity
public class Symptom {
	
	@Id
	@GeneratedValue
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	
	@Version
	private Long version;
	
	@Column(unique=true)
	@NotBlank
	private String name;
	
	private Long timeDuration;
	
	private Integer cases;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Drug> drug;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Disies> disies;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( joinColumns = @JoinColumn(name = "symptom_id"),
    			inverseJoinColumns = @JoinColumn(name = "symptoms_id"))
	private Set<Symptom> symptom;
	
	public Symptom() {}

	public Symptom(String name, Long timeDuration, Integer cases, Set<Drug> drug, Set<Disies> disies, Set<Symptom> symptom) {
		this.name = name;
		this.timeDuration = timeDuration;
		this.cases = cases;
		this.drug = drug;
		this.disies = disies;
		this.symptom = symptom;
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

	public Long getTimeDuration() {
		return timeDuration;
	}

	public void setTimeDuration(Long timeDuration) {
		this.timeDuration = timeDuration;
	}
	
	public Integer getCases() {
		return cases;
	}

	public void setCases(Integer cases) {
		this.cases = cases;
	}
	
	public Set<Drug> getDrug() {
		if(drug == null)
			drug = new HashSet<>();
		return drug;
	}

	public void setDrug(Set<Drug> drug) {
		this.drug = drug;
	}

	public Set<Disies> getDisies() {
		if(disies == null)
			disies = new HashSet<>();
		return disies;
	}

	public void setDisies(Set<Disies> disies) {
		this.disies = disies;
	}

	public Set<Symptom> getSymptom() {
		if(symptom == null)
			symptom = new HashSet<>();
		return symptom;
	}

	public void setSymptom(Set<Symptom> symptom) {
		this.symptom = symptom;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Symptom other = (Symptom) obj;
        if(getId() != other.getId())
        	return false;
        return true;
	}
	
	@Override
	public int hashCode() {
		return Math.toIntExact(getId());
	}

}
