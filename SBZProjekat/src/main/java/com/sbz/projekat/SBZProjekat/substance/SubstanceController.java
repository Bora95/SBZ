package com.sbz.projekat.SBZProjekat.substance;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbz.projekat.SBZProjekat.user.User;

@RestController
@RequestMapping("api/substance")
public class SubstanceController {
	
	@Autowired
	private HttpSession session;
	@Autowired
	private SubstanceService substanceService;
	
	@GetMapping
	public ResponseEntity<List<Substance>> getAll() {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		List<Substance> ret = substanceService.findAll();
		if(ret == null || ret.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Substance> add(@RequestBody @Valid Substance input) {
		User user = (User) session.getAttribute("user");
		if(user == null || !user.getType().equals(User.Type.ADMIN))
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		Substance s = substanceService.add(input);
		if(s == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(s, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id:\\d+}")
	public ResponseEntity<Substance> delete(@PathVariable Long id) {
		User user = (User) session.getAttribute("user");
		if(user == null || !user.getType().equals(User.Type.ADMIN))
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		 Substance delete;
		try {
			delete = substanceService.delete(id);
			if(delete == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<>(delete, HttpStatus.OK);
	}
	
}
