package com.sbz.projekat.SBZProjekat.diagnose;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class DiagnoseDTO {
	
	private Long id;
	
	@NotNull
	private Long disies;
	
	@NotEmpty
	private Set<Long> drugs;
	
	@NotNull
	private Long doctor;
	
	public DiagnoseDTO() {}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDisies() {
		return disies;
	}

	public void setDisies(Long disies) {
		this.disies = disies;
	}

	public Set<Long> getDrugs() {
		if(drugs == null)
			drugs = new HashSet<>();
		return drugs;
	}

	public void setDrugs(Set<Long> drugs) {
		this.drugs = drugs;
	}

	public Long getDoctor() {
		return doctor;
	}

	public void setDoctor(Long doctor) {
		this.doctor = doctor;
	}
	
}
