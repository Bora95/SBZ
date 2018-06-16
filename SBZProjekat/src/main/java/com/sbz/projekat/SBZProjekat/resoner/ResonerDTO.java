package com.sbz.projekat.SBZProjekat.resoner;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.sbz.projekat.SBZProjekat.disies.Disies;

public class ResonerDTO {
	
	private Set<Long> symptoms;
	
	@NotNull
	private Disies.Type type;
	
	@Pattern(regexp = "^(0[1-9]|[1-2][0-9]|31(?!(?:0[2469]|11))|30(?!02))(0[1-9]|1[0-2])([09][0-9]{2})([0-8][0-9]|9[0-6])([0-9]{3})(\\d)$")
	private String jmbg;

	public ResonerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Set<Long> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(Set<Long> symptoms) {
		if(symptoms == null)
			symptoms = new HashSet<>();
		this.symptoms = symptoms;
	}

	public Disies.Type getType() {
		return type;
	}

	public void setType(Disies.Type type) {
		this.type = type;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

}
