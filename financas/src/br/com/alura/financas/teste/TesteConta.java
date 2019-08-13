package br.com.alura.financas.teste;

import javax.persistence.EntityManager;

import br.com.alura.financas.modelo.Conta;
import br.com.alura.financas.util.JPAUtil;

public class TesteConta {
	public static void main(String[] args) {
		Conta conta = new Conta("Manuel", "123", "1234", "Quebrado");

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();
		em.persist(conta);
		em.getTransaction().commit();

		em.close();
	}
}
