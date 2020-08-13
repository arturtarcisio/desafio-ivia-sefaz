package br.com.arturtcs.desafioivia.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.arturtcs.desafioivia.dao.DaoTelefone;
import br.com.arturtcs.desafioivia.dao.DaoUsuario;
import br.com.arturtcs.desafioivia.model.Telefone;
import br.com.arturtcs.desafioivia.model.Usuario;

@ManagedBean(name = "telefoneManagedBean")
@ViewScoped
public class TelefoneManagedBean {
	
	private Telefone telefone = new Telefone();
	private Usuario usuario = new Usuario();
	
	private DaoTelefone<Telefone> daoTelefone = new DaoTelefone<Telefone>();
	private DaoUsuario<Usuario> daoUsuario = new DaoUsuario<Usuario>();
	
	@PostConstruct
	private void init() {
		
	}
	
	public String salvar() {
		
		return "";
	}
	
	public String novoTelefone() {
		telefone = new Telefone();
		return "";
	}
	
	public String removerTelefone() {
		
		
		return "";
	}	
	
	public Telefone getTelefone() {
		return telefone;
	}
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public DaoTelefone<Telefone> getDaoTelefone() {
		return daoTelefone;
	}

	public void setDaoTelefone(DaoTelefone<Telefone> daoTelefone) {
		this.daoTelefone = daoTelefone;
	}

	public DaoUsuario<Usuario> getDaoUsuario() {
		return daoUsuario;
	}

	public void setDaoUsuario(DaoUsuario<Usuario> daoUsuario) {
		this.daoUsuario = daoUsuario;
	}	

}
