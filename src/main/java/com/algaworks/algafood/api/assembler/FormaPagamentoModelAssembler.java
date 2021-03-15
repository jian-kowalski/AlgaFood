package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.Model.FormaPagamentoModel;
import com.algaworks.algafood.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormaPagamentoModelAssembler {

    public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(formaPagamento, FormaPagamentoModel.class);
    }

    public List<FormaPagamentoModel> toCollectionModel(Collection<FormaPagamento> formaPagamentos) {
        return formaPagamentos.stream().map(this::toModel).collect(Collectors.toList());
    }
}
