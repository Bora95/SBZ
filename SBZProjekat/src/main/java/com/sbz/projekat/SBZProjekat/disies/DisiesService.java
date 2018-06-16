package com.sbz.projekat.SBZProjekat.disies;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;

public interface DisiesService {
	
	public Disies findOne(@NotNull Long id);
	
	public List<Disies> findAll();
	
	public List<Disies> findAll(Disies.Type type);
	
	public Disies add(@NotNull DisiesDTO input);
	
	public Disies add(@NotNull Disies input);
	
	public Disies edit(@NotNull Disies input);
	
	public Disies delete(@NotNull Long id) throws Exception;

	public List<Disies> findDisieses(@NotNull Set<Long> symptoms);

}
