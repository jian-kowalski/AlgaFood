package com.algaworks.algafood.core.validation.jackson;

import com.algaworks.algafood.api.Model.mixin.CidadeMixin;
import com.algaworks.algafood.api.Model.mixin.CozinhaMixin;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.fasterxml.jackson.databind.module.SimpleModule;

import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule{

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  
  public JacksonMixinModule(){
    setMixInAnnotation(Cidade.class, CidadeMixin.class);
    setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
  }

}
