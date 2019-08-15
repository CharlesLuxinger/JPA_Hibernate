package br.com.alura.financas.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.alura.financas.enums.TipoMovimentacao;
import br.com.alura.financas.modelo.Categoria;
import br.com.alura.financas.modelo.Conta;
import br.com.alura.financas.modelo.Movimentacao;
import br.com.alura.financas.util.JPAUtil;

public class PopulaMovimentacaoComCategoria {
	public static void main(String[] args) {

		Categoria cat1 = new Categoria("Viagem");
		Categoria cat2 = new Categoria("Negócio");

		Conta conta1 = new Conta("Bruna Carla", "0547", "1234", "Banco America");

		Movimentacao mov1 = new Movimentacao(new BigDecimal("100.00"), "Viagem a São Paulo", Calendar.getInstance(),
				TipoMovimentacao.SAIDA, conta1, Arrays.asList(cat1, cat2));
		
		Calendar amanha = Calendar.getInstance();
		amanha.add(Calendar.DAY_OF_MONTH, 1);
		
		Movimentacao mov2 = new Movimentacao(new BigDecimal("300.00"), "Viagem a Rio de Janeiro",
				amanha, TipoMovimentacao.SAIDA, conta1, Arrays.asList(cat1, cat2));

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		em.persist(cat1);
		em.persist(cat2);
		
		em.persist(conta1);
		
		em.persist(mov1);
		em.persist(mov2);

		em.getTransaction().commit();
		em.close();
	}
}
