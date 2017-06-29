package com.algaworks.pedidovenda.service;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.time.DateUtils;

import com.algaworks.pedidovenda.model.Despesa;
import com.algaworks.pedidovenda.repository.DespesaDAO;
import com.algaworks.pedidovenda.util.Datas;
import com.algaworks.pedidovenda.util.jpa.Transactional;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

public class DespesaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DespesaDAO despesaDAO;

	@Transactional
	public Despesa salvar(Despesa despesa) throws ParseException {

		if (despesa != null) {
			validaDespesa(despesa);
			FacesUtil.InfoMessage("Despesa cadastrada com sucesso");
		} else {
			FacesUtil.ErrorMessage("Erro ao tentar cadastrar despesa");
		}

		return despesa;
	}

	public List<Despesa> pesquisaPorMes(int mes) {
		return despesaDAO.pesquisaPorMes(mes);
	}

	public void verificaDataVencimento(Despesa despesa) {
		if (despesa.getDataPrimeiraParcela().before(new Date())) {
			FacesUtil.AvisoMessage("A data do vencimento ja passou");
		}
	}

	public void verificaNumeroParcela(Despesa despesa) {
		if (despesa.getQuantidadeParcela() <= 0)
			FacesUtil.AvisoMessage("o mimino que pode ser parcelado é 1");
	}

	public Despesa validaDespesa(Despesa despesa) throws ParseException {
		if (despesa.isParcelado()) {
			despesa.setMostrarNoMes(false);
			verificaNumeroParcela(despesa);
			//verificaDataVencimento(despesa);

		}
		
		if (!despesa.isParcelado())
			despesa = this.despesaDAO.salvar(despesa);
		else {
			// registro no banco para mostrar a quantidade parcelas
			this.despesaDAO.salvar(despesa);

			if (Datas.pegarDia(despesa.getData()) < despesa.getCartao().getMelhorDiaCartao()) {
				creditaNoMes(despesa);
			} else {
				creditaMesSeguinte(despesa);
			}
		}
		return despesa;

	}
	
	public void creditaNoMes(Despesa despesa) throws ParseException{
		int p = 1;
		Date primeiraParcela = this.dataPrimeiraParcela(despesa);
		for (int i = 0; i < despesa.getQuantidadeParcela(); i++) {
			Despesa despesa1 = new Despesa();
			//antes
			//despesa1.setData(DateUtils.addMonths(despesa.getDataPrimeiraParcela(), i));
			despesa1.setData(DateUtils.addMonths(primeiraParcela, i));
			despesa1.setValor(despesa.getValor() / despesa.getQuantidadeParcela());
			despesa1.setDescricao("parcela " + (p++) + " de " + despesa.getQuantidadeParcela() + " - "
					+ despesa.getDescricao());
			despesa1.setCategoria(despesa.getCategoria());
			despesa1.setMostrarNoMes(true);
			despesaDAO.salvar(despesa1);
		}
	}
	
	public void creditaMesSeguinte(Despesa despesa) throws ParseException{
		int p = 1;
		Date primeiraParcela = this.dataPrimeiraParcela(despesa);
		for (int i = 1; i <= despesa.getQuantidadeParcela(); i++) {
			Despesa despesa1 = new Despesa();
			//antes
			//despesa1.setData(DateUtils.addMonths(despesa.getDataPrimeiraParcela(), p++));
			despesa1.setData(DateUtils.addMonths(primeiraParcela, p++));
			despesa1.setValor(despesa.getValor() / despesa.getQuantidadeParcela());
			despesa1.setDescricao("parcela " + i + " de " + despesa.getQuantidadeParcela() + " - "
					+ despesa.getDescricao());
			despesa1.setCategoria(despesa.getCategoria());
			despesa1.setMostrarNoMes(true);
			despesaDAO.salvar(despesa1);
		}
	}
	
	public Date dataPrimeiraParcela(Despesa despesa) throws ParseException{
		String dia = String.valueOf(despesa.getCartao().getDiaVencimento());
		String mes = String.valueOf(Datas.pegarMes(despesa.getData()));
		String ano = String.valueOf(Datas.pegarAno(despesa.getData()));
		System.out.println(dia+"/"+mes+"/"+ano);
		return Datas.informarData(dia+"/"+mes+"/"+ano);
		
	}

}
