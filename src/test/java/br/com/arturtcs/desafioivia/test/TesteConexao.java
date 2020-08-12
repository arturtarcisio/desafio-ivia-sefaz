package br.com.arturtcs.desafioivia.test;

import org.junit.Test;

import br.com.arturtcs.desafioivia.utils.HibernateUtil;

public class TesteConexao {
	
	@Test
	public void testeHibernateUtil() {
		HibernateUtil.getEntityManager();
		System.out.println("Conectado!");
	}

}
