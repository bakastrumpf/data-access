package com.iktpreobuka.dataaccess.services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import com.iktpreobuka.dataaccess.entities.AddressEntity;

@Service
public class AddressDAOImpl implements AddressDAO {
	
	// ne odgovara nam @Autowired jer on samo instancira objekat i vraća ga
	// @PersistenceContext dodatno pravi konekciju ka bazi i ubacuje sve iz APPLICATION PROPERTIES
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<AddressEntity> findAddressByUsername(String name){
		
		// TODO napravi HQL upit koji pronalazi adresu na kojoj živi korisnik s datim imenom
		// SELECT * FROM AddressEntity a LEFT JOIN UserEntity u ON a.id == u.address WHERE name u.name = :name
		
		// String sql = "SELECT a FROM AddressEntity a LEFT JOING FETCH a.users u WHERE u.name = " + name;
		String sql = "SELECT a FROM AddressEntity a"
				// HQL ne zna za kolone u tabeli već samo za reference na objekte
				// a pošto u AddressEntity postoji spisak USERS kao lista
				// pišemo a.users da bismo izvukli USERS iz ADDRESS ENTITY
				+ " LEFT JOIN FETCH a.users u "
				// prednosti :name 
				+ "WHERE u.name = :name";
		
		// TODO pozovi HQL upit
		Query query = em.createQuery(sql);
		
		// treba podesiti parametar NAME
		query.setParameter("name", name);
		
		List<AddressEntity> retVals = query.getResultList();
		
		// TODO obradi vraćene podatke i odradi return
		// return null;
		return retVals;
	}

//	@Override
//	public void save(AddressEntity address) {
//	}
//
//	@Override
//	public Iterable<AddressEntity> findAll() {
//		return null;
//	}
	
	
}