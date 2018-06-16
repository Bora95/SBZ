package com.sbz.projekat.SBZProjekat.user;

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

@RestController
@RequestMapping("api/user")
public class UserController {
	
	@Autowired
	private HttpSession session;
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<User> get() {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAll() {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		List<User> users = userService.findAll();
		if(users == null || users.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<User> add(@RequestBody @Valid User input) {
		User user = (User) session.getAttribute("user");
		if(user == null || !user.getType().equals(User.Type.ADMIN))
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		input = userService.add(input);
		if(input == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(input, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<User> login(@RequestBody User input) {
		 User user = userService.login(input.getUsername(), input.getPassword());
		 if(user == null)
			 return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		 
		 session.setAttribute("user", user);
		 return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<User> logout() {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		session.invalidate();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id:\\d+}")
	public ResponseEntity<User> delete(@PathVariable Long id) {
		User user = (User) session.getAttribute("user");
		if(user == null || !user.getType().equals(User.Type.ADMIN))
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		User userDelete;
		try {
			userDelete = userService.delete(id);
			if(userDelete == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<>(userDelete, HttpStatus.OK);
	}
	
}
