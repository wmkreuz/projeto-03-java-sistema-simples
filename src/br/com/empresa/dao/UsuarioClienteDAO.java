package br.com.empresa.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import br.com.empresa.exception.BOException;
import br.com.empresa.vo.ClienteVO;
import br.com.empresa.vo.UsuarioClienteVO;
import br.com.empresa.vo.UsuarioVO;

public class UsuarioClienteDAO implements IUsuarioClienteDAO{

	@Override
	public List<UsuarioClienteVO> listarClienteUsuario(UsuarioVO usuario) throws BOException {
		
		EntityManager em = HibernateUtil.getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> criteria = cb.createQuery(Tuple.class);
		
		//Clausula from
		Root<UsuarioClienteVO> UsuCliFrom = criteria.from(UsuarioClienteVO.class);
		Join<UsuarioClienteVO, ClienteVO> clienteFrom = UsuCliFrom.join("clienteVO");
		Join<UsuarioClienteVO, UsuarioVO> usuarioFrom = UsuCliFrom.join("usuarioVO");
		
		Path<BigInteger> usuCliFromFrom_Id = UsuCliFrom.get("id");
		
		//Clausula select
		criteria.multiselect(usuCliFromFrom_Id,clienteFrom,usuarioFrom);
		
		TypedQuery<Tuple> query = em.createQuery(criteria);
		query.setMaxResults(10);
		
		List<Tuple> tuples = query.getResultList();
		
		List<UsuarioClienteVO> usuarioClienteVOs = new ArrayList<UsuarioClienteVO>();
		
		if(tuples != null) {
			for(Tuple tuple : tuples) {
				UsuarioClienteVO usuarioClienteVO = new UsuarioClienteVO();
				usuarioClienteVO.setId(tuple.get(usuCliFromFrom_Id));
				usuarioClienteVO.setUsuarioVO(tuple.get(usuarioFrom));
				usuarioClienteVO.setClienteVO(tuple.get(clienteFrom));
				usuarioClienteVOs.add(usuarioClienteVO);
			}
		}
		
		List<UsuarioClienteVO> filtro = new ArrayList<>();
		
		for (UsuarioClienteVO usuarioClienteVO : usuarioClienteVOs) {
			if(usuarioClienteVO.getUsuarioVO().equals(usuario)) {
				filtro.add(usuarioClienteVO);
			}
		}
		
		//Fecha o entity manager
		em.close();
		
		return filtro;
	}

	@Override
	public int buscarQuantidadeClienteUsuario(UsuarioVO usuario) throws BOException {
		
		int qtd = 0;
		
		EntityManager em = HibernateUtil.getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> criteria = cb.createQuery(Tuple.class);
		
		//Clausula from
		Root<UsuarioClienteVO> UsuCliFrom = criteria.from(UsuarioClienteVO.class);
		Join<UsuarioClienteVO, ClienteVO> clienteFrom = UsuCliFrom.join("clienteVO");
		Join<UsuarioClienteVO, UsuarioVO> usuarioFrom = UsuCliFrom.join("usuarioVO");
		
		Path<BigInteger> usuCliFromFrom_Id = UsuCliFrom.get("id");
		
		//Clausula select
		criteria.multiselect(usuCliFromFrom_Id,clienteFrom,usuarioFrom);
		
		TypedQuery<Tuple> query = em.createQuery(criteria);
		query.setMaxResults(10);
		
		List<Tuple> tuples = query.getResultList();
		
		List<UsuarioClienteVO> usuarioClienteVOs = new ArrayList<UsuarioClienteVO>();
		
		if(tuples != null) {
			for(Tuple tuple : tuples) {
				UsuarioClienteVO usuarioClienteVO = new UsuarioClienteVO();
				usuarioClienteVO.setId(tuple.get(usuCliFromFrom_Id));
				usuarioClienteVO.setUsuarioVO(tuple.get(usuarioFrom));
				usuarioClienteVO.setClienteVO(tuple.get(clienteFrom));
				usuarioClienteVOs.add(usuarioClienteVO);
			}
		}
		
		for (UsuarioClienteVO usuarioClienteVO : usuarioClienteVOs) {
			if(usuarioClienteVO.getUsuarioVO().equals(usuario)) {
				qtd++;
			}
		}
		
		em.close();
		
		return qtd;
	}
	
}
