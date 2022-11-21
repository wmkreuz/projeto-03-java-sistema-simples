package br.com.empresa.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.empresa.exception.BOException;
import br.com.empresa.exception.BOValidationException;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.ProdutoVO;

public class ProdutoDAO implements IProdutoDAO{


	@Override
	public List<ProdutoVO> listarProduto(BigInteger id, String descri, String status, String codbar, ClienteVO client)
			throws BOException {
		
		EntityManager em = HibernateUtil.getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ProdutoVO> criteria = cb.createQuery(ProdutoVO.class);
		
		//Clausula from
		Root<ProdutoVO> produtoFrom = criteria.from(ProdutoVO.class);
		
		//Clausula where
		Predicate produtoWhere = cb.equal(produtoFrom.get("client"), client);
		
		if(id != null) {
			produtoWhere = cb.and(produtoWhere, cb.equal(produtoFrom.get("id"),id));
		}

		if(descri != null && descri.trim().length() > 0){
			produtoWhere = cb.and(produtoWhere, 
					cb.like(cb.lower(produtoFrom.get("descri")), "%" + descri.toLowerCase() + "%"));
		}
		
		if(status != null){
			produtoWhere = cb.and(produtoWhere, cb.equal(produtoFrom.get("status"), status));
		}
		
		if(codbar != null && codbar.trim().length() > 0){
			produtoWhere = cb.and(produtoWhere, cb.like(cb.lower(produtoFrom.get("codbar")),
			"%" + codbar.toLowerCase() + "%"));
		}
		
		//Atribuindo as clausulas a consulta
		criteria.select(produtoFrom);
		criteria.where(produtoWhere);
				
		TypedQuery<ProdutoVO> query = em.createQuery(criteria);
		
		query.setMaxResults(30);
		
		List<ProdutoVO> listaProdutos = query.getResultList();

		em.close();
		
		
		return listaProdutos;
		
	}

	@Override
	public void salvarProduto(ProdutoVO produto) throws BOValidationException, BOException {
		
		EntityManager em = HibernateUtil.getEntityManager();
		
		if(produto.getId() == null) {
			
			ProdutoVO produtoVO = new ProdutoVO();
			
			produtoVO.setDescri(produto.getDescri());
			produtoVO.setCodbar(produto.getCodbar());
			produtoVO.setQtdest(produto.getQtdest());
			produtoVO.setValcom(produto.getValcom());
			produtoVO.setValven(produto.getValven());
			produtoVO.setDatfab(produto.getDatfab());
			produtoVO.setDatval(produto.getDatval());
			produtoVO.setStatus(produto.getStatus());
			produtoVO.setClient(produto.getClient());
			
			try {
				em.getTransaction().begin();
				em.persist(produtoVO);
				produto.setId(produtoVO.getId());
				em.getTransaction().commit();
				
			} catch (Exception e) {
				e.printStackTrace();
				em.getTransaction().rollback();
			} finally {
				em.close();
			}
			
		}else {
			try {
				
				em.getTransaction().begin();
				ProdutoVO produtoVO = em.find(ProdutoVO.class, produto.getId());
				produtoVO.setDescri(produto.getDescri());
				produtoVO.setCodbar(produto.getCodbar());
				produtoVO.setQtdest(produto.getQtdest());
				produtoVO.setValcom(produto.getValcom());
				produtoVO.setValven(produto.getValven());
				produtoVO.setDatfab(produto.getDatfab());
				produtoVO.setDatval(produto.getDatval());
				produtoVO.setStatus(produto.getStatus());
				em.merge(produtoVO);
				em.getTransaction().commit();
				
			} catch (Exception e) {
				e.printStackTrace();
				em.getTransaction().rollback();
			} finally {
				em.close();
			}
		}		
	}
	
	@Override
	public void importarProdutos(List<ProdutoVO> produtos) throws BOValidationException, BOException {
		
		EntityManager em = HibernateUtil.getEntityManager();
		
		for(ProdutoVO produto : produtos) {
			ProdutoVO produtoVO = new ProdutoVO();
			
			produtoVO.setDescri(produto.getDescri());
			produtoVO.setCodbar(produto.getCodbar());
			produtoVO.setQtdest(produto.getQtdest());
			produtoVO.setValcom(produto.getValcom());
			produtoVO.setValven(produto.getValven());
			produtoVO.setStatus(produto.getStatus());
			produtoVO.setClient(produto.getClient());
			
			try {
				em.getTransaction().begin();
				em.persist(produtoVO);
				em.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				em.getTransaction().rollback();
			}
		}
		
		em.close();
		
	}

	@Override
	public void excluirProduto(ProdutoVO produto) throws BOValidationException, BOException {
		
		EntityManager em = HibernateUtil.getEntityManager();
		
		try {
			
			em.getTransaction().begin();
			ProdutoVO produtoVO = em.find(ProdutoVO.class, produto.getId());
			em.remove(produtoVO);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		
	}

	@Override
	public ProdutoVO buscarProdutoPorId(ProdutoVO produto) throws BOException {
		return produto;
	}

	@Override
	public List<ProdutoVO> listarProdutos(int first, int pageSize, Map<String, Object> filters, ClienteVO cliente)
			throws BOException {
				return null;
		
	}

	public void exportarProdutos(File raiz, ClienteVO client)  throws BOException{
		
		try {
			
			EntityManager em = HibernateUtil.getEntityManager();
			
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ProdutoVO> criteria = cb.createQuery(ProdutoVO.class);
			
			//Cláusula From
			Root<ProdutoVO> produtoFrom = criteria.from(ProdutoVO.class);
			
			//Clausula where
			Predicate produtoWhere = cb.equal(produtoFrom.get("client"), client);
			
			//Atribuindo as cláusulas à consulta
			criteria.select(produtoFrom);
			criteria.where(produtoWhere);
			
			TypedQuery<ProdutoVO> query = em.createQuery(criteria);
			List<ProdutoVO> listaProdutos = query.getResultList();
			
			
			//Classes responsaveis pela escritura do novo Arquivo
			OutputStream outputStream = new FileOutputStream(raiz, true);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "ISO-8859-1"); //utf-8
			PrintWriter printWriter = new PrintWriter(raiz);
			
			printWriter.println("id;descri;codbar;status;qtdest;vlrcom;vlrven;client;datfab;datval");
			
			for (ProdutoVO produtoVO : listaProdutos) {		
				printWriter.println(produtoVO.getId() + ";" + produtoVO.getDescri() + ";" +
						            produtoVO.getCodbar() + ";" + produtoVO.getStatus() + ";" +
						            produtoVO.getQtdest() + ";" + produtoVO.getValcom() + ";" +
						            produtoVO.getValven() + ";" + produtoVO.getClient() + ";" +
						            produtoVO.getDatfab() + ";" + produtoVO.getDatval());
			}

			//Finalizando objetos de escrita
			printWriter.close();
			outputStream.close();
			outputStreamWriter.close();
			
			em.close();
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
