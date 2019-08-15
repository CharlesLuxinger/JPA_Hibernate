package br.com.alura.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.alura.model.Loja;

@Repository
public class LojaDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Loja> getLojas() { 
		TypedQuery<Loja> query = em.createQuery("from Loja", Loja.class);
		
		return query.getResultList();
	}

	public Loja getLoja(Integer lojaId) {
		return em.find(Loja.class, lojaId);
	}

}
