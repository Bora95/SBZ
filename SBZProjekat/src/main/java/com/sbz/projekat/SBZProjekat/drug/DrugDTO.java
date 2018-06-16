package com.sbz.projekat.SBZProjekat.drug;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.sbz.projekat.SBZProjekat.drug.Drug.Type;

public class DrugDTO {

	private Long id;
	
	@NotBlank
	private String name;
	
	@NotNull
	private Type type;
	
	private Set<Long> substances;
	
	public DrugDTO() {}

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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Set<Long> getSubstances() {
		if(substances == null)
			substances = new HashSet<>();
		return substances;
	}

	public void setSubstances(Set<Long> substances) {
		this.substances = substances;
	}

}
