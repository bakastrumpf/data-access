package com.iktpreobuka.dataaccess.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.dataaccess.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
	
	public UserEntity findByEmail(String email);
	public List<UserEntity> findByNameOrderByDateOfBirthAsc(String name);
	public List<UserEntity> findByDateOfBirthOrderByNameAsc(LocalDate dateOfBirth);
	public List<UserEntity> findByNameStartsWith(String firstLetter);

}