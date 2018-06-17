package com.sbz.projekat.SBZProjekat.resoner;

import java.util.Set;

import com.sbz.projekat.SBZProjekat.disies.Disies;
import com.sbz.projekat.SBZProjekat.medicalRecord.MedicalRecord;

public class MedicalRecordDisies {
	
	private MedicalRecord medicalRecord;
	
	private Set<Disies> disies;

	public MedicalRecordDisies() {}

	public MedicalRecordDisies(MedicalRecord medicalRecord, Set<Disies> disies) {
		this.medicalRecord = medicalRecord;
		this.disies = disies;
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public Set<Disies> getDisies() {
		return disies;
	}

	public void setDisies(Set<Disies> disies) {
		this.disies = disies;
	}
	
}
