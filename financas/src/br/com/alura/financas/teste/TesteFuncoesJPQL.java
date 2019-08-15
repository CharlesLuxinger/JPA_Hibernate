package br.com.alura.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.alura.financas.modelo.Categoria;
import br.com.alura.financas.util.JPAUtil;

public class TesteFuncoesJPQL {
	@SuppressWarnings({ "deprecation" })
	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		Categoria cat1 = new Categoria();
		cat1.setId(1);

		TypedQuery<Double> query = em.createQuery(
				" SELECT AVG(mov.valor) FROM Movimentacao mov " +
				" JOIN mov.categorias cat " +
				" WHERE cat = :pCategoria" +
				" GROUB BY mov.categorias.id ", Double.class);

		query.setParameter("pCategoria", cat1);

		List<Double> result2 = (List<Double>) query.getResultList();

		result2.forEach(m -> {
			System.out.println("Média: " + m);
		});

		em.getTransaction().commit();
		em.close();
	}
}
