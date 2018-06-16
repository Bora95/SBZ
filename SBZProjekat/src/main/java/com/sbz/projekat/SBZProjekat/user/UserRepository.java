package com.sbz.projekat.SBZProjekat.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbz.projekat.SBZProjekat.user.User.Type;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsername(String username);

	public User findByIdAndType(Long id, Type type);
}
