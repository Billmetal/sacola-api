package br.com.ifooddevweek.sacola.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifooddevweek.sacola.models.Item;
import br.com.ifooddevweek.sacola.models.Sacola;
import br.com.ifooddevweek.sacola.resource.dto.ItemDto;
import br.com.ifooddevweek.sacola.service.SacolaService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Api(value="/ifood-devweek/sacolas")
@RestController
@RequestMapping("/ifood-devweek/sacolas")
@RequiredArgsConstructor
public class SacolaController {
	
	
	private final SacolaService sacolaService;
	
	@PostMapping
	public Item incluirItemNaSacola(@RequestBody ItemDto itemDto) {
		return sacolaService.incluirItemNaSacola(itemDto);
	}
	
	@GetMapping("/{id}")
	public Sacola verSacola(@PathVariable("id") Long id) {
		return sacolaService.verSacola(id);
	}
	
	@PatchMapping("/fecharSacola/{id}")
	public Sacola fecharSacola(@PathVariable("id") Long id,@RequestParam("formaPagamento") int formaPagamento) {
		return sacolaService.fecharSacola(id, formaPagamento);
	}
}
