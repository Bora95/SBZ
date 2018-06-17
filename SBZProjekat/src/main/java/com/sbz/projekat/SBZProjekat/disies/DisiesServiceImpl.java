package com.sbz.projekat.SBZProjekat.disies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbz.projekat.SBZProjekat.symptom.Symptom;
import com.sbz.projekat.SBZProjekat.symptom.SymptomService;

@Transactional(readOnly = true)
@Service
public class DisiesServiceImpl implements DisiesService {

	@Autowired
	private DisiesRepository disiesRepository;
	@Autowired
	private SymptomService symptomService;
	
	@Override
	public Disies findOne(Long id) {
		return disiesRepository.findOne(id);
	}

	@Override
	public List<Disies> findAll() {
		return disiesRepository.findAll();
	}
	
	@Override
	public List<Disies> findAll(Disies.Type type) {
		return disiesRepository.findByType(type);
	}

	@Override
	@Transactional(readOnly = false)
	public Disies add(DisiesDTO input) {
		if(input.getType().equals(Disies.Type.TYPE3) && (input.getSymptoms().size() < 2 || input.getSpecificSymptoms().size() == 0))
			return null;
		
		Set<Long> tmp = new HashSet<>();
		tmp.addAll(input.getSymptoms());
		tmp.addAll(input.getSpecificSymptoms());
		if(tmp.size() < (input.getSpecificSymptoms().size() + input.getSymptoms().size()))
			return null;
		
		Disies save = new Disies(input.getName(), null, null, input.getType());
		for(Long symptom : input.getSymptoms()) {
			save.getSymptoms().add(symptomService.findOne(symptom));
		}
		for(Long symptom : input.getSpecificSymptoms()) {
			save.getSpecificSymptoms().add(symptomService.findOne(symptom));
		}
		return disiesRepository.save(save);
	}
	
	@Override
	@Transactional(readOnly = false)
	public Disies add(Disies input) {
		return disiesRepository.save(input);
	}

	@Override
	@Transactional(readOnly = false)
	public Disies edit(Disies input) {
		if(input.getId() == null)
			return null;
		Disies disiesDB = disiesRepository.findOne(input.getId());
		if(disiesDB == null)
			return null;
		
		if(input.getName() != null && !input.getName().trim().isEmpty())
			disiesDB.setName(input.getName());
		if(input.getSymptoms() != null && !input.getSymptoms().isEmpty())
			disiesDB.setSymptoms(input.getSymptoms());
		if(input.getType() != null)
			disiesDB.setType(input.getType());
		
		return disiesRepository.save(disiesDB);
	}

	@Override
	@Transactional(readOnly = false)
	public Disies delete(Long id) {
		Disies disiesDB = disiesRepository.findOne(id);
		if(disiesDB == null)
			return null;
		disiesRepository.delete(id);
		return disiesDB;
	}

	@Override
	public List<Disies> findDisieses(Set<Long> symptoms) {
		Set<Disies> query = disiesRepository.findBySymptoms_IdInOrSpecificSymptoms_IdIn(symptoms, symptoms);
		Disies[] array = query.toArray(new Disies[query.size()]);
		
		for(int i=0; i < array.length-1; i++) {
			Set<Symptom> tmp1 = new HashSet<>();
			tmp1.addAll(array[i].getSymptoms());
			tmp1.addAll(array[i].getSpecificSymptoms());
			Set<Symptom> tmp2 = new HashSet<>();
			tmp2.addAll(array[i+1].getSymptoms());
			tmp2.addAll(array[i+1].getSpecificSymptoms());
			if(contains(tmp2, symptoms) > contains(tmp1, symptoms)) {
				Disies tmp = array[i+1];
				array[i+1] = array[i];
				array[i] = tmp;
				i = -1;
			}
		}
		
		return new ArrayList<>(Arrays.asList(array));
	}
	
	private int contains(Set<Symptom> set1, Set<Long> set2) {
		int count = 0;
		for(Symptom symptom : set1) {
			if(set2.contains(symptom.getId()))
				count++;
		}
		return count;
	}

}
