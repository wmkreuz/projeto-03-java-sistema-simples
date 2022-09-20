package br.com.empresa.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

public class UsuarioVO implements Serializable {

	private static final long serialVersionUID = 4069103495730479233L;

	private BigInteger id;
	
	//Login - 30 caracteres
	private String logusu;
	
	//Senha - 100 caracteres
	private String senusu;
	
	private PessoaVO pessoaVO;

	public UsuarioVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsuarioVO(BigInteger id) {
		super();
		this.id = id;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getLogusu() {
		return logusu;
	}

	public void setLogusu(String logusu) {
		this.logusu = logusu;
	}

	public String getSenusu() {
		return senusu;
	}

	public void setSenusu(String senusu) {
		this.senusu = senusu;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public PessoaVO getPessoaVO() {
		return pessoaVO;
	}

	public void setPessoaVO(PessoaVO pessoaVO) {
		this.pessoaVO = pessoaVO;
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
		UsuarioVO other = (UsuarioVO) obj;
		return Objects.equals(id, other.id);
	}
	
}
