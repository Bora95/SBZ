package com.sbz.projekat.SBZProjekat.medicalRecord;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.sbz.projekat.SBZProjekat.diagnose.DiagnoseDTO;

public interface MedicalRecordService {
	
	public MedicalRecord findOne(@NotNull String jmbg);
	
	public List<MedicalRecord> findAll();
	
	public MedicalRecord add(@NotNull MedicalRecordDTO input);
	
	public MedicalRecord add(@NotNull MedicalRecord input);
	
	public MedicalRecord edit(@NotNull MedicalRecord input);
	
	public MedicalRecord delete(@NotNull String jmbg) throws Exception;
	
	public boolean checkSymptom(@NotNull String jmbg, @NotNull Long symptomId);
	
	public MedicalRecord addDiagnose(@NotNull DiagnoseDTO input);
}
