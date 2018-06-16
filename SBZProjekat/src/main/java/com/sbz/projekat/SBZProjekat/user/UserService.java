package com.sbz.projekat.SBZProjekat.user;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public interface UserService {
	
	public User findOne(@NotNull Long id);
	
	public User findOne(@NotNull Long id, @NotNull User.Type type);
	
	public List<User> findAll();
	
	public User add(@NotNull User input);
	
	public User edit(@NotNull User input);
	
	public User delete(@NotNull Long id) throws Exception;

	public User login(@NotBlank String username, @NotBlank String password);
}
