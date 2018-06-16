package com.sbz.projekat.SBZProjekat.medicalRecord;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.sbz.projekat.SBZProjekat.diagnose.Diagnose;
import com.sbz.projekat.SBZProjekat.drug.Drug;
import com.sbz.projekat.SBZProjekat.substance.Substance;

@Entity
public class MedicalRecord {
	
	@Id
	@Pattern(regexp = "^(0[1-9]|[1-2][0-9]|31(?!(?:0[2469]|11))|30(?!02))(0[1-9]|1[0-2])([09][0-9]{2})([0-8][0-9]|9[0-6])([0-9]{3})(\\d)$")
	private String jmbg;
	
	@Version
	private Long version;
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private Gender gender;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Diagnose> diagnoses;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Substance> alergicToSubstances;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Drug> alergicToDrugs;
	
	public MedicalRecord() {}
	
	public MedicalRecord(String jmbg, String firstName, String lastName, Gender gender) {
		this.jmbg = jmbg;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
	}
	
	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
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

	public Set<Diagnose> getDiagnoses() {
		if(diagnoses == null)
			diagnoses = new HashSet<>();
		return diagnoses;
	}

	public void setDiagnoses(Set<Diagnose> diagnoses) {
		this.diagnoses = diagnoses;
	}

	public Set<Substance> getAlergicToSubstances() {
		if(alergicToSubstances == null)
			alergicToSubstances = new HashSet<>();
		return alergicToSubstances;
	}

	public void setAlergicToSubstances(Set<Substance> alergicToSubstances) {
		this.alergicToSubstances = alergicToSubstances;
	}
	
	public Set<Drug> getAlergicToDrugs() {
		if(alergicToDrugs == null)
			alergicToDrugs = new HashSet<>();
		return alergicToDrugs;
	}

	public void setAlergicToDrugs(Set<Drug> alergicToDrugs) {
		this.alergicToDrugs = alergicToDrugs;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MedicalRecord other = (MedicalRecord) obj;
        if(getJmbg() != other.getJmbg())
        	return false;
        return true;
	}

	public enum Gender {
		MALE, FEMALE;
	}

}
