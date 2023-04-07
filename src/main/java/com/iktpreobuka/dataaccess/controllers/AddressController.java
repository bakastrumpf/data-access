package com.iktpreobuka.dataaccess.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.dataaccess.entities.AddressEntity;
import com.iktpreobuka.dataaccess.entities.CityEntity;
import com.iktpreobuka.dataaccess.entities.UserEntity;
import com.iktpreobuka.dataaccess.repositories.AddressRepository;
import com.iktpreobuka.dataaccess.repositories.CityRepository;
import com.iktpreobuka.dataaccess.repositories.UserRepository;
import com.iktpreobuka.dataaccess.services.AddressDAO;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AddressDAO addressService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@RequestMapping(method = RequestMethod.POST)
	public AddressEntity createAddress(@RequestParam String street, 
										@RequestParam CityEntity city, 
										@RequestParam String country) {
		AddressEntity address = new AddressEntity(null, street, city, country, null, null);
//		address.setCity(street);
//		address.setCountry(city);
//		address.setStreet(country);		
		return addressRepository.save(address);
	}
	
	@GetMapping()
	//@RequestMapping(method = RequestMethod.GET)
	public Iterable<AddressEntity> getAll(){
		//return (List<AddressEntity>) addressRepository.findAll();
		return addressRepository.findAll();
	}
	
	// metoda koja prolazi kroz sve adrese, u kojima su liste korisnika
	// i vraća samo adrese na kojima živi korisnik s korisničkim imenom {name}
	// treba nam servis koji će to da radi za nas 
	// @Autowired AddressDAO... da bismo koristili njegovu metodu findAddressByUsername
	@RequestMapping(method = RequestMethod.GET, path = "/user/{name}")
	public List<AddressEntity> findAddressByUsername(@PathVariable String name){
		// return null;
		return addressService.findAddressByUsername(name);
	}
	
	/*
		• 1.1 popuniti bazu podataka sa podacima o deset adresa
		• 1.2 u potpunosti omogućiti rad sa adresama
		• vraćanje adrese po ID
		• ažuriranje adrese
		• brisanje adrese
	 */
	
	@GetMapping("/fing-by-id") // zašto se traži da tip bude OPTIONAL?
	public AddressEntity getById(@RequestParam Integer addressId) {
		return addressRepository.findById(addressId).get();
	}
	
	
//	@GetMapping("/by-id")
//	public AddressEntity getById(@RequestParam Integer addressId) {
//		return addressRepository.findById(addressId).get();
//	}
	
	
	@PutMapping("/{id}")
	public AddressEntity changeAdress(@PathVariable Integer id, @RequestBody AddressEntity modifiedAddress) {
		AddressEntity address = addressRepository.findById(id).get();
		if(modifiedAddress.getStreet() != null)
			address.setStreet(modifiedAddress.getStreet());
		return addressRepository.save(address);
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Integer id) {
		addressRepository.deleteById(id);
		// dobra je praksa da se vrati obrisani objekat
		// return STH;
		
	}
	
	/*
	• 1.3 omogućiti pronalaženje adrese po gradu
	• putanja /by-city
	
	*/
	
	@GetMapping("/by-city/{city}")
	public List<AddressRepository> findAllByCity(@PathVariable String city) {
		return addressRepository.findAllByCity(city);
	}
	
	
	
	/*
	• 1.4 omogućiti pronalaženje adrese po državi
	• vraćanje adresa sortiranih po rastućoj vrednosti države
	• putanja /by-country
	*/
	
	@GetMapping("/by-country/{country}")
	public List<AddressRepository> findAllByCountryOrderByCityAsc(@PathVariable String country) {
		return addressRepository.findAllByCountryOrderByCityAsc(country);
	}
	
	

	// 2.2 u AddressController dodati REST entpoint-e za dodavanje i brisanje korisnika u adresama

	@PutMapping("/{id}/addUser")
	public AddressEntity addUser(@PathVariable Integer id, @RequestParam Integer userId) {
		AddressEntity address = addressRepository.findById(id).get();
		UserEntity user = userRepository.findById(userId).get();
		address.getUsers().add(user);
		address.setNumOfUsers(address.getUsers().size());
		return address;
	}
	
	@GetMapping("/{id}/removeUser")
	public AddressEntity removeUser(@PathVariable Integer id, @RequestParam Integer userId) {
		AddressEntity address = addressRepository.findById(id).get();
		UserEntity user = userRepository.findById(userId).get();
		address.getUsers().remove(user);
		address.setNumOfUsers(address.getUsers().size());
		return address;
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/{id}/city")
	public AddressEntity addCity(@PathVariable Integer id, @RequestParam Integer cityId) {
		AddressEntity address = addressRepository.findById(id).get();
		CityEntity city = cityRepository.findById(cityId).get();
		address.setCity(city);
		return addressRepository.save(address);
	}
	
	/*
• 3.1* državu i grad izdvojiti kao posebne entitete i povezati sa
adresom
• jedna adresa pripada tačno jednom gradu
• jedan grad može imati više adresa
• jedan grad pripada tačno jednoj državi
• jedna država može imati više gradova
• 3.2* za svaki od entiteta napraviti REST kontrolere
• koji podržavaju standardne CRUD operacije
	 */
	

	

}
