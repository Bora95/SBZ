package com.sbz.projekat.SBZProjekat.resoner;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbz.projekat.SBZProjekat.disies.Disies;
import com.sbz.projekat.SBZProjekat.disies.DisiesService;
import com.sbz.projekat.SBZProjekat.user.User;

@RestController
@RequestMapping("api/resoner")
public class ResonerController {

	@Autowired
	private HttpSession session;
	@Autowired
	private ResonerService resonerService;
	@Autowired
	private DisiesService disiesService;
	
	@PostMapping
	public ResponseEntity<Disies> getDiganose(@RequestBody @Valid ResonerDTO input) {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		Disies ret = resonerService.diagnose(input.getSymptoms(), input.getType(), input.getJmbg());
		if(ret == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@PostMapping("/get-disies")
	public ResponseEntity<List<Disies>> getDisies(@RequestBody Set<Long> input) {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		List<Disies> ret = disiesService.findDisieses(input);
		if(ret == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
}
