package br.com.alura.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.alura.financas.modelo.Categoria;

public class MovimentacaoDAO {

	private EntityManager em;
	public MovimentacaoDAO(EntityManager em) {
		this.em = em;
	}
		
	public List<Double> getMediasPorCategoria(Categoria cat1) {
		TypedQuery<Double> query = em.createQuery(
				" SELECT AVG(mov.valor) FROM Movimentacao mov " +
				" JOIN mov.categorias cat " +
				" WHERE cat = :pCategoria" +
				" GROUB BY mov.categorias.id ", Double.class);

		query.setParameter("pCategoria", cat1);
		return query.getResultList();
	}	
}
