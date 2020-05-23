package com.algaworks.algafood.jpa;

import java.util.List;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ConsultaConzinhaMain {

  public static void main(String[] args) {
    ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
      .web(WebApplicationType.NONE)
      .run(args);
    
      CozinhaRepository cadastroCozinha =  applicationContext.getBean(CozinhaRepository.class);
      List<Cozinha> cozinhas = cadastroCozinha.todas();

      for (Cozinha cozinha : cozinhas) {
        System.out.println(cozinha.getNome());  
      }
  }
  
}