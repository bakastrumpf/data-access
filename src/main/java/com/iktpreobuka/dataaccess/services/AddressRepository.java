package com.iktpreobuka.dataaccess.services;

import java.util.List;
import com.iktpreobuka.dataaccess.entities.AddressEntity;

public interface AddressRepository {
	public List<AddressEntity> findAddressByUsername (String name);

	public void save(AddressEntity address);

	public Iterable<AddressEntity> findAll();
	
}