package br.com.empresa.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.empresa.exception.BOException;
import br.com.empresa.vo.UsuarioClienteVO;
import br.com.empresa.vo.UsuarioVO;

public class UsuarioClienteDAO implements IUsuarioClienteDAO{

	@Override
	public List<UsuarioClienteVO> listarClienteUsuario(UsuarioVO usuario) throws BOException {
		
		List<UsuarioClienteVO> usuarioClienteVOs = Dados.getUsuarioClienteVOs();
		
		List<UsuarioClienteVO> filtro = new ArrayList<>();
		
		for (UsuarioClienteVO usuarioClienteVO : usuarioClienteVOs) {
			if(usuarioClienteVO.getUsuarioVO().equals(usuario)) {
				filtro.add(usuarioClienteVO);
			}
		}
		
		return filtro;
	}

	@Override
	public int buscarQuantidadeClienteUsuario(UsuarioVO usuario) throws BOException {
		
		int qtd = 0;
		
		List<UsuarioClienteVO> usuarioClienteVOs = Dados.getUsuarioClienteVOs();
		
		
		for (UsuarioClienteVO usuarioClienteVO : usuarioClienteVOs) {
			if(usuarioClienteVO.getUsuarioVO().equals(usuario)) {
				qtd++;
			}
		}
		
		return qtd;
	}
	
}
