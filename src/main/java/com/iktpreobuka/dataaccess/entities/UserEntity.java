package com.iktpreobuka.dataaccess.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/*
• 2.1 unaprediti UserEntity tako da ima datum rođenja, broj telefona, JMBG, broj lične karte

• odabrati odgovarajuće Hibernate anotacije i njihove parametre za svako od ovih polja
• 2.2 omogućiti pronalaženje korisnika po datumu rođenja sortiranih u rastućem redosledu imena
• putanja /by-dob
• 2.3* omogućiti pronalaženje različitih imena korisnika po prvom slovu imena
• putanja /by-name-first-letter
 */

@Entity
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String email;
	private Date datumRodjenja;
	private String brTel;
	private String JMBG;
	private String brLK;
	
	@ManyToOne (cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "address")
	private AddressEntity address;
	
	public UserEntity() {
		super();
	}

	public UserEntity(Integer id, String name, String email, Date datumRodjenja, String brTel, String jMBG, String brLK,
			AddressEntity address) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.datumRodjenja = datumRodjenja;
		this.brTel = brTel;
		JMBG = jMBG;
		this.brLK = brLK;
		this.address = address;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public String getBrTel() {
		return brTel;
	}

	public void setBrTel(String brTel) {
		this.brTel = brTel;
	}

	public String getJMBG() {
		return JMBG;
	}

	public void setJMBG(String jMBG) {
		JMBG = jMBG;
	}

	public String getBrLK() {
		return brLK;
	}

	public void setBrLK(String brLK) {
		this.brLK = brLK;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}
}