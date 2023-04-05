package com.iktpreobuka.dataaccess.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.dataaccess.entities.AddressEntity;
import com.iktpreobuka.dataaccess.entities.CityEntity;
import com.iktpreobuka.dataaccess.entities.CountryEntity;

public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {

	List<AddressRepository> findAllByCity(CityEntity city);

	List<AddressRepository> findAllByCountryOrderByCityAsc(CountryEntity country);

}
