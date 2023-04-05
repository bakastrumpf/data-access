package com.iktpreobuka.dataaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.dataaccess.entities.CountryEntity;

public interface CountryRepository extends CrudRepository<CountryEntity, Integer> {

}
