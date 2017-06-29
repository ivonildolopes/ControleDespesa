package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Despesa;
import com.algaworks.pedidovenda.service.DespesaService;
import com.algaworks.pedidovenda.util.Datas;

@Named
@ViewScoped
public class PesquisaDespesaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DespesaService despesaService;

	private Despesa despesaSelecionado;

	private List<Despesa> listaDespesa = new ArrayList<Despesa>();
	
	private int mes = Datas.pegarMes(new Date());
	
	private Double valorTotalMes = 0.0;

	public PesquisaDespesaBean() {
		//listaDespesa = despesaService.pesquisaPorMes(mes);
	}

	public void pesquisar() {
		listaDespesa = despesaService.pesquisaPorMes(mes);
		
		this.valorTotalMes = 0.0;
		for(Despesa despesa : listaDespesa){
			valorTotalMes +=despesa.getValor();
		}
	}
	
	
	// GET AND SET
	public List<Despesa> getListaDespesa() {
		return listaDespesa;
	}

	public void setListaDespesa(List<Despesa> listaDespesa) {
		this.listaDespesa = listaDespesa;
	}

	public Despesa getDespesaSelecionado() {
		return despesaSelecionado;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public Double getValorTotalMes() {
		return valorTotalMes;
	}

	public void setValorTotalMes(Double valorTotalMes) {
		this.valorTotalMes = valorTotalMes;
	}
}
