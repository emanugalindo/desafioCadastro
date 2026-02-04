package com.example.springboot.services;

import com.example.springboot.models.FormModel;
import com.example.springboot.repositories.FormRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormService {

    private static final Logger log = LoggerFactory.getLogger(FormService.class);

    @Autowired
    private FormRepository formRepository;

    public List<FormModel> listAllQuestions() {
        return formRepository.findAll();
    }

    public FormModel createNewQuestion(String questionText) {
        if (questionText == null || questionText.isBlank()) {
            throw new IllegalArgumentException("O texto da pergunta não pode estar vazio");
        }

        FormModel saved = formRepository.save(new FormModel(questionText));
        log.info("Pergunta criada com sucesso - ID: {}", saved.getId());
        return saved;
    }

    public FormModel findById(Long id) {
        return formRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pergunta não encontrada com ID: " + id));
    }

    public FormModel updateQuestion(Long id, String newQuestionText) {
        FormModel question = findById(id);

        if (newQuestionText == null || newQuestionText.isBlank()) {
            throw new IllegalArgumentException("O texto da pergunta não pode estar vazio");
        }

        question.setQuestionText(newQuestionText);
        FormModel updated = formRepository.save(question);
        log.info("Pergunta atualizada com sucesso - ID: {}", id);
        return updated;
    }

    public void deleteQuestion(Long id) {
        FormModel question = findById(id);

        if (question.isDefault()) {
            throw new IllegalArgumentException("Não é possível deletar perguntas padrão");
        }

        formRepository.delete(question);
        log.info("Pergunta deletada com sucesso - ID: {}", id);
    }
}