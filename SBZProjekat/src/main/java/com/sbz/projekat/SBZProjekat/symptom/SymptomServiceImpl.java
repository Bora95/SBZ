package com.sbz.projekat.SBZProjekat.symptom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbz.projekat.SBZProjekat.disies.DisiesService;
import com.sbz.projekat.SBZProjekat.drug.DrugService;

@Transactional(readOnly = true)
@Service
public class SymptomServiceImpl implements SymptomService {

	@Autowired
	private SymptomRepository symptomRepository;
	@Autowired
	private DrugService drugService;
	@Autowired
	private DisiesService disiesService;
	
	@Override
	public Symptom findOne(Long id) {
		return symptomRepository.findOne(id);
	}

	@Override
	public List<Symptom> findAll() {
		return symptomRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public Symptom add(SymptomDTO input) {
		Symptom save = new Symptom(input.getName(), input.getTimeDuration(), input.getCases(), null, null, null);
		for(Long drug : input.getDrug()) {
			save.getDrug().add(drugService.findOne(drug));
		}
		for(Long symptom : input.getSymptom()) {
			save.getSymptom().add(findOne(symptom));
		}
		for(Long disies : input.getDisies()) {
			save.getDisies().add(disiesService.findOne(disies));
		}
		return symptomRepository.save(save);
	}
	
	@Override
	@Transactional(readOnly = false)
	public Symptom add(Symptom input) {
		return symptomRepository.save(input);
	}

	@Override
	@Transactional(readOnly = false)
	public Symptom edit(Symptom input) {
		if(input.getId() == null)
			return null;
		Symptom symptomDB = symptomRepository.findOne(input.getId());
		if(symptomDB == null)
			return null;
		
		if(input.getName() != null)
			symptomDB.setName(input.getName());
		if(input.getTimeDuration() != null)
			symptomDB.setTimeDuration(input.getTimeDuration());
		
		return symptomRepository.save(symptomDB);
	}

	@Override
	@Transactional(readOnly = false)
	public Symptom delete(Long id) {
		Symptom symptomDB = symptomRepository.findOne(id);
		if(symptomDB == null)
			return null;
		symptomRepository.delete(id);
		return symptomDB;
	}

}
