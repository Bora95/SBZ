package com.sbz.projekat.SBZProjekat.medicalRecord;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbz.projekat.SBZProjekat.diagnose.DiagnoseDTO;
import com.sbz.projekat.SBZProjekat.user.User;

@RestController
@RequestMapping("api/record")
public class MedicalRecordController {
	
	@Autowired
	private HttpSession session;
	@Autowired
	private MedicalRecordService medicalRecordService;
	
	@GetMapping
	public ResponseEntity<List<MedicalRecord>> getAll() {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		List<MedicalRecord> ret = medicalRecordService.findAll();
		if(ret == null || ret.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@GetMapping("/{jmbg:(0[1-9]|[1-2][0-9]|31(?!(?:0[2469]|11))|30(?!02))(0[1-9]|1[0-2])([09][0-9]{2})([0-8][0-9]|9[0-6])([0-9]{3})(\\d)$}")
	public ResponseEntity<MedicalRecord> getOne(@PathVariable String jmbg) {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		MedicalRecord ret = medicalRecordService.findOne(jmbg);
		if(ret == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<MedicalRecord> add(@RequestBody @Valid MedicalRecordDTO input) {
		User user = (User) session.getAttribute("user");
		if(user == null || !user.getType().equals(User.Type.DOCTOR))
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		MedicalRecord s = medicalRecordService.add(input);
		if(s == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(s, HttpStatus.OK);
	}
	
	@PutMapping("/{jmbg:(0[1-9]|[1-2][0-9]|31(?!(?:0[2469]|11))|30(?!02))(0[1-9]|1[0-2])([09][0-9]{2})([0-8][0-9]|9[0-6])([0-9]{3})(\\d)$}")
	public ResponseEntity<MedicalRecord> addDiagnose(@RequestBody @Valid DiagnoseDTO input, @PathVariable String jmbg) {
		User user = (User) session.getAttribute("user");
		if(user == null || !user.getType().equals(User.Type.DOCTOR))
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		MedicalRecord s = medicalRecordService.addDiagnose(input, jmbg);
		if(s == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(s, HttpStatus.OK);
	}
	
	@DeleteMapping("/{jmbg:(0[1-9]|[1-2][0-9]|31(?!(?:0[2469]|11))|30(?!02))(0[1-9]|1[0-2])([09][0-9]{2})([0-8][0-9]|9[0-6])([0-9]{3})(\\d)$}")
	public ResponseEntity<MedicalRecord> delete(@PathVariable String jmbg) {
		User user = (User) session.getAttribute("user");
		if(user == null || !user.getType().equals(User.Type.ADMIN))
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		 MedicalRecord delete;
		try {
			delete = medicalRecordService.delete(jmbg);
			if(delete == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<>(delete, HttpStatus.OK);
	}
	
}
