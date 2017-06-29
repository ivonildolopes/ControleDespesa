package com.algaworks.pedidovenda.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "despesas")
public class Despesa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	@NotNull(message = "informe o valor da despesa")
	private double valor;
	
	@Column(name = "pago")
	private boolean isPago;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	private String descricao;
	
	private String categoria;
	
	@Column(name = "parcelado")
	private boolean isParcelado;
	
	private int quantidadeParcela;
	
	@Temporal(TemporalType.DATE)
	private Date dataPrimeiraParcela;
	
	private boolean mostrarNoMes = true;

	@ManyToOne
	@JoinColumn(name="id_cartao", referencedColumnName="id")
	private Cartao cartao;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public boolean isParcelado() {
		return isParcelado;
	}

	public void setParcelado(boolean isParcelado) {
		this.isParcelado = isParcelado;
	}

	public int getQuantidadeParcela() {
		return quantidadeParcela;
	}

	public void setQuantidadeParcela(int quantidadeParcela) {
		this.quantidadeParcela = quantidadeParcela;
	}

	public boolean isMostrarNoMes() {
		return mostrarNoMes;
	}

	public void setMostrarNoMes(boolean mostrarNoMes) {
		this.mostrarNoMes = mostrarNoMes;
	}

	public boolean isPago() {
		return isPago;
	}

	public void setPago(boolean isPago) {
		this.isPago = isPago;
	}

	public Date getDataPrimeiraParcela() {
		return dataPrimeiraParcela;
	}

	public void setDataPrimeiraParcela(Date dataPrimeiraParcela) {
		this.dataPrimeiraParcela = dataPrimeiraParcela;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
	
	
}
