package br.com.alura.financas.teste;

import javax.persistence.EntityManager;

import br.com.alura.financas.modelo.Conta;
import br.com.alura.financas.util.JPAUtil;

public class TesteConta {
	public static void main(String[] args) {
		Conta conta = new Conta("Manuela Santos", "123", "1234", "Quebrado");

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();
		//A partir do persist a entidade passa de Transient > Managed sendo assim
		//Qualquer altera��o na entitidade conta no final da transa��o ir� ser alterado no DB
		em.persist(conta);
		
		conta.setBanco("Bradesco");
		
		em.getTransaction().commit();
		em.close();
		
		//Ap�s fechar a transa��o est� no estado Detached, a entidade n�o � mais gerenciada pelo JPA
		//Necessitando voltar para o estado Managed, com o m�todo merge
		EntityManager em2 = new JPAUtil().getEntityManager();
		em2.getTransaction().begin();
		
		conta.setTitular("Leonardo da Silva");
		em2.merge(conta);
		
		em2.getTransaction().commit();
		em2.close();
		
		//Para se remover a entidade deve estar no estado Managed, atrav�s no metodo find � poss�vel
		EntityManager em3 = new JPAUtil().getEntityManager();
		em3.getTransaction().begin();
			
		Conta conta2 = em3.find(Conta.class, 6);
		em3.remove(conta2);		
		
		em3.getTransaction().commit();
		em3.close();
	}
}
