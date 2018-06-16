package com.sbz.projekat.SBZProjekat.substance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class SubstanceServiceImpl implements SubstanceService {

	@Autowired
	private SubstanceRepository substanceRepository;
	
	@Override
	public Substance findOne(Long id) {
		return substanceRepository.findOne(id);
	}

	@Override
	public List<Substance> findAll() {
		return substanceRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = false)
	public Substance add(Substance input) {
		return substanceRepository.save(input);
	}

	@Override
	@Transactional(readOnly = false)
	public Substance edit(Substance input) {
		if(input.getId() == null)
			return null;
		Substance substanceDB = substanceRepository.findOne(input.getId());
		if(substanceDB == null)
			return null;
		
		if(input.getName() != null)
			substanceDB.setName(input.getName());
		
		return substanceRepository.save(substanceDB);
	}

	@Override
	@Transactional(readOnly = false)
	public Substance delete(Long id) {
		Substance substanceDB = substanceRepository.findOne(id);
		if(substanceDB == null)
			return null;
		substanceRepository.delete(id);
		return substanceDB;
	}

}
