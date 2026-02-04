package com.example.springboot;

import com.example.springboot.models.FormModel;
import com.example.springboot.repositories.FormRepository;
import com.example.springboot.services.FormService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FormServiceTest {
    @Mock
    private FormRepository formRepository;

    @InjectMocks
    private FormService formService;

    private FormModel formModel;

    @BeforeEach
    void setUpt() {
        formModel = new FormModel("Qual o nome do pet?");
        formModel.setId(1L);
    }

    @Test
    void shouldCreateNewQuestion() {
        String questionText = "Qual o nome do pet?";
        FormModel expected = new FormModel(questionText);
        when(formRepository.save(any(FormModel.class))).thenReturn(expected);

        FormModel result = formService.createNewQuestion(questionText);

        assertNotNull(questionText);
        assertEquals(questionText, result.getQuestionText());
        verify(formRepository, times(1)).save(any(FormModel.class));
    }

    @Test
    void shouldNotCreateNewQuestion() {
        assertThrows(IllegalArgumentException.class, () -> {
            formService.createNewQuestion("");
        });

        verify(formRepository, never()).save(any(FormModel.class));
    }

    @Test
    void shouldNotCreateQuestionWithNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            formService.createNewQuestion(null);
        });

        verify(formRepository, never()).save(any(FormModel.class));
    }

    @Test
    void shouldListAllQuestion() {
        List<FormModel> expected = Arrays.asList(
                new FormModel("Pergunta 1"),
                new FormModel("Pergunta 2")
        );

        when(formRepository.findAll()).thenReturn(expected);

        List<FormModel> result = formService.listAllQuestions();

        assertEquals(2, result.size());
        verify(formRepository, times(1)).findAll();
    }

    @Test
    void shouldSearchQuestionById() {
        when(formRepository.findById(1L)).thenReturn(Optional.of(formModel));

        FormModel result = formService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Qual o nome do pet?", result.getQuestionText());
        verify(formRepository, times(1)).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenDoNotFindQuestion() {
        when(formRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            formService.findById(999L);
        });
    }

    @Test
    void shouldUpdateQuestion() {
        FormModel notDefaultQuestion = new FormModel("Pergunta customizada");
        notDefaultQuestion.setId(8L);

        String questionTextUpdated = "Pergunta atualizada";

        when(formRepository.findById(8L)).thenReturn(Optional.of(notDefaultQuestion));
        when(formRepository.save(any(FormModel.class))).thenAnswer(i -> i.getArguments()[0]);

        FormModel result = formService.updateQuestion(8L, questionTextUpdated);

        assertEquals(questionTextUpdated, result.getQuestionText());
        verify(formRepository, times(1)).save(notDefaultQuestion);
    }

    @Test
    void shouldNotUpdateDefaultQuestion() {
        FormModel defaultQuestion = new FormModel("Pergunta padrão", true);
        defaultQuestion.setId(1L);

        when(formRepository.findById(1L)).thenReturn(Optional.of(defaultQuestion));

        assertThrows(RuntimeException.class, () -> {
            formService.updateQuestion(1L, "Novo texto");
        });

        verify(formRepository, never()).save(any(FormModel.class));
    }

    @Test
    void shouldDeleteQuestion() {
        FormModel notDefaultQuestion = new FormModel("Pergunta customizada");
        notDefaultQuestion.setId(8L);

        when(formRepository.findById(8L)).thenReturn(Optional.of(notDefaultQuestion));

        formService.deleteQuestion(8L);

        verify(formRepository, times(1)).delete(notDefaultQuestion);
    }

    @Test
    void shouldNotDeleteDefaultQuestion() {
        FormModel notdefaultquestion = new FormModel("Pergunta padrão", true);
        notdefaultquestion.setId(1L);

        when(formRepository.findById(1L)).thenReturn(Optional.of(notdefaultquestion));

        assertThrows(IllegalArgumentException.class, () -> {
            formService.deleteQuestion(1L);
        });

        verify(formRepository, never()).delete(any(FormModel.class));
    }
}
