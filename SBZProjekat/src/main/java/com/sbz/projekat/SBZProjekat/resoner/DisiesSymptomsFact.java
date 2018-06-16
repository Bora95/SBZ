package com.sbz.projekat.SBZProjekat.resoner;

import java.util.Set;

import com.sbz.projekat.SBZProjekat.disies.Disies;
import com.sbz.projekat.SBZProjekat.symptom.Symptom;

public class DisiesSymptomsFact {
	
	private Disies disies;
	
	private Set<Symptom> symptoms;

	public DisiesSymptomsFact() {}

	public DisiesSymptomsFact(Disies disies, Set<Symptom> symptoms) {
		this.disies = disies;
		this.symptoms = symptoms;
	}
	
	public Disies getDisies() {
		return disies;
	}

	public void setDisies(Disies disies) {
		this.disies = disies;
	}

	public Set<Symptom> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(Set<Symptom> symptoms) {
		this.symptoms = symptoms;
	}
	
	public int symptomsCount() {
		return disies.getSymptoms().size() + disies.getSpecificSymptoms().size();
	}
	
	public double proc() {
		return ((double) symptoms.size()) / symptomsCount();
	}
}
