package br.com.arturtcs.desafioivia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.arturtcs.desafioivia.model.Usuario;
import br.com.arturtcs.desafioivia.utils.HibernateUtil;

public class DaoUsuario<T> extends DaoGeneric<Usuario> {

	public void removerUsuarioCascata(Usuario usuario) throws Exception {
		getEm().getTransaction().begin();
		String sqlDeleteFone = "delete from Telefone where usuario_id = " + usuario.getId();
		getEm().createNativeQuery(sqlDeleteFone).executeUpdate();
		getEm().getTransaction().commit();

		super.deletarPorId(usuario);
	}

	public Usuario login(String email, String senha) {

		Usuario usuario = null;

		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		usuario = (Usuario) em
				.createQuery("select u from Usuario u where u.email = '" + email + "' and u.senha = '" + senha + "'")
				.getSingleResult();

		transaction.commit();
		em.close();

		return usuario;
	}

	public List<Usuario> pesquisarUsuario(String nome) {
		return getEm().createQuery("from Usuario where upper(nome) like '%" + nome + "%'  ", Usuario.class)
				.getResultList();
	}

//	return getEm().createQuery("from Usuario where upper(nome) like '%" + nome + "%'  ", Usuario.class)
	
}
