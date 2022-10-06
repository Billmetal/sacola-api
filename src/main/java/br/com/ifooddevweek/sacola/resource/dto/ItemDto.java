package br.com.ifooddevweek.sacola.resource.dto;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Embeddable
public class ItemDto {

	private Long produtoId;
	private int quantidade;
	private Long idSacola;
}
