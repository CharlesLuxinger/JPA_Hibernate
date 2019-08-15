package br.com.alura.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.alura.financas.modelo.Conta;
import br.com.alura.financas.util.JPAUtil;

public class TestesTodasMovimentacoesContas {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		// Para evitar o comportamento Lazy(N+1 para cada select de conta 1 select de movimentacoes
		// Add Join Fetch(Inner Join) faz o select de movimentacoes junto como de conta
		// Mas trás apenas as contas que possue movimentacao
		String jpql = "select c from Conta c join fetch c.movimentacoes";
		
		Query query = em.createQuery(jpql);
		
		List<Conta> todasContas = query.getResultList();
		
		todasContas.forEach(x ->{
			System.out.println("Titular: " + x.getTitular());
			System.out.println("Movimentaçoes: ");
			System.out.println(x.getMovimentacoes());
		});
		
		em.getTransaction().commit();
		em.close();
		
		
		EntityManager em2 = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		// é necessário add o left join para trazer tds as contas msm as que não possuem movimentacao
		String jpql2 = "select c from Conta c left join fetch c.movimentacoes";
		
		Query query2 = em.createQuery(jpql2);
		
		List<Conta> todasContas2 = query2.getResultList();
		
		todasContas2.forEach(x ->{
			System.out.println("Titular: " + x.getTitular());
			System.out.println("Movimentaçoes: ");
			System.out.println(x.getMovimentacoes());
		});
		
		em2.getTransaction().commit();
		em2.close();
		
	}
}
