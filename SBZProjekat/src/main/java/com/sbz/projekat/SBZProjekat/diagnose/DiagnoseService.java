package com.sbz.projekat.SBZProjekat.diagnose;

import java.util.List;

import javax.validation.constraints.NotNull;

public interface DiagnoseService {
	
	public Diagnose findOne(@NotNull Long id);
	
	public List<Diagnose> findAll();
	
	public Diagnose add(@NotNull DiagnoseDTO input);
	
	public Diagnose add(@NotNull Diagnose input);
	
	public Diagnose edit(@NotNull Diagnose input);
	
	public Diagnose delete(@NotNull Long id) throws Exception;
	
}
