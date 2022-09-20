package br.com.empresa.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

public class ProdutoVO implements Serializable {

	private static final long serialVersionUID = 3929961339408678201L;

	private BigInteger id;
	
	//Nome do produto - 100 caracteres
	private String descri;
	
	//Código de barras - 20 caracteres
	private String codbar;
	
	//Status - 1 caracter
	private String status;
	
	//Quantidade em estoque - 7 inteiros e 4 decimais
	private BigDecimal qtdest;
	
	//Valor de compra - 7 inteiros e 2 decimais
	private BigDecimal valcom;
	
	//Valor de venda - 7 inteiros e 2 decimais
	private BigDecimal valven;
	
	//Cliente
	private ClienteVO cliente;
	
	public ProdutoVO() {
		super();
	}

	public ProdutoVO(BigInteger id) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getQtdest() {
		return qtdest;
	}

	public void setQtdest(BigDecimal qtdest) {
		this.qtdest = qtdest;
	}


	public ClienteVO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}
	
	public BigDecimal getValcom() {
		return valcom;
	}

	public void setValcom(BigDecimal valcom) {
		this.valcom = valcom;
	}

	public BigDecimal getValven() {
		return valven;
	}

	public void setValven(BigDecimal valven) {
		this.valven = valven;
	}

	public String getCodbar() {
		return codbar;
	}

	public void setCodbar(String codbar) {
		this.codbar = codbar;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoVO other = (ProdutoVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	/*@Override
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
		ProdutoVO other = (ProdutoVO) obj;
		return Objects.equals(id, other.id);
	}*/
}
