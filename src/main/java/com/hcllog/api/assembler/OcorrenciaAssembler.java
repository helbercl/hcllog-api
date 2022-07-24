package com.hcllog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.hcllog.api.domain.model.Ocorrencia;
import com.hcllog.api.domain.model.input.OcorrenciaInput;
import com.hcllog.api.representationmodel.outuput.OcorrenciaModelOutuput;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OcorrenciaAssembler {

	private ModelMapper modelMapper;

	// conversão para model(apresentação para o consumer)
	public OcorrenciaModelOutuput toModel(Ocorrencia ocorrencia) {
		return modelMapper.map(ocorrencia, OcorrenciaModelOutuput.class);

	}

	// conversão para uma lista de model(apresentação para o consumer)
	public List<OcorrenciaModelOutuput> toCollectionListModel(List<Ocorrencia> listaOcorrencia) {
		return listaOcorrencia.stream().map(this::toModel).collect(Collectors.toList());

	}

	// conversão para uma entity(inserção na base)
	public Ocorrencia toEntity(OcorrenciaInput ocorrenciaInput) {
		return modelMapper.map(ocorrenciaInput, Ocorrencia.class);
	}

}
