package com.sbz.projekat.SBZProjekat.diagnose;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbz.projekat.SBZProjekat.disies.DisiesService;
import com.sbz.projekat.SBZProjekat.drug.DrugService;
import com.sbz.projekat.SBZProjekat.user.User;
import com.sbz.projekat.SBZProjekat.user.UserService;

@Transactional(readOnly = true)
@Service
public class DiagnoseServiceImpl implements DiagnoseService{

	@Autowired
	private DiagnoseRepository diagnoseRepository;
	@Autowired
	private DisiesService disiesService;
	@Autowired
	private DrugService drugService;
	@Autowired
	private UserService userService;
	
	@Override
	public Diagnose findOne(Long id) {
		return diagnoseRepository.findOne(id);
	}

	@Override
	public List<Diagnose> findAll() {
		return diagnoseRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public Diagnose add(DiagnoseDTO input) {
		Diagnose save = new Diagnose(disiesService.findOne(input.getDisies()), null, userService.findOne(input.getDoctor(), User.Type.DOCTOR));
		for(Long drug : input.getDrugs()) {
			save.getDrugs().add(drugService.findOne(drug));
		}
		return diagnoseRepository.save(save);
	}
	
	@Override
	@Transactional(readOnly = false)
	public Diagnose add(Diagnose input) {
		return diagnoseRepository.save(input);
	}

	@Override
	@Transactional(readOnly = false)
	public Diagnose edit(Diagnose input) {
		if(input.getId() == null)
			return null;
		Diagnose diagnoseDB = diagnoseRepository.findOne(input.getId());
		if(diagnoseDB == null)
			return null;
		
		if(input.getDisies() != null)
			diagnoseDB.setDisies(input.getDisies());
		if(input.getDrugs() != null && !input.getDrugs().isEmpty())
			diagnoseDB.setDrugs(input.getDrugs());
		if(input.getDoctor() !=null)
			diagnoseDB.setDoctor(input.getDoctor());
		
		return diagnoseRepository.save(diagnoseDB);
	}

	@Override
	@Transactional(readOnly = false)
	public Diagnose delete(Long id) {
		Diagnose diagnoseDB = diagnoseRepository.findOne(id);
		if(diagnoseDB == null)
			return null;
		diagnoseRepository.delete(id);
		return diagnoseDB;
	}

}
