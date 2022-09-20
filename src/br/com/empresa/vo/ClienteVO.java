package br.com.empresa.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

public class ClienteVO implements Serializable{

	private static final long serialVersionUID = -1559895156741900940L;

	private BigInteger id;
	
	//Nome do cliente - 100 caracteres
	private String descri;

	public ClienteVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClienteVO(BigInteger id) {
		super();
		this.id = id;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getDescri() {
		return descri;
	}

	public void setDescri(String descri) {
		this.descri = descri;
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
		ClienteVO other = (ClienteVO) obj;
		return Objects.equals(id, other.id);
	}
	
	@Override
	public String toString() {
		return this.descri;
	}
	
}
