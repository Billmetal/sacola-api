package br.com.ifooddevweek.sacola.service;


import br.com.ifooddevweek.sacola.models.Item;
import br.com.ifooddevweek.sacola.models.Sacola;
import br.com.ifooddevweek.sacola.resource.dto.ItemDto;

public interface SacolaService {

	Item incluirItemNaSacola(ItemDto itemDto); 
	
	Sacola verSacola(Long id);
	
	Sacola fecharSacola(Long id,int formaPagamento);
}
