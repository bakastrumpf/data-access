package com.iktpreobuka.dataaccess.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CountryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "country_name")
	private String name;
	
	@Column(name = "city_name")
	private CityEntity city;

	
	public CountryEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CountryEntity(Integer id, String name, CityEntity city) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public CityEntity getCity() {
		return city;
	}


	public void setCity(CityEntity city) {
		this.city = city;
	}
	
	
	

}
