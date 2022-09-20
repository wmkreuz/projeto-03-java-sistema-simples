package br.com.empresa.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.ProdutoVO;

public class ProdutoDAO implements IProdutoDAO{
	

	@Override
	public List<ProdutoVO> listarProduto(BigInteger id, String descri, String status, String codbar, ClienteVO client)
			throws BOException {

		List<ProdutoVO> produtos = Dados.getProdutoVOs();
		List<ProdutoVO> retorno = new ArrayList<ProdutoVO>();
		
		for(ProdutoVO produtoVO : produtos) {
			
			if(produtoVO.getCliente().equals(client) == false) {
				continue;
			}
			
			if(id != null) {
				if(produtoVO.getId() != null &&
						produtoVO.getId().equals(id) == false) {
					continue;
				}
			}
			
			if(status != null) {
				if(produtoVO.getStatus() != null && 
						produtoVO.getStatus().equals(status) == false) {
					continue;
				}
			}
			
			if(descri != null && descri.trim().length() > 0) {
				if(produtoVO.getDescri().contains(descri) == false) {
					continue;
				}
			}
			
			if(codbar != null && codbar.trim().length() > 0) {
				if(produtoVO.getCodbar().contains(codbar) == false) {
					continue;
				}
			}
			
			retorno.add(produtoVO);
			
		}
		
		return retorno;
	}

	@Override
	public void salvarProduto(ProdutoVO produto) throws BOValidationException, BOException {
		
		List<ProdutoVO> produtoVOs = Dados.getProdutoVOs();
		
		if(produto.getId() == null) {
			if(produtoVOs.size() > 0) {
				ProdutoVO ultimoProdutoVO = produtoVOs.get(produtoVOs.size() - 1);
				produto.setId(ultimoProdutoVO.getId().add(BigInteger.ONE));
			}else {
				produto.setId(BigInteger.ONE);
			}
			Dados.getProdutoVOs().add(produto);
		}else {
			
			for(int i = 0; i < produtoVOs.size(); i++) {
				if(produtoVOs.get(i).equals(produto)) {
					Dados.getProdutoVOs().set(i, produto);
				}
			}
			
		}
		
	}

	@Override
	public void excluirProduto(ProdutoVO produto) throws BOValidationException, BOException {
		
		for(int i = 0; i < Dados.getProdutoVOs().size(); i++) {
			if(Dados.getProdutoVOs().get(i).equals(produto)) {
				Dados.getProdutoVOs().remove(i);
			}
		}
		
	}

	@Override
	public ProdutoVO buscarProdutoPorId(ProdutoVO produto) throws BOException {
		
		for(int i = 0; i < Dados.getProdutoVOs().size(); i++) {
			if(Dados.getProdutoVOs().get(i).equals(produto)) {
				return Dados.getProdutoVOs().get(i);
			}
		}
		
		return null;
	}

	@Override
	public List<ProdutoVO> listarProdutos(int first, int pageSize, Map<String, Object> filters, ClienteVO cliente)
			throws BOException {
		// TODO Auto-generated method stub
		return null;
	}

}
