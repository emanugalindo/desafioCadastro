package com.example.springboot;

import com.example.springboot.controllers.FormController;
import com.example.springboot.models.FormModel;
import com.example.springboot.services.FormService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FormController.class)
public class FormControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FormService formService;

    @Test
    void shouldListAllQuestion() throws Exception {
        List<FormModel> questions = Arrays.asList(
                new FormModel("Pergunta 1"),
                new FormModel("Pergunta 2")
        );
        when(formService.listAllQuestions()).thenReturn(questions);

        mockMvc.perform(get("/desafioCadastro/form"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].questionText").value("Pergunta 1"))
                .andExpect(jsonPath("$[1].questionText").value("Pergunta 2"));
        verify(formService, times(1)).listAllQuestions();
    }

    @Test
    void shouldFindQuestionById() throws Exception {
        FormModel question = new FormModel("Qual o nome do pet?");
        question.setId(1L);
        when(formService.findById(1L)).thenReturn(question);

        mockMvc.perform(get("/desafioCadastro/form/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.questionText").value("Qual o nome do pet?"));

        verify(formService, times(1)).findById(1L);
    }

    @Test
    void shouldCreateNewQuestion() throws Exception {
        FormModel newQuestion = new FormModel("Nova pergunta");
        FormModel savedQuestion = new FormModel("Nova pergunta");
        savedQuestion.setId(1L);

        when(formService.createNewQuestion("Nova pergunta")).thenReturn(savedQuestion);

        mockMvc.perform(post("/desafioCadastro/form")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newQuestion)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.questionText").value("Nova pergunta"));


        verify(formService, times(1)).createNewQuestion("Nova pergunta");
    }

    @Test
    void shouldUpdateQuestion() throws Exception {
        FormModel updatedQuestion = new FormModel("Pergunta atualizada");
        updatedQuestion.setId(1L);

        when(formService.updateQuestion(eq(1L), eq("Pergunta atualizada")))
                .thenReturn(updatedQuestion);

        mockMvc.perform(put("/desafioCadastro/form/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedQuestion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.questionText").value("Pergunta atualizada"));

        verify(formService, times(1)).updateQuestion(1L, "Pergunta atualizada");
    }

    @Test
    void shouldDeleteQuestion() throws Exception {
        doNothing().when(formService).deleteQuestion(1L);

        mockMvc.perform(delete("/desafioCadastro/form/1"))
                .andExpect(status().isNoContent());

        verify(formService, times(1)).deleteQuestion(1L);
    }
}
