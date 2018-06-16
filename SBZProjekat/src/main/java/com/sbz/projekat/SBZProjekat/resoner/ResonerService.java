package com.sbz.projekat.SBZProjekat.resoner;

import java.util.Set;

import com.sbz.projekat.SBZProjekat.disies.Disies;

public interface ResonerService {
	
	public Disies diagnose(Set<Long> symptoms, Disies.Type type, String jmbg);

}
