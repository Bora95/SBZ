package com.sbz.projekat.SBZProjekat.symptom;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.NotBlank;

public class SymptomDTO {
	
	private Long id;
	
	@NotBlank
	private String name;
	
	private Set<Long> drug;
	
	private Set<Long> symptom;
	
	private Set<Long> disies;
	
	private Long timeDuration;
	
	private Integer cases;

	public SymptomDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public Set<Long> getDrug() {
		if(drug == null)
			drug = new HashSet<>();
		return drug;
	}

	public void setDrug(Set<Long> drug) {
		this.drug = drug;
	}

	public Set<Long> getSymptom() {
		if(symptom == null)
			symptom = new HashSet<>();
		return symptom;
	}

	public void setSymptom(Set<Long> symptom) {
		this.symptom = symptom;
	}

	public Set<Long> getDisies() {
		if(disies == null)
			disies = new HashSet<>();
		return disies;
	}

	public void setDisies(Set<Long> disies) {
		this.disies = disies;
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
	
}
