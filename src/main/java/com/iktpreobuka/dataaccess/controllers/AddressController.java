package com.iktpreobuka.dataaccess.controllers;

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
import com.iktpreobuka.dataaccess.repositories.AddressRepository;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@RequestMapping(method = RequestMethod.POST)
	public AddressEntity createAddress(@RequestParam String street, 
										@RequestParam String city, 
										@RequestParam String country) {
		AddressEntity address = new AddressEntity(street, city, country);
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
	
//	@RequestMapping(method = RequestMethod.GET, path = "/user/{name}")
//	public List<AddressEntity> findAddressByUsername(@PathVariable String name){
//		return ((AddressRepository) addressRepository).findAddressByUsername(name);
//	}
	
	/*
• 1.1 popuniti bazu podataka sa podacima o deset adresa
• 1.2 u potpunosti omogućiti rad sa adresama
• vraćanje adrese po ID
• ažuriranje adrese
• brisanje adrese
• 1.3 omogućiti pronalaženje adrese po gradu
• putanja /by-city
• 1.4 omogućiti pronalaženje adrese po državi
• vraćanje adresa sortiranih po rastućoj vrednosti države
• putanja /by-country
	 */
	
	/*
• 2.2 u AddressController dodati REST entpoint-e za dodavanje i brisanje korisnika u adresama
*/
	
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
	
	@GetMapping("/by-id")
	public AddressEntity getById(@RequestParam Integer addressId) {
		return addressRepository.findById(addressId).get();
	}
	
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
	}

}
