package br.com.alura.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.alura.financas.enums.TipoMovimentacao;
import br.com.alura.financas.modelo.Conta;
import br.com.alura.financas.modelo.Movimentacao;
import br.com.alura.financas.util.JPAUtil;

public class TesteJPQL {
	@SuppressWarnings({ "unchecked"})
	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
	
		//Sempre utilizar letras minusculas nos atributos, o hibernate não reconhece maisculas.
		// Apenas o nome da Classe(tabela) deve ser extamente a msm.
		Query query = em.createQuery("SELECT mov FROM Movimentacao mov Where mov.conta.id = 2");

		List<Movimentacao> result = query.getResultList();

		result.forEach(m -> {
			System.out.println("Descricão: " + m.getDescricao());
			System.out.println("Conta.id: " + m.getConta().getId());
		});

		em.getTransaction().commit();
		em.close();
		
		//Comparar objetos
		EntityManager em2 = new JPAUtil().getEntityManager();
		em2.getTransaction().begin();
		
		Conta conta = new Conta();
		conta.setId(2);
		
		Query query2 = em2.createQuery("SELECT mov FROM Movimentacao mov WHERE mov.conta = :pConta "
				+ "AND mov.tipoMovimentacao = :pTipo "
				+ "ORDER BY mov.valor");

		query2.setParameter("pConta", conta);
		query2.setParameter("pTipo", TipoMovimentacao.SAIDA);
		List<Movimentacao> result2 = query2.getResultList();

		result2.forEach(m -> {
			System.out.println("Descricão: " + m.getDescricao());
			System.out.println("Conta.id: " + m.getConta().getId());
		});

		em2.getTransaction().commit();
		em2.close();
	}
}
