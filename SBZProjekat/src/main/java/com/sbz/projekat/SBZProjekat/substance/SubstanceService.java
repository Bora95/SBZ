package com.sbz.projekat.SBZProjekat.substance;

import java.util.List;

import javax.validation.constraints.NotNull;

public interface SubstanceService {

	public Substance findOne(@NotNull Long id);
	
	public List<Substance> findAll();
	
	public Substance add(@NotNull Substance input);
	
	public Substance edit(@NotNull Substance input);
	
	public Substance delete(@NotNull Long id) throws Exception;
	
}
