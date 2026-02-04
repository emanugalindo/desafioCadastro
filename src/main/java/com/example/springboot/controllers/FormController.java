package com.example.springboot.controllers;

import com.example.springboot.models.FormModel;
import com.example.springboot.services.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/desafioCadastro/form")
public class FormController {

    @Autowired
    private FormService formService;

    @GetMapping
    public ResponseEntity<List<FormModel>> getFormQuestions() {
        return ResponseEntity.ok(formService.listAllQuestions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormModel> getQuestionById(@PathVariable Long id) {
        return ResponseEntity.ok(formService.findById(id));
    }

    @PostMapping
    public ResponseEntity<FormModel> createNewQuestion(@RequestBody FormModel formModel) {
        FormModel saved = formService.createNewQuestion(formModel.getQuestionText());
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormModel> updateQuestion(
            @PathVariable Long id,
            @RequestBody FormModel formModel) {
        FormModel updated = formService.updateQuestion(id, formModel.getQuestionText());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        formService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}