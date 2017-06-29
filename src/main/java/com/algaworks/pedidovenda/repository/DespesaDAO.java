package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.algaworks.pedidovenda.model.Despesa;

public class DespesaDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	public Despesa porId(Long id){
		return em.find(Despesa.class, id);
	}
	
	
	public Despesa salvar(Despesa despesa){
		return em.merge(despesa);
	}
	
	@SuppressWarnings("unchecked")
	public List<Despesa> pesquisaPorMes(int mes){
				
		String jpql = "from Despesa d where date_part('Month',d.data) = :mes and"
				+ " date_part('year',d.data) = date_part('year',current_date) and"
				+ " d.mostrarNoMes = true";
		Query query = em.createQuery(jpql, Despesa.class);
		query.setParameter("mes", mes);
		return query.getResultList();
		
	}
	
	public Double totalMes(int mes){
		String jpql = "select sum(d.valor) from Despesa d where date_part('Month',d.data) = :mes and"
				+ " date_part('year',d.data) = date_part('year',current_date) and"
				+ " d.mostrarNoMes = true";
		Query query = em.createQuery(jpql);
		query.setParameter("mes", mes);
		return (Double) query.getSingleResult();
	}
	
}
