package com.hcllog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.hcllog.api.domain.model.Entrega;
import com.hcllog.api.domain.model.input.EntregaInput;
import com.hcllog.api.representationmodel.outuput.EntregaModelOutuput;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EntregaAssembler {

	// Não é componente do Spring por isso nao injeta
	private ModelMapper modelMapper;

	// conversão para model(apresentação para o consumer)
	public EntregaModelOutuput toModel(Entrega entrega) {
		return modelMapper.map(entrega, EntregaModelOutuput.class);
	}

	// conversão para uma lista de model(apresentação para o consumer)
	public List<EntregaModelOutuput> toListCollectionModel(List<Entrega> listaEntrega) {
		return listaEntrega.stream().map(this::toModel).collect(Collectors.toList());
	}

	// conversão para uma entity(inserção na base)
	public Entrega toEntity(EntregaInput entregaInput) {
		return modelMapper.map(entregaInput, Entrega.class);
	}

}
