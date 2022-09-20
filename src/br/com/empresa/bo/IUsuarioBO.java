package br.com.empresa.bo;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.UsuarioVO;

public interface IUsuarioBO {

	public abstract UsuarioVO validarAcesso(String logim, String senha) 
			throws BOValidationException, BOException;
	
}
