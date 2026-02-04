package com.example.springboot.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_FORM")
public class FormModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String questionText;

    @Column(nullable = false)
    private boolean isDefault;

    public FormModel() {
    }

    public FormModel(String questionText) {
        this.questionText = questionText;
        isDefault = false;
    }

    public FormModel(String questionText, boolean isDefault) {
        this.questionText = questionText;
        this.isDefault = isDefault;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        if (questionText == null || questionText.isEmpty()) {
            throw new IllegalArgumentException("Não é possível adicionar uma pergunta em branco");
        }
        if (this.isDefault) {
            throw new IllegalArgumentException("Não é possível alterar o texto das perguntas padrão");
        }

        this.questionText = questionText;
    }

    public boolean isDefault() {
        return isDefault;
    }
}
