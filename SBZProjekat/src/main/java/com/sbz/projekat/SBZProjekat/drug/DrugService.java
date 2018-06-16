package com.sbz.projekat.SBZProjekat.drug;

import java.util.List;

import javax.validation.constraints.NotNull;

public interface DrugService {
	
	public Drug findOne(@NotNull Long id);
	
	public List<Drug> findAll();
	
	public Drug add(@NotNull DrugDTO input);
	
	public Drug add(@NotNull Drug input);
	
	public Drug edit(@NotNull Drug input);
	
	public Drug delete(@NotNull Long id) throws Exception;
	
}
