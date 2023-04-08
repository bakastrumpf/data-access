package com.iktpreobuka.dataaccess.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.dataaccess.entities.AddressEntity;

public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {

	public List<AddressRepository> findAllByCity(String city);

	public List<AddressRepository> findAllByCountryOrderByCityAsc(String country);
	
	// ? public List<AddressEntity> findByName(String name);
	
	// Boolean existsByStreetAndCityAndCountry(String street, String city, String country);
	
	// AddressEntity findByStreet(String street);

}
