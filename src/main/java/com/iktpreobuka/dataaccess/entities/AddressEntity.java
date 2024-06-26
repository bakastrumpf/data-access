package com.iktpreobuka.dataaccess.entities;
// as of May 2024, db annotations no longer work
import java.util.List;

import javax.persistence.CascadeType;

//import java.util.List;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AddressEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false)
	private String street;
	
	@Column(nullable = false)
	private CityEntity city;
	
	@Column(nullable = false)
	private String country;
		
	@OneToMany(mappedBy = "address", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<UserEntity> users;
	
	@Column(name = "users")
	private Integer numOfUsers;	
	
	@Version
	private Integer version;

	
	public AddressEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


	public AddressEntity(Integer id, String street, CityEntity city, String country, List<UserEntity> users,
			Integer numOfUsers) {
		super();
		this.id = id;
		this.street = street;
		this.city = city;
		this.country = country;
		this.users = users;
		this.numOfUsers = numOfUsers;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public CityEntity getCity() {
		return city;
	}


	public void setCity(CityEntity city) {
		this.city = city;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public List<UserEntity> getUsers() {
		return users;
	}


	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}


	public Integer getNumOfUsers() {
		return numOfUsers;
	}


	public void setNumOfUsers(Integer numOfUsers) {
		this.numOfUsers = numOfUsers;
	}


	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
	
	
	
}
