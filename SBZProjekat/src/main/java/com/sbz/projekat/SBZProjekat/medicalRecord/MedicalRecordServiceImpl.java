package com.sbz.projekat.SBZProjekat.medicalRecord;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbz.projekat.SBZProjekat.diagnose.Diagnose;
import com.sbz.projekat.SBZProjekat.diagnose.DiagnoseDTO;
import com.sbz.projekat.SBZProjekat.diagnose.DiagnoseService;
import com.sbz.projekat.SBZProjekat.disies.Disies;
import com.sbz.projekat.SBZProjekat.drug.Drug;
import com.sbz.projekat.SBZProjekat.substance.SubstanceService;
import com.sbz.projekat.SBZProjekat.symptom.Symptom;
import com.sbz.projekat.SBZProjekat.symptom.SymptomService;

@Transactional(readOnly = true)
@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
	@Autowired
	private SymptomService symptomService;
	@Autowired
	private SubstanceService substanceService;
	@Autowired
	private DiagnoseService diagnosService;
	
	@Override
	public MedicalRecord findOne(String jmbg) {
		return medicalRecordRepository.findOne(jmbg);
	}

	@Override
	public List<MedicalRecord> findAll() {
		return medicalRecordRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public MedicalRecord add(MedicalRecordDTO input) {
		MedicalRecord save = new MedicalRecord(input.getJmbg(), input.getFirstName(), input.getLastName(), input.getGender());
		for(Long substance : input.getAlergicToSubstances()) {
			save.getAlergicToSubstances().add(substanceService.findOne(substance));
		}
		return medicalRecordRepository.save(save);
	}
	
	@Override
	@Transactional(readOnly = false)
	public MedicalRecord add(MedicalRecord input) {
		return medicalRecordRepository.save(input);
	}

	@Override
	@Transactional(readOnly = false)
	public MedicalRecord edit(MedicalRecord input) {
		if(input.getJmbg() == null)
			return null;
		MedicalRecord medicalRecordDB = medicalRecordRepository.findOne(input.getJmbg());
		if(medicalRecordDB == null)
			return null;
		
		if(input.getFirstName() != null && !input.getFirstName().trim().isEmpty())
			medicalRecordDB.setFirstName(input.getFirstName());
		if(input.getLastName() != null && !input.getLastName().trim().isEmpty())
			medicalRecordDB.setLastName(input.getLastName());
		if(input.getDiagnoses() != null)
			medicalRecordDB.getDiagnoses().addAll(input.getDiagnoses());
		if(input.getAlergicToSubstances() != null)
			medicalRecordDB.getAlergicToSubstances().addAll(input.getAlergicToSubstances());
		
		return medicalRecordRepository.save(medicalRecordDB);
	}

	@Override
	@Transactional(readOnly = false)
	public MedicalRecord delete(String jmbg) throws Exception {
		MedicalRecord medicalRecordDB = medicalRecordRepository.findOne(jmbg);
		if(medicalRecordDB == null)
			return null;
		medicalRecordRepository.delete(jmbg);
		return medicalRecordDB;
	}

	@Override
	public boolean checkSymptom(String jmbg, Long symptomId) {
		Symptom s = symptomService.findOne(symptomId);
		if(s == null)
			return false;
		MedicalRecord record = findOne(jmbg);
		if(record == null)
			return false;
		
		Integer count = null;
		for (Diagnose diagnose : record.getDiagnoses()) {
			if(s.getTimeDuration() != null) {
				Long passed = System.currentTimeMillis() - diagnose.getTimeStamp();
				if(passed < 0)
					continue;
				
					if(s.getTimeDuration() < 0) {
						if((passed + s.getTimeDuration()) > 0)
							continue;
					} else {
						if(passed < s.getTimeDuration())
							continue;
					}
			}
			
			count = s.getCases();
			
			for (Drug drug : s.getDrug()) {
				if(diagnose.getDrugs().contains(drug)) {
					if(count != null) {
						count--;
					} else { 
						return true;
					}
				}
			}
			for(Symptom symptom : s.getSymptom()) {
				if(diagnose.getDisies().getSymptoms().contains(symptom) || diagnose.getDisies().getSpecificSymptoms().contains(symptom)) {
					if(count != null) {
						count--;
					} else { 
						return true;
					}
				}
			}
			for(Disies disies : s.getDisies()) {
				if(diagnose.getDisies().equals(disies)) {
					if(count != null) {
						count--;
					} else { 
						return true;
					}
				}
			}
		}
		if(count != null && count == 0)
			return true;
		return false;
	}

	@Override
	@Transactional(readOnly = false)
	public MedicalRecord addDiagnose(DiagnoseDTO input, String jmbg) {
		MedicalRecord mr = findOne(jmbg);
		if(mr == null)
			return null;
		Diagnose diagnose = diagnosService.add(input);
		if(diagnose == null)
			return null;
		
		mr.getDiagnoses().add(diagnose);
		return medicalRecordRepository.save(mr);
	}
	
}
