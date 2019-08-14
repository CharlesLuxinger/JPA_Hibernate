package br.com.alura.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.alura.financas.modelo.Categoria;
import br.com.alura.financas.modelo.Movimentacao;
import br.com.alura.financas.util.JPAUtil;

public class TesteJPQL {
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		Categoria cat1 = new Categoria();
		cat1.setId(1);

		Query query = em.createQuery(
				"SELECT mov FROM Movimentacao mov " +
				"JOIN mov.categorias cat " +
				"WHERE cat = :pCategoria");

		query.setParameter("pCategoria", cat1);

		System.out.println(query.toString());

		List<Movimentacao> result2 = query.getResultList();

		result2.forEach(m -> {
			System.out.println("Descricão: " + m.getDescricao());
			System.out.println("Conta.id: " + m.getConta().getId());
		});

		em.getTransaction().commit();
		em.close();
	}
}
