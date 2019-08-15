package br.com.alura.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.alura.financas.dao.MovimentacaoDAO;
import br.com.alura.financas.modelo.Categoria;
import br.com.alura.financas.modelo.Conta;
import br.com.alura.financas.modelo.Movimentacao;
import br.com.alura.financas.util.JPAUtil;

public class TesteFuncoesJPQL {
	@SuppressWarnings({ "deprecation" })
	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		Categoria cat1 = new Categoria();
		cat1.setId(1);

		MovimentacaoDAO movimentacaoDao = new MovimentacaoDAO(em);

		List<Double> result2 = movimentacaoDao.getMediasPorCategoria(cat1);

		result2.forEach(m -> {
			System.out.println("Média: " + m);
		});

		em.getTransaction().commit();
		em.close();

		// teste named query

		EntityManager em2 = new JPAUtil().getEntityManager();
		em2.getTransaction().begin();

		Conta conta = new Conta();
		conta.setId(1);

		TypedQuery<Movimentacao> queryMovimentacoesConta = em2.createNamedQuery("movimentacaoFindByConta",
				Movimentacao.class);
		queryMovimentacoesConta.setParameter("pConta", conta);

		List<Movimentacao> movimentacoes = queryMovimentacoesConta.getResultList();

		movimentacoes.forEach(x -> System.out.println(x.getConta().getTitular()));

		em2.getTransaction().commit();
		em2.close();
	}
}
