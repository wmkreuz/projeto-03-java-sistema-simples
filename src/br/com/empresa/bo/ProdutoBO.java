package br.com.empresa.bo;

import java.io.File;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import br.com.empresa.dao.ProdutoDAO;
import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.ProdutoVO;

public class ProdutoBO implements IProdutoBO{
	
	private ProdutoDAO produtoDAO;
	
	public ProdutoBO() {
		produtoDAO = new ProdutoDAO();
	}
	

	@Override
	public List<ProdutoVO> listarProduto(BigInteger id, String descri, String status, String codbar, ClienteVO client)
			throws BOException {
		if(client == null || client.getId() == null) {
			throw new BOException();
		}
		
		
		
		return produtoDAO.listarProduto(id, descri, status, codbar, client);
	}

	@Override
	public void salvarProduto(ProdutoVO produto) throws BOValidationException, BOException {
		
		if(produto == null) {
			throw new BOException("Não é possível salvar o produto pois o objeto é nulo.");
		}else if(produto.getDescri() == null || produto.getDescri().trim().length() == 0){
			throw new BOValidationException("Descrição: erro de validação. "
					+ "A descrição deve ser preenchida.");
		}else if(produto.getCodbar() == null || produto.getCodbar().trim().length() == 0){
			throw new BOValidationException("Cod. Barra: erro de validação. "
					+ "O código de barrras deve ser preenchido.");
		}else if(produto.getQtdest() == null){
			throw new BOValidationException("Qtd: erro de validação. "
					+ "A quantidade deve ser preenchida.");
		}else if(produto.getValcom() == null){
			throw new BOValidationException("Vlr Compra: erro de validação. "
					+ "O valor de compra deve ser preenchido.");
		}else if(produto.getValven() == null){
			throw new BOValidationException("Vlr Venda: erro de validação. "
					+ "O valor de venda deve ser preenchido.");
		}else if(produto.getDatfab() == null){
			throw new BOValidationException("Data Fabricação: erro de validação. "
					+ "A data de fabricação deve ser preenchida.");
		}else if(produto.getDatval() == null){
			throw new BOValidationException("Data Validade: erro de validação. "
					+ "A data de validade deve ser preenchida.");
		}else {
			if(produto.getStatus() == null) {
				throw new BOValidationException("Status: erro de validação. "
						+ "O status não pode ser nulo.");
			}
		}
		
		produtoDAO.salvarProduto(produto);
		
	}
	
	@Override
	public void importarProdutos(List<ProdutoVO> produtos) throws BOValidationException, BOException {
		
		for(ProdutoVO produto : produtos) {
			
			if(produto == null) {
				throw new BOException("Não é possível salvar o produto pois o objeto é nulo.");
			}else if(produto.getDescri() == null || produto.getDescri().trim().length() == 0){
				throw new BOValidationException("Descrição: erro de validação. "
						+ "A descrição deve ser preenchida.");
			}else if(produto.getCodbar() == null || produto.getCodbar().trim().length() == 0){
				throw new BOValidationException("Cod. Barra: erro de validação. "
						+ "O código de barrras deve ser preenchido.");
			}else if(produto.getQtdest() == null){
				throw new BOValidationException("Qtd: erro de validação. "
						+ "A quantidade deve ser preenchida.");
			}else if(produto.getValcom() == null){
				throw new BOValidationException("Vlr Compra: erro de validação. "
						+ "O valor de compra deve ser preenchido.");
			}else if(produto.getValven() == null){
				throw new BOValidationException("Vlr Venda: erro de validação. "
						+ "O valor de venda deve ser preenchido.");
			}else {
				if(produto.getStatus() == null) {
					throw new BOValidationException("Status: erro de validação. "
							+ "O status não pode ser nulo.");
				}
			}
			
		}
		
		produtoDAO.importarProdutos(produtos);
		
	}
	
	@Override
	public void exportarProdutos(File raiz, ClienteVO client) throws BOException{
		
		produtoDAO.exportarProdutos(raiz, client) ;
		
	}

	@Override
	public void excluirProduto(ProdutoVO produto) throws BOValidationException, BOException {
		
		if(produto == null || produto.getId() == null) {
			throw new BOException("Ocorreu um erro ao excluir o produto.");
		}
		
		produtoDAO.excluirProduto(produto);
		
	}

	@Override
	public ProdutoVO buscarProdutoPorId(ProdutoVO produto) throws BOException {
		
		if(produto == null || produto.getId() == null) {
			throw new BOException("Ocorreu um erro ao buscar o produto pelo ID.");
		}
		
		return produtoDAO.buscarProdutoPorId(produto);
	}

	@Override
	public List<ProdutoVO> listarProdutos(int first, int pageSize, Map<String, Object> filters, ClienteVO cliente)
			throws BOException {
		if(cliente == null || cliente.getId() == null) {
			throw new BOException();
		}
		
		
		
		return produtoDAO.listarProdutos(first, pageSize, filters, cliente);
	}

}
