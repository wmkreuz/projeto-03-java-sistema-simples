package br.com.empresa.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import br.com.empresa.bo.IPessoaBO;
import br.com.empresa.bo.IProdutoBO;
import br.com.empresa.bo.IUsuarioBO;
import br.com.empresa.bo.IUsuarioClienteBO;
import br.com.empresa.bo.PessoaBO;
import br.com.empresa.bo.ProdutoBO;
import br.com.empresa.bo.UsuarioBO;
import br.com.empresa.bo.UsuarioClienteBO;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.PessoaVO;
import br.com.empresa.vo.ProdutoVO;
import br.com.empresa.vo.UsuarioClienteVO;
import br.com.empresa.vo.UsuarioVO;

public class ServicoBeanLocal implements IServicoBeanLocal{

	@Override
	public UsuarioVO validarAcesso(String login, String senha) 
			throws BOValidationException, BOException{
		
		IUsuarioBO usuarioBO = new UsuarioBO();
		
		return usuarioBO.validarAcesso(login, senha);
	}

	@Override
	public List<UsuarioClienteVO> listarClienteUsuario(UsuarioVO usuario) throws BOException {
		
		IUsuarioClienteBO usuarioClienteBO = new UsuarioClienteBO();
		
		return usuarioClienteBO.listarClienteUsuario(usuario);
	}

	@Override
	public int buscarQuantidadeClienteUsuario(UsuarioVO usuario) throws BOException {
		
		IUsuarioClienteBO usuarioClienteBO = new UsuarioClienteBO();
		
		return usuarioClienteBO.buscarQuantidadeClienteUsuario(usuario);
	}

	@Override
	public List<PessoaVO> listarPessoas(String tipoPessoa, String nomePessoa, String cpfCnpj, String cidade,
			String estado, ClienteVO cliente) throws BOValidationException, BOException {

		IPessoaBO pessoaBO = new PessoaBO();
		
		return pessoaBO.listarPessoas(tipoPessoa, nomePessoa, cpfCnpj, cidade, estado, cliente);
	
	}

	@Override
	public void salvarPessoa(PessoaVO pessoa) throws BOValidationException, BOException {
		
		IPessoaBO pessoaBO = new PessoaBO();
		
		pessoaBO.salvarPessoa(pessoa);
		
	}

	@Override
	public void excluirPessoa(PessoaVO pessoa) throws BOValidationException, BOException {
		
		IPessoaBO pessoaBO = new PessoaBO();
		
		pessoaBO.excluirPessoa(pessoa);
		
	}

	@Override
	public PessoaVO buscarPessoaPorId(PessoaVO pessoa) throws BOException {
		IPessoaBO pessoaBO = new PessoaBO();
		return pessoaBO.buscarPessoaPorId(pessoa);
	}

	@Override
	public List<PessoaVO> listarPessoas(int first, int pageSize, Map<String, Object> filters, ClienteVO cliente)
			throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdutoVO> listarProduto(BigInteger id, String descri, String status, String codbar, ClienteVO client)
			throws BOException {
		IProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.listarProduto(id, descri, status, codbar, client);
	}

	@Override
	public void salvarProduto(ProdutoVO produto) throws BOValidationException, BOException {
		
        IProdutoBO produtoBO = new ProdutoBO();
		
        produtoBO.salvarProduto(produto);
		
	}

	@Override
	public void excluirProduto(ProdutoVO produto) throws BOValidationException, BOException {
		
        IProdutoBO produtoBO = new ProdutoBO();
		
        produtoBO.excluirProduto(produto);
		
	}

	@Override
	public ProdutoVO buscarProdutoPorId(ProdutoVO produto) throws BOException {
		IProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.buscarProdutoPorId(produto);
	}

	@Override
	public List<ProdutoVO> listarProdutos(int first, int pageSize, Map<String, Object> filters, ClienteVO cliente)
			throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

}
