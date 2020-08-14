package br.com.arturtcs.desafioivia.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.arturtcs.desafioivia.model.Usuario;
import br.com.arturtcs.desafioivia.utils.HibernateUtil;

@WebFilter(urlPatterns = { "/*" })
public class FilterAutenticacao implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		HibernateUtil.getEntityManager();

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		String url = req.getServletPath();

		if (!url.equalsIgnoreCase("index.jsf") && usuarioLogado == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsf");
			dispatcher.forward(request, response);
			return;
		} else {
			// Executa as a��es do request e response
			chain.doFilter(request, response);
		}

	}

	@Override
	public void destroy() {
	}

}