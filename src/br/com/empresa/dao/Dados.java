package br.com.empresa.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.PessoaVO;
import br.com.empresa.vo.ProdutoVO;
import br.com.empresa.vo.UsuarioClienteVO;
import br.com.empresa.vo.UsuarioVO;

public class Dados {

	static UsuarioVO usuarioLogado;
	static ClienteVO clienteSelecionado;
	
	static List<UsuarioVO> usuarioVOs;
	static List<ClienteVO> clienteVOs;
	static List<UsuarioClienteVO> usuarioClienteVOs;
	
	static List<PessoaVO> pessoaVOs;
	static List<ProdutoVO> produtoVOs;
	
	static {
		//Inclusão de usuários
		usuarioVOs = new ArrayList<>();
		UsuarioVO u1 = new UsuarioVO();
		u1.setId(new BigInteger("1"));
		u1.setLogusu("pedro");
		u1.setSenusu("123456");
		
		UsuarioVO u2 = new UsuarioVO();
		u2.setId(new BigInteger("2"));
		u2.setLogusu("joao");
		u2.setSenusu("123456");
		
		usuarioVOs.add(u1);
		usuarioVOs.add(u2);
		
		//Inclusão de clientes
		clienteVOs = new ArrayList<>();
		ClienteVO c1 = new ClienteVO(new BigInteger("1"));
		c1.setDescri("Cliente 1");
		
		ClienteVO c2 = new ClienteVO(new BigInteger("2"));
		c2.setDescri("Cliente 2");
		
		clienteVOs.add(c1);
		clienteVOs.add(c2);
		
		//Vinculo entre ambos
		usuarioClienteVOs = new ArrayList<>();
		//Vinculo do pedro
		UsuarioClienteVO uc1 = new UsuarioClienteVO(new BigInteger("1"));
		uc1.setClienteVO(c1);
		uc1.setUsuarioVO(u1);
		
		UsuarioClienteVO uc2 = new UsuarioClienteVO(new BigInteger("2"));
		uc2.setClienteVO(c2);
		uc2.setUsuarioVO(u1);
		//Vinculo do joão
		UsuarioClienteVO uc3 = new UsuarioClienteVO(new BigInteger("3"));
		uc3.setClienteVO(c1);
		uc3.setUsuarioVO(u2);
		
		usuarioClienteVOs.add(uc1);
		usuarioClienteVOs.add(uc2);
		usuarioClienteVOs.add(uc3);
		
		//Inclusão de Pessoas
		pessoaVOs = new ArrayList<PessoaVO>();
		
		PessoaVO p1 = new PessoaVO();
		p1.setId(new BigInteger("1"));
		p1.setDescri("Pedro da Silva");
		p1.setCpfcnpj("098390919-99");
		p1.setTippes("F");
		p1.setRuaend("Rua Varci Colombo");
		p1.setBaiend("Bairro x");
		p1.setNumend("747");
		p1.setCidade("Criciuma");
		p1.setEstado("SC");
		p1.setCepend(88818686);
		p1.setClienteVO(c1);
		
		PessoaVO p2 = new PessoaVO();
		p2.setId(new BigInteger("2"));
		p2.setDescri("Maria Joaquina");
		p2.setCpfcnpj("544545445-44");
		p2.setTippes("F");
		p2.setRuaend("Rua Marechal Colombo");
		p2.setBaiend("Bairro xyz");
		p2.setNumend("800");
		p2.setCidade("Criciuma");
		p2.setEstado("SC");
		p2.setCepend(88818686);
		p2.setClienteVO(c1);

		pessoaVOs.add(p1);
		pessoaVOs.add(p2);
		
		//Inclusão de Produtos
		produtoVOs = new ArrayList<ProdutoVO>();
		
		ProdutoVO pd1 = new ProdutoVO();
		pd1.setId(new BigInteger("1"));
		pd1.setDescri("Notebook Acer");
		pd1.setCodbar("10000000000");
		pd1.setStatus("A");
		pd1.setQtdest(new BigDecimal(5.000));
		pd1.setValcom(new BigDecimal(2000.00));
		pd1.setValven(new BigDecimal(2499.99));
		pd1.setCliente(c1);
		
		ProdutoVO pd2 = new ProdutoVO();
		pd2.setId(new BigInteger("2"));
		pd2.setDescri("Mouse C3Tech");
		pd2.setCodbar("10000000001");
		pd2.setStatus("A");
		pd2.setQtdest(new BigDecimal(10.000));
		pd2.setValcom(new BigDecimal(15.00));
		pd2.setValven(new BigDecimal(20.00));
		pd2.setCliente(c1);
		
		ProdutoVO pd3 = new ProdutoVO();
		pd3.setId(new BigInteger("3"));
		pd3.setDescri("Teclado C3Tech");
		pd3.setCodbar("10000000003");
		pd3.setStatus("I");
		pd3.setQtdest(new BigDecimal(1.000));
		pd3.setValcom(new BigDecimal(20.00));
		pd3.setValven(new BigDecimal(29.99));
		pd3.setCliente(c1);
		
		produtoVOs.add(pd1);
		produtoVOs.add(pd2);
		produtoVOs.add(pd3);
	}


	public static UsuarioVO getUsuarioLogado() {
		return usuarioLogado;
	}

	public static void setUsuarioLogado(UsuarioVO usuarioLogado) {
		Dados.usuarioLogado = usuarioLogado;
	}

	public static ClienteVO getClienteSelecionado() {
		return clienteSelecionado;
	}

	public static void setClienteSelecionado(ClienteVO clienteSelecionado) {
		Dados.clienteSelecionado = clienteSelecionado;
	}

	public static List<UsuarioVO> getUsuarioVOs() {
		return usuarioVOs;
	}

	public static void setUsuarioVOs(List<UsuarioVO> usuarioVOs) {
		Dados.usuarioVOs = usuarioVOs;
	}

	public static List<ClienteVO> getClienteVOs() {
		return clienteVOs;
	}

	public static void setClienteVOs(List<ClienteVO> clienteVOs) {
		Dados.clienteVOs = clienteVOs;
	}

	public static List<UsuarioClienteVO> getUsuarioClienteVOs() {
		return usuarioClienteVOs;
	}

	public static void setUsuarioClienteVOs(List<UsuarioClienteVO> usuarioClienteVOs) {
		Dados.usuarioClienteVOs = usuarioClienteVOs;
	}
	
	public static List<PessoaVO> getPessoaVOs() {
		return pessoaVOs;
	}

	public static void setPessoaVOs(List<PessoaVO> pessoaVOs) {
		Dados.pessoaVOs = pessoaVOs;
	}

	public static List<ProdutoVO> getProdutoVOs() {
		return produtoVOs;
	}

	public static void setProdutoVOs(List<ProdutoVO> produtoVOs) {
		Dados.produtoVOs = produtoVOs;
	}
	
}
