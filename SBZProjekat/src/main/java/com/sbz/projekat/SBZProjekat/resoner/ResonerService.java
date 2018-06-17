package com.sbz.projekat.SBZProjekat.resoner;

import java.util.List;
import java.util.Set;

import com.sbz.projekat.SBZProjekat.diagnose.DiagnoseDTO;
import com.sbz.projekat.SBZProjekat.disies.Disies;
import com.sbz.projekat.SBZProjekat.medicalRecord.MedicalRecord;

public interface ResonerService {
	
	public Disies diagnose(Set<Long> symptoms, Disies.Type type, String jmbg);
	
	public String validateDiagnose(DiagnoseDTO input);
	
	public List<MedicalRecord> getReport(ReportType type);
	
	public enum ReportType {
		HRONIC, ADDICT, WEAK;
	}
}
