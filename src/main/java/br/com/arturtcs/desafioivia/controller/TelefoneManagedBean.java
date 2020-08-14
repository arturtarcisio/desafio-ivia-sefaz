package br.com.arturtcs.desafioivia.controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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
		String coduser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("codigousuario");
		usuario = daoUsuario.pesquisar(Usuario.class, Long.parseLong(coduser));
	}
	
	public String salvar() {
		telefone.setUsuario(usuario);
		daoTelefone.atualizar(telefone);
		
		usuario = daoUsuario.pesquisar(Usuario.class, usuario.getId());
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Telefone cadastrado com sucesso!"));
		
		telefone = new Telefone();
		return "";
	}
	
	public String novoTelefone() {
		telefone = new Telefone();
		return "";
	}
	
	public String removerTelefone() {
		try {
			telefone.setUsuario(usuario);
			daoTelefone.deletarPorId(telefone);
			usuario = daoUsuario.pesquisar(Usuario.class, usuario.getId());
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Telefone excluído com sucesso!"));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		telefone = new Telefone();
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
