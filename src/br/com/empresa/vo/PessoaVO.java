package br.com.empresa.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

public class PessoaVO implements Serializable {

	private static final long serialVersionUID = 390695892933900176L;

	private BigInteger id;

	// CPF CNPJ - 12 CARACTERES
	private String cpfcnpj;

	// Tipo de pessoa - 1 Caractere (F-Física / J-Jurídica)
	private String tippes;

	// Nome - 100 Caracteres
	private String descri;

	// CEP
	private Integer cepend;

	// Nome da rua - 80 caracteres
	private String ruaend;

	// Bairro - 30 caracteres
	private String baiend;

	// Complemento do endereço - 80 caracteres
	private String comend;

	// Número do endereço - 20 caracteres
	private String numend;

	// Nome da Cidade - 30 caracteres
	private String cidade;

	//Estado - 2 caracteres
	private String estado;
	
	//Cliente - chave estrangeira
	private ClienteVO clienteVO;

	public PessoaVO() {
		super();
	}

	public PessoaVO(BigInteger id) {
		super();
		this.id = id;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getCpfcnpj() {
		return cpfcnpj;
	}

	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}

	public String getTippes() {
		return tippes;
	}

	public void setTippes(String tippes) {
		this.tippes = tippes;
	}

	public String getDescri() {
		return descri;
	}

	public void setDescri(String descri) {
		this.descri = descri;
	}

	public Integer getCepend() {
		return cepend;
	}

	public void setCepend(Integer cepend) {
		this.cepend = cepend;
	}

	public String getRuaend() {
		return ruaend;
	}

	public void setRuaend(String ruaend) {
		this.ruaend = ruaend;
	}

	public String getBaiend() {
		return baiend;
	}

	public void setBaiend(String baiend) {
		this.baiend = baiend;
	}

	public String getComend() {
		return comend;
	}

	public void setComend(String comend) {
		this.comend = comend;
	}

	public String getNumend() {
		return numend;
	}

	public void setNumend(String numend) {
		this.numend = numend;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public ClienteVO getClienteVO() {
		return clienteVO;
	}

	public void setClienteVO(ClienteVO clienteVO) {
		this.clienteVO = clienteVO;
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
		PessoaVO other = (PessoaVO) obj;
		return Objects.equals(id, other.id);
	}
	
	
}