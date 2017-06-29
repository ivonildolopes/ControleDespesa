package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Cartao;
import com.algaworks.pedidovenda.model.Despesa;
import com.algaworks.pedidovenda.repository.CartaoDAO;
import com.algaworks.pedidovenda.service.DespesaService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@SessionScoped
public class CadastroDespesaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Despesa despesa;

	@Inject
	private CartaoDAO cartaoDao;
	
	private List<Cartao> listaCartao;
	
	@Inject
	private DespesaService despesaService;
	
	public CadastroDespesaBean(){
		this.limpar();
	}
	
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			this.listaCartao = this.cartaoDao.listarTodos();
		}
	}

	private void limpar() {
		this.despesa = new Despesa();
	}
	
	public void salvar() throws ParseException{
		this.despesaService.salvar(despesa);
		this.limpar();
	}

	public boolean mostraCampo(){
		boolean retorno = false;
		
		if(despesa.isParcelado())
			retorno = true;
		
		return retorno;
	}
	
	//get and set
	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}

	public List<Cartao> getListaCartao() {
		return listaCartao;
	}

	public void setListaCartao(List<Cartao> listaCartao) {
		this.listaCartao = listaCartao;
	}
	
}
