package br.com.alura.financas.teste;

import javax.persistence.EntityManager;

import br.com.alura.financas.modelo.Cliente;
import br.com.alura.financas.modelo.Conta;
import br.com.alura.financas.util.JPAUtil;

public class TesteContaCliente {
	public static void main(String[] args) {

		Conta conta1 = new Conta();
		conta1.setId(1);

		Cliente cli1 = new Cliente("Maria dos Santos", "Cabelereira", "Rua Sem Fim", conta1);

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		em.persist(cli1);

		em.getTransaction().commit();
		em.close();
	}
}
