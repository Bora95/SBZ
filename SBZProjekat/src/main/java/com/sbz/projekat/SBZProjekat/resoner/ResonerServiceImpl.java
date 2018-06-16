package com.sbz.projekat.SBZProjekat.resoner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.map.HashedMap;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbz.projekat.SBZProjekat.disies.Disies;
import com.sbz.projekat.SBZProjekat.disies.DisiesService;
import com.sbz.projekat.SBZProjekat.medicalRecord.MedicalRecordService;
import com.sbz.projekat.SBZProjekat.symptom.Symptom;
import com.sbz.projekat.SBZProjekat.symptom.SymptomService;

@Transactional(readOnly = true)
@Service
public class ResonerServiceImpl implements ResonerService {
	
	@Autowired
	private KieContainer kieContainer;
	@Autowired
	private DisiesService disiesService;
	@Autowired
	private SymptomService symptomService;
	@Autowired
	private MedicalRecordService medicalRecordService;

	@Override
	public Disies diagnose(Set<Long> symptoms, Disies.Type type, String jmbg) {
		KieSession kieSession = kieContainer.newKieSession();
		HashedMap<Long, Set<Symptom>> disiesSymptoms = new HashedMap<>();
		List<DisiesSymptomsFact> result = new ArrayList<>();
		kieSession.setGlobal("ret", result);
		kieSession.setGlobal("disiesSymptoms", disiesSymptoms);
		kieSession.setGlobal("medicalRecordService", medicalRecordService);
		kieSession.setGlobal("jmbg", jmbg);
		kieSession.getAgenda().getAgendaGroup("getDisesSymptoms").setFocus();
		for (Disies disies : disiesService.findAll(type)) {
			kieSession.insert(disies);
		}
		for (Long symptom : symptoms) {
			kieSession.insert(symptomService.findOne(symptom));
		}
        kieSession.fireAllRules();
		switch (type) {
			case TYPE1:
				kieSession.getAgenda().getAgendaGroup("getDiagnose_1").setFocus();
				break;
			case TYPE2:
				kieSession.getAgenda().getAgendaGroup("getDiagnose_2").setFocus();
				break;
			case TYPE3:
				kieSession.getAgenda().getAgendaGroup("getDiagnose_3").setFocus();
				break;
			default:
				break;
		}
		for (Map.Entry<Long, Set<Symptom>> entry : disiesSymptoms.entrySet()) {
			kieSession.insert(new DisiesSymptomsFact(disiesService.findOne(entry.getKey()), entry.getValue()));
		}
		kieSession.fireAllRules();
		kieSession.dispose();
		
		if(result.size() > 1 || result.size() == 0) {
			DisiesSymptomsFact max = null;
			for (DisiesSymptomsFact disiesSymptomsFact : result) {
				if(max == null)
					max = disiesSymptomsFact;
				else if(disiesSymptomsFact.proc() > max.proc()) {
					max = disiesSymptomsFact;
				} else if(disiesSymptomsFact.proc() == max.proc() && disiesSymptomsFact.symptomsCount() >= max.symptomsCount()) {
					max = disiesSymptomsFact;
				}
			}
			if(max == null)
				return null;
			return disiesService.findOne(max.getDisies().getId());
		}
		return disiesService.findOne(result.get(0).getDisies().getId());
	}

}
