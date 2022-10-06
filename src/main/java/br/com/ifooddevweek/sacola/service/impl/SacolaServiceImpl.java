package br.com.ifooddevweek.sacola.service.impl;

import org.springframework.stereotype.Service;

import br.com.ifooddevweek.sacola.enumeration.FormaPagamento;
import br.com.ifooddevweek.sacola.models.Item;
import br.com.ifooddevweek.sacola.models.Restaurante;
import br.com.ifooddevweek.sacola.models.Sacola;
import br.com.ifooddevweek.sacola.repository.ProdutoRepository;
import br.com.ifooddevweek.sacola.repository.SacolaRepository;
import br.com.ifooddevweek.sacola.resource.dto.ItemDto;
import br.com.ifooddevweek.sacola.service.SacolaService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SacolaServiceImpl implements SacolaService {
	
	private final SacolaRepository sacolaRepository;
	
	private final ProdutoRepository produtoRepository;

	@Override
	public Item incluirItemNaSacola(ItemDto itemDto) {
		Sacola sacola = verSacola(itemDto.getIdSacola());
		
		if(sacola.isFechada()) {
			throw new RuntimeException("Esta sacola está fechada !");
		}
		
		Item item = Item.builder()
			.quantidade(itemDto.getQuantidade())
			.sacola(sacola)
			.produto(produtoRepository.findById(itemDto.getProdutoId()).orElseThrow(() -> {
				throw new RuntimeException("Esse produto não existe !!");
			}))
			.build();
		
		if(sacola.getItens().isEmpty()) {
			sacola.getItens().add(item);
		} else {
			Restaurante restauranteAtual = sacola.getItens().get(0).getProduto().getRestaurante();
			Restaurante restauranteNovoItem = item.getProduto().getRestaurante();
			if(restauranteAtual.equals(restauranteNovoItem)) {
				sacola.getItens().add(item);
			} else {
				throw new RuntimeException("Não é possível adicionar produtos de restaurantes diferentes !!");
			}
		}
		
		sacola.setValorTotal(sacola.getItens().stream()
				.mapToDouble(itemDaSacola -> itemDaSacola.getProduto().getValorUnitario() * itemDaSacola.getQuantidade()).sum());
		
		sacolaRepository.save(sacola);
		return item;
	}

	@Override
	public Sacola verSacola(Long id) {
		return sacolaRepository.findById(id).orElseThrow(() -> {
			throw new RuntimeException("Essa sacola não existe !!");
		});
	}

	@Override
	public Sacola fecharSacola(Long id, int formaPagamento) {
		Sacola sacola = verSacola(id);
		if(sacola.getItens().isEmpty()) {
			throw new RuntimeException("Inclua itens na sacola !");
		}
		sacola.setFormaPagamento(formaPagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUINACARTAO);
		sacola.setFechada(true);
		return sacolaRepository.save(sacola);		
	}

}
