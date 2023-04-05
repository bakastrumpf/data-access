package com.iktpreobuka.dataaccess.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.dataaccess.entities.AddressEntity;

public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {

	List<AddressRepository> findAllByCity(String city);

	List<AddressRepository> findAllByCountryOrderByCityAsc(String country);
	
	// Boolean existsByStreetAndCityAndCountry(String street, String city, String country);
	
	// AddressEntity findByStreet(String street);

}
