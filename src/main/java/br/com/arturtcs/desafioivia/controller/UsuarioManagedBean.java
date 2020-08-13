package br.com.arturtcs.desafioivia.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

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

	public String logar() {
		Usuario usuarioLogar = dao.login(usuario.getEmail(), usuario.getSenha());

		if (usuarioLogar != null) {
			// adicionar o usuário na sessão usuarioLogado
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", usuarioLogar);

			return "usuarioCadastro.jsf";
		}

		return "index.jsf";
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuarioLogado");

		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();

		httpServletRequest.getSession().invalidate();

		return "index.jsf";
	}
	
	public boolean permiteAcesso(String acesso) {
		Usuario usuario = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuarioLogado");
		return usuario.getPerfil().equals(acesso);
		
	}

	public String salvar() {
		dao.salvar(usuario);
		//list.add(usuario);

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Usuário cadastrado com sucesso!"));

		usuario = new Usuario();
		return "";
	}

	public String novo() {
		usuario = new Usuario();

		return "";
	}

	public String excluirUsuario() {

		try {
			dao.removerUsuarioCascata(usuario);
			
			list.remove(usuario);
			usuario = new Usuario();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Excluído com sucesso!"));

		} catch (Exception e) {
			if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Informação: ", "Existem telefones para o usuário!"));
			} else {
				e.printStackTrace();
			}
		}

		usuario = new Usuario();

		return "";
	}

	public void pesquisaCep(AjaxBehaviorEvent event) {
		try {

			URL url = new URL("https://viacep.com.br/ws/" + usuario.getCep() + "/json/");
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

			String cep = "";
			StringBuilder jsonCep = new StringBuilder();

			while ((cep = br.readLine()) != null) {
				jsonCep.append(cep);
			}

			Usuario usuarioCep = new Gson().fromJson(jsonCep.toString(), Usuario.class);

			usuario.setCep(usuarioCep.getCep());
			usuario.setLogradouro(usuarioCep.getLogradouro());
			usuario.setComplemento(usuarioCep.getComplemento());
			usuario.setBairro(usuarioCep.getBairro());
			usuario.setLocalidade(usuarioCep.getLocalidade());
			usuario.setUf(usuarioCep.getUf());

		} catch (Exception e) {
			e.printStackTrace();
		}
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
