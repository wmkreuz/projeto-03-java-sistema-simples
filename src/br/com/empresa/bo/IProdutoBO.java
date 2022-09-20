package br.com.empresa.bo;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.ProdutoVO;

public interface IProdutoBO {
	
	public abstract List<ProdutoVO> listarProduto(BigInteger id, String descri, 
			String status, String codbar, ClienteVO client) throws BOException;
	
	public abstract void salvarProduto(ProdutoVO produto) throws BOValidationException, BOException;
	
	public abstract void excluirProduto(ProdutoVO produto) throws BOValidationException, BOException;
	
	public abstract ProdutoVO buscarProdutoPorId(ProdutoVO produto) throws BOException;
	
	public abstract List<ProdutoVO> listarProdutos(int first, int pageSize,
		       Map<String, Object> filters, ClienteVO cliente) throws BOException;
	
	
}
