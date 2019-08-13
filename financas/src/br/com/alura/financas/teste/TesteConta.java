package br.com.alura.financas.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.financas.modelo.Conta;

public class TesteConta {
	public static void main(String[] args) {
		Conta conta = new Conta("Manuel", "123", "1234", "Quebrado");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("financas");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(conta);
		em.getTransaction().commit();

		em.close();
		emf.close();
	}
}
