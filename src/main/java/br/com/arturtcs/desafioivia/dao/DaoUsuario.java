package br.com.arturtcs.desafioivia.dao;

import br.com.arturtcs.desafioivia.model.Usuario;

public class DaoUsuario<T> extends DaoGeneric<Usuario> {
	
	public void removerUsuarioCascata(Usuario usuario) throws Exception {
		getEm().getTransaction().begin();
		String sqlDeleteFone = "delete from TelefoneUser where usuariopessoa_id = " + usuario.getId();
		getEm().createNativeQuery(sqlDeleteFone).executeUpdate();
		getEm().getTransaction().commit();
		
		super.deletarPorId(usuario);
	}

}
