package com.sbz.projekat.SBZProjekat.diagnose;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class DiagnoseDTO {
	
	private Long id;
	
	@Pattern(regexp = "^(0[1-9]|[1-2][0-9]|31(?!(?:0[2469]|11))|30(?!02))(0[1-9]|1[0-2])([09][0-9]{2})([0-8][0-9]|9[0-6])([0-9]{3})(\\d)$")
	private String jmbg;
	
	@NotNull
	private Long disies;
	
	@NotEmpty
	private Set<Long> drugs;
	
	private Long doctor;
	
	public DiagnoseDTO() {}

	public DiagnoseDTO(Long disies, Set<Long> drugs, Long doctor) {
		super();
		this.disies = disies;
		this.drugs = drugs;
		this.doctor = doctor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
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
