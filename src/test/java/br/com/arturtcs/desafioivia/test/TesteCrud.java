package br.com.arturtcs.desafioivia.test;

import java.util.List;

import org.junit.Test;

import br.com.arturtcs.desafioivia.dao.DaoGeneric;
import br.com.arturtcs.desafioivia.model.Telefone;
import br.com.arturtcs.desafioivia.model.Usuario;

public class TesteCrud {

	@Test
	public void salvarUsuario() {
		DaoGeneric<Usuario> dao = new DaoGeneric<Usuario>();
		Usuario usuario = new Usuario();
		usuario.setNome("Jorge Carlos");
		usuario.setEmail("jorge@");
		usuario.setSenha("123");
		dao.salvar(usuario);
		System.out.println("Pessoa cadastrada com sucesso!");
	}

	@Test
	public void buscarUsuario() {
		DaoGeneric<Usuario> dao = new DaoGeneric<Usuario>();
		Usuario usuario = new Usuario();
		usuario = dao.pesquisar(Usuario.class, 1L);
		System.out.println("O usuario pesquisado é o: " + usuario);
	}

	@Test
	public void listarTodosUsuarios() {
		DaoGeneric<Usuario> dao = new DaoGeneric<Usuario>();
		List<Usuario> lista = dao.listarTodos(Usuario.class);

		for (Usuario usuarios : lista) {
			System.out.println(usuarios);
			System.out.println("---------------------------------------------------------------------------------");
		}
	}

	@Test
	public void atualizarUsuario() {
		DaoGeneric<Usuario> dao = new DaoGeneric<Usuario>();
		Usuario usuarioPesquisado = dao.pesquisar(Usuario.class, 2L);

		System.out.println("O usuário a ser alterado é o " + usuarioPesquisado);

		usuarioPesquisado.setNome("Murilo Casé da Silva");

		usuarioPesquisado = dao.atualizar(usuarioPesquisado);
		System.out.println("Alterado nome do usuário para : " + usuarioPesquisado);
	}

	@Test
	public void daoGenericDelete() {
		DaoGeneric<Usuario> dao = new DaoGeneric<Usuario>();
		Usuario usuarioASerDeletado = dao.pesquisar(Usuario.class, 3L);

		System.out.println("Usuário a ser deletado: " + usuarioASerDeletado);

		dao.deletarPorId(usuarioASerDeletado);

		System.out.println("Deletado com sucesso!");
	}

	@Test
	public void testeGravaTelefone() {
		DaoGeneric dao = new DaoGeneric();
		Telefone fone = new Telefone();

		Usuario usuario = (Usuario) dao.pesquisar(Usuario.class, 1L);

		fone.setDdd(81);
		fone.setTipo("fixo");
		fone.setNumero("11111111");
		fone.setUsuario(usuario);

		dao.salvar(fone);

		System.out.println("Telefone salvo com sucesso!");
		System.out.println(fone);
		System.out.println(usuario);

	}

	@Test
	public void testeConsultaTelefone() {
		DaoGeneric<Usuario> dao = new DaoGeneric<Usuario>();

		Usuario pessoa = (Usuario) dao.pesquisar(Usuario.class, 1L);

		for (Telefone telefones : pessoa.getTelefones()) {
			System.out.println(telefones.getDdd());
			System.out.println(telefones.getNumero());
			System.out.println(telefones.getTipo());
			System.out.println(telefones.getUsuario().getNome());
			System.out.println("----------------------------------------------------------------------------");
		}
	}

	@Test
	public void listarTelefones() {
		DaoGeneric<Telefone> dao = new DaoGeneric<Telefone>();
		List<Telefone> lista = dao.listarTodos(Telefone.class);
		for (Telefone telefones : lista) {
			System.out.println(telefones);
			System.out.println("-----------------------------------------------------------------------------");
		}
	}

}
