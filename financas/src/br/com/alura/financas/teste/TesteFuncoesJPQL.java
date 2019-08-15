package br.com.alura.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.financas.dao.MovimentacaoDAO;
import br.com.alura.financas.modelo.Categoria;
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
	}
}
