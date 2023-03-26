package com.iktpreobuka.dataaccess.services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import com.iktpreobuka.dataaccess.entities.AddressEntity;

@Service
public class AddressDAOImpl implements AddressDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<AddressEntity> findAddressByUsername(String name){
		// TODO napravi SQL upit 
		// SELECT * FROM AddressEntity a LEFT JOIN UserEntity u ON a.id == u.addressId WHERE name u.name = :name
		String sql = "SELECT a FROM AddressEntity a"
				+ " LEFT JOIN FETCH a.users u "
				+ "WHERE u.name = :name";
		// TODO pozovi HQL upit
		Query query = em.createQuery(sql);
		query.setParameter("name", name);
		
		List<AddressEntity> retVals = query.getResultList();
		// TODO obradi vraćene podatke i obradi return
		return retVals;
	}

	@Override
	public void save(AddressEntity address) {
	}

	@Override
	public Iterable<AddressEntity> findAll() {
		return null;
	}
}