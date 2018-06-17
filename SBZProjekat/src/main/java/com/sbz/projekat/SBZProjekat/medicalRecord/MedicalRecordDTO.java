package com.sbz.projekat.SBZProjekat.medicalRecord;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.sbz.projekat.SBZProjekat.medicalRecord.MedicalRecord.Gender;

public class MedicalRecordDTO {
	
	@Pattern(regexp = "^(0[1-9]|[1-2][0-9]|31(?!(?:0[2469]|11))|30(?!02))(0[1-9]|1[0-2])([09][0-9]{2})([0-8][0-9]|9[0-6])([0-9]{3})(\\d)$")
	private String jmbg;
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private Gender gender;
	
	private Set<Long> alergicToSubstances;
	
	private Set<Long> alergicToDrugs;
	
	public MedicalRecordDTO() {}
	
	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Set<Long> getAlergicToSubstances() {
		if(alergicToSubstances == null)
			alergicToSubstances = new HashSet<>();
		return alergicToSubstances;
	}

	public void setAlergicToSubstances(Set<Long> alergicToSubstances) {
		this.alergicToSubstances = alergicToSubstances;
	}

	public Set<Long> getAlergicToDrugs() {
		if(alergicToDrugs == null)
			alergicToDrugs = new HashSet<>();
		return alergicToDrugs;
	}

	public void setAlergicToDrugs(Set<Long> alergicToDrugs) {
		this.alergicToDrugs = alergicToDrugs;
	}
	
	
}
