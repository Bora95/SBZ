package com.sbz.projekat.SBZProjekat.user;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbz.projekat.SBZProjekat.user.User.Type;

@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findOne(@NotNull Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public User add(@NotNull User input) {
		if(userRepository.findByUsername(input.getUsername()) != null)
			return null;
		
		return userRepository.save(input);
	}

	@Override
	@Transactional(readOnly = false)
	public User edit(@NotNull User input) {
		if(input.getId() == null)
			return null;
		User userDB = userRepository.findOne(input.getId());
		if(userDB == null)
			return null;
		
		if(input.getFirstName() != null)
			userDB.setFirstName(input.getFirstName());
		if(input.getLastName() != null)
			userDB.setLastName(input.getLastName());
		if(input.getPassword() != null)
			userDB.setPassword(input.getPassword());
		if(input.getType() != null)
			userDB.setType(input.getType());
		
		return userRepository.save(userDB);
	}

	@Override
	@Transactional(readOnly = false)
	public User delete(@NotNull Long id) {
		User userDB = userRepository.findOne(id);
		userRepository.delete(id);
		return userDB;
	}

	@Override
	public User login(@NotBlank String username, @NotBlank String password) {
		User userDB = userRepository.findByUsername(username);
		if(userDB == null || !userDB.getPassword().equals(password))
			return null;
		
		return userDB;
	}

	@Override
	public User findOne(Long id, Type type) {
		return userRepository.findByIdAndType(id, type);
	}

}
