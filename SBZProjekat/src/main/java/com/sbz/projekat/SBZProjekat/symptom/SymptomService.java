package com.sbz.projekat.SBZProjekat.symptom;

import java.util.List;

import javax.validation.constraints.NotNull;

public interface SymptomService {
	
	public Symptom findOne(@NotNull Long id);
	
	public List<Symptom> findAll();
	
	public Symptom add(@NotNull SymptomDTO input);
	
	public Symptom add(@NotNull Symptom input);
	
	public Symptom edit(@NotNull Symptom input);
	
	public Symptom delete(@NotNull Long id) throws Exception;
	
}
