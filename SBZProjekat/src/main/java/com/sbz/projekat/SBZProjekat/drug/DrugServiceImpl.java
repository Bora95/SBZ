package com.sbz.projekat.SBZProjekat.drug;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbz.projekat.SBZProjekat.substance.SubstanceService;

@Transactional(readOnly = true)
@Service
public class DrugServiceImpl implements DrugService {

	@Autowired
	private DrugRepository drugRepository;
	@Autowired
	private SubstanceService substanceService;
	
	@Override
	public Drug findOne(Long id) {
		return drugRepository.findOne(id);
	}

	@Override
	public List<Drug> findAll() {
		return drugRepository.findAll();
	}

	@Override
	public List<Drug> findAll(Set<Long> ids) {
		return drugRepository.findByIdIn(ids);
	}

	@Override
	@Transactional(readOnly = false)
	public Drug add(DrugDTO input) {
		Drug save = new Drug(input.getName(), input.getType(), null);
		for(Long substance : input.getSubstances()) {
			save.getSubstances().add(substanceService.findOne(substance));
		}
		return drugRepository.save(save);
	}
	
	@Override
	@Transactional(readOnly = false)
	public Drug add(Drug input) {
		return drugRepository.save(input);
	}

	@Override
	@Transactional(readOnly = false)
	public Drug edit(Drug input) {
		if(input.getId() == null)
			return null;
		Drug drugDB = drugRepository.findOne(input.getId());
		if(drugDB == null)
			return null;
		
		if(input.getName() != null && !input.getName().trim().isEmpty())
			drugDB.setName(input.getName());
		if(input.getType() != null)
			drugDB.setType(input.getType());
		if(input.getSubstances() != null && !input.getSubstances().isEmpty())
			drugDB.setSubstances(input.getSubstances());
		
		return drugRepository.save(drugDB);
	}

	@Override
	@Transactional(readOnly = false)
	public Drug delete(Long id) {
		Drug drugDB = drugRepository.findOne(id);
		if(drugDB == null)
			return null;
		drugRepository.delete(id);
		return drugDB;
	}

}
