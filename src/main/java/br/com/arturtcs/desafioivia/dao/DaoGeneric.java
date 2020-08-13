package br.com.arturtcs.desafioivia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.arturtcs.desafioivia.utils.HibernateUtil;

public class DaoGeneric<T> {
	
	private EntityManager em = HibernateUtil.getEntityManager(); 
	
	public void salvar(T entidade) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(entidade);
		transaction.commit();
	}
	
	public T pesquisar(Class<T> entidade, Long id) {
		em.clear();
		T entidadeRetornada = em.find(entidade, id);
		//T entidadeRetornada = (T) em.createQuery("from " + entidade.getSimpleName() + " where id = " + id).getSingleResult();
		return entidadeRetornada;
	}
	
	public T atualizar(T entidade) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		T entidadeSalva = em.merge(entidade);
		transaction.commit();
		return entidadeSalva;
	}
	
	public void deletarPorId(T entidade) {
		Object id = HibernateUtil.getPrimaryKey(entidade);
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.createNativeQuery(
				"delete from " + entidade.getClass().getSimpleName().toLowerCase() + 
				" where id = " + id).executeUpdate(); 
		transaction.commit(); 
	}
	
	public List<T> listarTodos(Class<T> entidade){
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		List<T> lista = em.createQuery("from " + entidade.getName()).getResultList();
		
		transaction.commit();
		
		return lista;
		
	}

	public EntityManager getEm() {
		return em;
	}

}
