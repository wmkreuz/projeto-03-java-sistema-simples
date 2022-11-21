package br.com.empresa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.empresa.vo.UsuarioVO;

public class UsuarioDAO implements IUsuarioDAO{

	@Override
	public UsuarioVO buscarUsuario(String login, String senha) {

		EntityManager em = HibernateUtil.getEntityManager();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UsuarioVO> criteria = cb.createQuery(UsuarioVO.class);
		
		//Cláusula From
		Root<UsuarioVO> produtoFrom = criteria.from(UsuarioVO.class);
		
		//Atribuindo as cláusulas à consulta
		criteria.select(produtoFrom);
		
		TypedQuery<UsuarioVO> query = em.createQuery(criteria);
		List<UsuarioVO> listaUsuarios = query.getResultList();
		
		for (UsuarioVO usuarioVO : listaUsuarios) {
			if(usuarioVO.getLogusu().equals(login) && usuarioVO.getSenusu().equals(senha)) {
				return usuarioVO;
			}
		}
		return null;
	}

}
