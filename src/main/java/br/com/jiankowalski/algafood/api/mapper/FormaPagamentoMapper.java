package br.com.jiankowalski.algafood.api.mapper;


import br.com.jiankowalski.algafood.api.model.FormaPagamentoModel;
import br.com.jiankowalski.algafood.api.model.input.FormaPagamentoIdInput;
import br.com.jiankowalski.algafood.api.model.input.FormaPagamentoInput;
import br.com.jiankowalski.algafood.domain.model.FormaPagamento;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Set;

@Mapper
public interface FormaPagamentoMapper {

    List<FormaPagamentoModel> toCollectionModel(List<FormaPagamento> formasPagamento);

    FormaPagamentoModel toModel(FormaPagamento buscar);

    FormaPagamento inputToDomain(FormaPagamentoInput formaPagamentoInput);

    List<FormaPagamentoModel> toCollectionModel(Set<FormaPagamento> formasPagamento);

    void update(@MappingTarget FormaPagamento formaPagamentoAtual, FormaPagamentoInput formaPagamentoInput);

    FormaPagamento formaPagamentoIdInputToFormaPagamento(FormaPagamentoIdInput formaPagamentoIdInput);
}
