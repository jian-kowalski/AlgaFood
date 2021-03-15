package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.Model.ProdutoModel;
import com.algaworks.algafood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoModelAssembler {

    public ProdutoModel toModel(Produto produto) {
        var modelMapper = new ModelMapper();
        return modelMapper.map(produto, ProdutoModel.class);
    }

    public List<ProdutoModel> toColletion(List<Produto> produtos) {
        return produtos.stream().map(this::toModel).collect(Collectors.toList());
    }
}
