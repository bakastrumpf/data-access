package com.iktpreobuka.dataaccess.controllers;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.dataaccess.entities.AddressEntity;
import com.iktpreobuka.dataaccess.entities.UserEntity;
import com.iktpreobuka.dataaccess.repositories.AddressRepository;
import com.iktpreobuka.dataaccess.repositories.UserRepository;

@RestController
@RequestMapping("/api/v1/users")
//@RequestMapping("/api/v2/users") v1 ide pre users jer...

public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@RequestMapping(method = RequestMethod.POST)
	public UserEntity createUser(@RequestParam String name,	@RequestParam String email) {
		UserEntity user = new UserEntity();
		user.setEmail(email);
		user.setName(name);
		// return null;
	//	UserEntity retUser = userRepository.save(user);
		userRepository.save(user);
	//	return retUser;
		return user;
		
	}
	
	@RequestMapping
	// podrazumevane vrednosti:
	// @RequestMapping(method = RequestMethod.GET)
	// @RequestMapping(method = RequestMethod.GET, value = "")
	public List<UserEntity> getAll(){
		return (List<UserEntity>) userRepository.findAll();
	}
	
	
	@RequestMapping(method = RequestMethod.PUT, path = "/{id}/address")
	public UserEntity addAddress(@PathVariable Integer id, 
			@RequestParam Integer addressId) {
		// klasa Optional koja  nam pomaže da se odbranimo od vrednosti NULL:
		// pomoću .get() kažemo daj mi vrednost koja god da je, pa i ako je NULL
		// trenutno nećemo komplikovati više od toga
		UserEntity user = userRepository.findById(id).get();
		AddressEntity address = addressRepository.findById(addressId).get();
		user.setAddress(address);
		userRepository.save(user);
		return user;
		// ili
		// return userRepository.save(user);
	}
	
	
	
	//1.3 omogućiti pronalaženje korisnika po email adresi
	// putanja /by-email
//	@RequestMapping(method = RequestMethod.GET, path = "/by-email")
//	public List<UserEntity> findByEmail(@RequestParam String emailId){
//		UserEntity email = userRepository.findByEmail(emailId).get();
//		return email;
//	}
	
	/*@RequestMapping(method = RequestMethod.PUT, path = "/{id}/email")
	public UserEntity addEmail(@PathVariable Integer id, 
			@RequestParam Integer emailId) {
		UserEntity user = userRepository.findById(id).get();
		UserEntity email = userRepository.findById(emailId).get();
	?	user.setEmail(email);
		return userRepository.save(user);
	}
	*/
	
	// 1.4 omogućiti pronalaženje korisnika po imenu
	// vraćanje korisnika sortiranih po rastućoj vrednosti emaila
	// putanja /by-name
	
	/*
• 1.1 popuniti bazu podataka sa podacima o deset osoba
• 1.2 u potpunosti omogućiti rad sa korisnicima
• vraćanje korisnika po ID
• ažuriranje korisnika
• brisanje korisnika
• 1.3 omogućiti pronalaženje korisnika po email adresi
• putanja /by-email
• 1.4 omogućiti pronalaženje korisnika po imenu
• vraćanje korisnika sortiranih po rastućoj vrednosti emaila
• putanja /by-name
	 */
	
		
	@GetMapping("/by-name")
	public List<UserEntity> findByNameOrderByDateOfBirthAsc(@RequestParam String name) {
		return userRepository.findByNameOrderByDateOfBirthAsc(name);
	}
	
	@GetMapping("/by-dob")
	public List<UserEntity> findByDateOfBirthOrderByNameAsc(@RequestParam LocalDate dateOfBirth) {
		return userRepository.findByDateOfBirthOrderByNameAsc(dateOfBirth);
	}
	
	@GetMapping("/by-name-first-letter")
	public List<UserEntity> findByNameStartsWith(@RequestParam String firstLetter) {
		return userRepository.findByNameStartsWith(firstLetter);
	}
	
	
	// TODO 2.1 dodati REST entpoint u UserController koji omogućava uklanjanje adrese iz entiteta korisnika

	
	
	
	/* 	• 2.3* dodati REST entpoint u UserController koji omogućava prosleđivanje parametara za kreiranje korisnika i adrese
		• kreira korisnika
		• proverava postojanje adrese
		• ukoliko adresa postoji u bazi podataka dodaje je korisniku
		• ukoliko adresa ne postoji, kreira adresu i dodaje je korisniku
	 */
	
	@PostMapping("/with-address")
	// moglo je i bez ovoliko RequestParam
	// umesto toga, mapiramo u JSONu već napisan UserEntity
	// u RequestBody pošaljemo JSON UserEntity
	public UserEntity newUserWithAddress(@RequestParam String name, 
										@RequestParam String email,
										@RequestParam Date dateOfBirth, 
										@RequestParam String brTel, 
										@RequestParam String jMBG, 
										@RequestParam String brLK, 
										@RequestParam Integer addressId, 
										@RequestParam String street) {
		UserEntity user = new UserEntity();
		user.setName(name);
		user.setEmail(email);
		user.setDatumRodjenja(dateOfBirth);
		user.setBrTel(brTel);
		user.setJMBG(jMBG);
		user.setBrLK(brLK);
		AddressEntity adresa = addressRepository.findById(addressId).orElseGet(() -> {
			AddressEntity novaAdresa = new AddressEntity();
				novaAdresa.setStreet(street);
				addressRepository.save(novaAdresa);
				user.setAddress(novaAdresa);
				return novaAdresa;
			}
		);
		if(adresa != null) {
			user.setAddress(adresa);
 		} else {
 			// TODO
 		}
		
		/* druga mogućnost:
		 * AddressEntity adresa = addressRepository.findById(addressId).get();
			if(adresa != null) {
				user.setAddress(adresa);
	 		} else {
	 			AddressEntity novaAdresa = new AddressEntity();
	 			novaAdresa.setStreet(street);
	 			addressRepository.save(novaAdresa);
	 			user.setAddress(novaAdresa);
	 		}
		 */
		
		/* treća mogućnost:
		 * user.setBrLK(brLK);
		 * AddressEntity address;
		 * if(!addressRepository.existsById(addressId){
		 * 		address = new AddressEntity();
	 			address.setStreet(street);
	 			addressRepository.save(address);
	 			user.setAddress(address);
		 * } else {
		 * 		address = addressRepository.findById(addressId).get();
		 * }
		 */
		UserEntity retUser = userRepository.save(user);
		return retUser;
		}
	
	/*  četvrta mogućnost
	 * @RequestMapping(method = RequestMethod.POST, value = "/create-user-with-address")
	 * public UserEntity newUserWithAddress(@RequestParam String name, 
										@RequestParam String email,
										@RequestParam Date dateOfBirth, 
										@RequestParam String brTel, 
										@RequestParam String jMBG, 
										@RequestParam String brLK, 
										@RequestParam Integer addressId, 
										@RequestParam String street, 
										@RequestParam String city,
										@RequestParam String country) {
										
		// trenutno radi  s pozivanjem endpointa, ali ne bi trebalo tako raditi
		// kasnije će logika biti u servisu, pa ćemo svakako menjati ovaj kod 
		UserEntity user = createUser(name, email,  dateOfBirth, brTel, jMBG, brLK, addressId, street);
		
		Boolean exists = addressRepository.existsByStreetAndCityAndCountry(street, city, country);
		
		// predlog: pribaviti objekat i proveriti da li je null
		// ako je null, onda ga praviti (orElseGet)
		if(exists) {
			AddressEntity adre = addressRepository.findByStreet(street);
			user.setAddress(adre);
 		} else {
 			AddressEntity adre = new AddressEntity(street,  city, country);
 			user.setAddress(adre);
 			addressRepository.save(adre);
 		}
 		
 		userRepository.save(user);
 		
 		return user;
	 * 
	 */
	
	// zadaci nakon UPLOAD / EMAIL
	
	//• 1.1 omogućiti upload fajla sa listom korisnika gde svaki red u fajlu sadrži podatke za jednog korisnika (ime i email), 
	//gde su podaci delimitirani zarezom

	//• 1.2 nakon što je fajl sa korisnicima upload-ovan omogućiti čuvanje svih korisnika koji se nalaze u fajlu

	//• 1.3 Prilikom dodavanja novog korisnika ili izmene postojećeg korisnika omogućiti proveru da li je prosleđena mail adresa
	//korisnika već uneta u bazu podataka. 
	//Ukoliko jeste zabraniti unos ili izmenu

	//• 1.4 u klasu UserEntity dodati polje troškovi. 
	//Prilikom unosa novog ili izmene postojećeg korisnika polje troškovi postaviti na vrednost 5000 ukoliko korisnik živi u Novom Sadu, 
	//ili 10000 ukoliko korisnik živi u Beogradu. U svim ostalim situacijama upisati vrednost 0 u polje troškovi

	//• 2.1 Kreirati REST endpoint koji omogućuje download fajla

	//• 2.2 Kreirati REST endpoint koji omogućuje downolad fajla u kome se nalaze podaci o svim korisnicima koji se nalaze u bazi podataka. 
	//Fajl je potrebno prvo napraviti na serverskoj strani tako da su podaci o korisnicima razdvojeni zarezom (csv fajl)

	//• 2.3 Proširiti zadatak 2.2 tako da REST endpoint prima listu svih atributa klase UserEntity koje korisnik želi da se nalaze u csv fajlu.
	//Ukoliko se u listi nalazi atribut koji nije deo UserEntity kase, vratiti odgovarajuću grešku korisniku. Ukoliko je prosleđena prazna lista, 
	//u fajl upisati sve atribute klase UserEntity

	//• 2.4 Napraviti endpoint za slanje MIME email-a sa attachment-om, 
	//gde ce parametri email-a (to, subject, text) fajl koji je attachment biti prosledjeni endpoint-u.
	
}
