package br.com.alura.financas.teste;

import javax.persistence.EntityManager;

import br.com.alura.financas.modelo.Conta;
import br.com.alura.financas.util.JPAUtil;

public class TesteConta {
	public static void main(String[] args) {
		Conta conta = new Conta("Manuela Santos", "123", "1234", "Quebrado");

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();
		//A partir do persist a entidade passa de Transient > Managed sendo assim
		//Qualquer alteração na entitidade conta no final da transação irá ser alterado no DB
		em.persist(conta);
		
		conta.setBanco("Bradesco");
		
		em.getTransaction().commit();
		em.close();
	}
}
