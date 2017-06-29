package com.algaworks.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.pedidovenda.model.Cartao;
import com.algaworks.pedidovenda.repository.CartaoDAO;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Cartao.class,value="cartaoConverter")
public class CartaoConverter implements Converter {

	// @Inject
	private CartaoDAO cartaoDAO;

	public CartaoConverter() {
		cartaoDAO = CDIServiceLocator.getBean(CartaoDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Cartao retorno = null;

		if (value != null) {
			Integer id = new Integer(value);
			retorno = cartaoDAO.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Cartao cartao = (Cartao) value;
			return cartao.getId() == null ? null : cartao.getId().toString();
		}

		return "";
	}

}
