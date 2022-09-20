package br.com.empresa.dao;

import br.com.empresa.vo.UsuarioVO;

public interface IUsuarioDAO {

	public abstract UsuarioVO buscarUsuario(String login, String senha);
	
}
