package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.algaworks.pedidovenda.model.Cartao;

public class CartaoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	public Cartao porId(Integer id){
		return em.find(Cartao.class, id);
	}
	
	
	public Cartao salvar(Cartao cartao){
		return em.merge(cartao);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cartao> listarTodos(){
		String jpql = "from Cartao order by nomeCartao";
		Query query = em.createQuery(jpql,Cartao.class);
		return query.getResultList();
	}
	
}
