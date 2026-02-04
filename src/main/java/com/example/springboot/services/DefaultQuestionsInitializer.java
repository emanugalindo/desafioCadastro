package com.example.springboot.services;

import com.example.springboot.models.FormModel;
import com.example.springboot.repositories.FormRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public final class DefaultQuestionsInitializer {
    private static final Logger log = LoggerFactory.getLogger(DefaultQuestionsInitializer.class);
    @Autowired
    private FormRepository formRepository;

    @PostConstruct
    public void initDefaultQuestions() {
        if (formRepository.countByIsDefault(true) == 0) {
            formRepository.saveAll(Arrays.asList(
                    new FormModel("Qual o nome e sobrenome do pet?", true),
                    new FormModel("Qual o tipo do pet (Cachorro/Gato)?", true),
                    new FormModel("Qual o sexo do animal?", true),
                    new FormModel("Qual endereço e bairro que ele foi encontrado?", true),
                    new FormModel("Qual a idade aproximada do pet?", true),
                    new FormModel("Qual o peso aproximado do pet?", true),
                    new FormModel("Qual a raça do pet?", true)
            ));
        log.info("Perguntas padrão criadas.");
        }
    }
}
