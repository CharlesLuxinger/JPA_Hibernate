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
		// Add Join Fetch faz o select de movimentacoes junto como de conta
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
		
	}
}
