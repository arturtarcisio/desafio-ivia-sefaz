package br.com.arturtcs.desafioivia.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.arturtcs.desafioivia.dao.DaoUsuario;
import br.com.arturtcs.desafioivia.model.Usuario;

@ManagedBean(name = "usuarioManagedBean")
@ViewScoped
public class UsuarioManagedBean {
	
	private Usuario usuario = new Usuario();
	private List<Usuario> list = new ArrayList<Usuario>();
	private DaoUsuario<Usuario> dao = new DaoUsuario<Usuario>();
	
	@PostConstruct
	public void init() {
		list = dao.listarTodos(Usuario.class);
	}
	
	public String salvar() {
		dao.salvar(usuario);
		list.add(usuario);
		
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Usuário cadastrado com sucesso!"));
		
		return "";
	}
	
	public String novo() {
		usuario = new Usuario();
		
		return "";
	}

	public String excluirUsuario() {
		
		try {
			dao.removerUsuarioCascata(usuario);;
			list.remove(usuario);
			usuario = new Usuario();		
			
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Excluído com sucesso!"));
			
		} catch (Exception e) {
			if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Existem telefones para o usuário!"));
			} else {
				e.printStackTrace();
			}
		}
			
		usuario = new Usuario();		
		
		return "";
	}
	
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Usuario> getList() {
		return list;
	}
	public void setList(List<Usuario> list) {
		this.list = list;
	}
		

}
