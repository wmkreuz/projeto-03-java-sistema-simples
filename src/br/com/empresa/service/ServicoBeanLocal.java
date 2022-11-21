package br.com.empresa.service;

import java.io.File;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import br.com.empresa.bo.IProdutoBO;
import br.com.empresa.bo.IUsuarioBO;
import br.com.empresa.bo.IUsuarioClienteBO;
import br.com.empresa.bo.ProdutoBO;
import br.com.empresa.bo.UsuarioBO;
import br.com.empresa.bo.UsuarioClienteBO;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.ClienteVO;
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
	public void importarProdutos(List<ProdutoVO> produtos) throws BOValidationException, BOException {
		
        IProdutoBO produtoBO = new ProdutoBO();
		
        produtoBO.importarProdutos(produtos);
		
	}
	
	@Override
	public void exportarProdutos(File raiz, ClienteVO client) throws BOException{
		
        IProdutoBO produtoBO = new ProdutoBO();
		
        produtoBO.exportarProdutos(raiz, client);
		
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
		IProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.listarProdutos(first, pageSize, filters, cliente);
	}

}
