package br.com.empresa.dao;

import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.UsuarioVO;

public class Dados {

	static UsuarioVO usuarioSelecionado;
	static ClienteVO clienteSelecionado;

	public static UsuarioVO getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public static void setUsuarioSelecionado(UsuarioVO usuarioSelecionado) {
		Dados.usuarioSelecionado = usuarioSelecionado;
	}

	public static ClienteVO getClienteSelecionado() {
		return clienteSelecionado;
	}

	public static void setClienteSelecionado(ClienteVO clienteSelecionado) {
		Dados.clienteSelecionado = clienteSelecionado;
	}	
	
}
