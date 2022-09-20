package br.com.empresa.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

/**
 * Esta classe é responsavel por manter a ligação entre o usuário e o cliente
 * @since 11/08/2022
 * @author willhan.kreuz
 *
 */
public class UsuarioClienteVO implements Serializable{
	
	private static final long serialVersionUID = -6477812876104948431L;

	private BigInteger id;
	
	private UsuarioVO usuarioVO;
	
	private ClienteVO clienteVO;

	public UsuarioClienteVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsuarioClienteVO(BigInteger id) {
		super();
		this.id = id;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public UsuarioVO getUsuarioVO() {
		return usuarioVO;
	}

	public void setUsuarioVO(UsuarioVO usuarioVO) {
		this.usuarioVO = usuarioVO;
	}

	public ClienteVO getClienteVO() {
		return clienteVO;
	}

	public void setClienteVO(ClienteVO clienteVO) {
		this.clienteVO = clienteVO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioClienteVO other = (UsuarioClienteVO) obj;
		return Objects.equals(id, other.id);
	}
	
}
