package br.com.alura.financas.teste;

import javax.persistence.EntityManager;

import br.com.alura.financas.modelo.Conta;
import br.com.alura.financas.util.JPAUtil;

public class TesteBuscaConta {

	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		//Qualquer alteração na entitidade conta no final da transação irá ser alterado no DB
		Conta conta = em.find(Conta.class, 1);
		
		conta.setTitular("Manuel da Silva");
		
		System.out.println(conta.getTitular());
		
		em.getTransaction().commit();
		em.close();
	}

}
