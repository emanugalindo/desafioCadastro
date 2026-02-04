package com.example.springboot.services;

import com.example.springboot.models.FormModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class MenuService implements CommandLineRunner {
    @Autowired
    private FormService formService;


    private int option;
    private final Scanner sc = new Scanner(System.in);
    private String questionText;

    @Override
    public void run(String... args) throws Exception {
        menu();
    }

    private void menu() {
        do {
            System.out.println("\n========== Sistema de Cadastro de Pets ==========");
            System.out.println("1 - Iniciar o sistema de cadastro de PETS");
            System.out.println("2 - Iniciar o sistema de formulário");
            System.out.println("3 - Sair");
            System.out.println("Escolha:");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 -> System.out.println("Iniciando sistema de cadastro");
                case 2 -> formMenu();
                case 3 -> {
                    System.out.println("Finalizando o programa");
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida. Tente novamente");
            }
        }
        while (option != 3);
    }

    private void formMenu() {
        do {
            System.out.println("\n========== Menu do Formulário ==========");
            System.out.println("1 - Criar nova pergunta");
            System.out.println("2 - Alterar pergunta existente");
            System.out.println("3 - Excluir pergunta existente");
            System.out.println("4 - Voltar para o menu inicial");
            System.out.println("5 - Sair");
            System.out.println("Escolha:");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 -> createNewQuestion();
                case 2 -> updateQuestion();
                case 3 -> deleteQuestion();
                case 4 -> {
                    return;
                }
                case 5 -> {
                    System.out.println("Finalizando o programa");
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida!!!");
            }
        }
        while (option != 5);
    }

    private void createNewQuestion() {
        System.out.println("Digite o texto da nova pergunta:");
        questionText = sc.nextLine();

        try {
            formService.createNewQuestion(questionText);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro:" + e.getMessage());
        }
    }

    private List<FormModel> listAllQuestions() {
        List<FormModel> questions = formService.listAllQuestions();

        if (questions.isEmpty()) System.out.println("Nenhuma pergunta encontrada");
        ;

        for (int i = 0; i < questions.size(); i++) {
            FormModel q = questions.get(i);
            System.out.printf("%d - %s\n", (i + 1), q.getQuestionText());
        }

        return questions;
    }

    private void updateQuestion() {
        List<FormModel> questions = listAllQuestions();

        System.out.println("Digite o número da pergunta a ser alterada:");
        option = sc.nextInt();
        sc.nextLine();

        if (option < 1 || option > questions.size()) {
            System.out.println("Opção inválida!!!");
            return;
        }

        if (option >= 1 && option <= 7) {
            System.out.printf("Não é possível alterar uma pergunta padrão");
            return;
        }

        Long id = questions.get(option - 1).getId();

        try {
            FormModel question = formService.findById(id);
            System.out.println("Pergunta atual: " + question.getQuestionText());

            System.out.println("Digite a nova pergunta: ");
            questionText = sc.nextLine();

            formService.updateQuestion(id, questionText);
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void deleteQuestion() {
        List<FormModel> questions = formService.listAllQuestions();
        listAllQuestions();

        System.out.println("Digite o número da pergunta a ser deletada:");
        option = sc.nextInt();
        sc.nextLine();

        if (option < 1 || option > questions.size()) {
            System.out.println("Opção inválida!!!");
            return;
        }

        if (option <= 7) {
            System.out.println("Não é possível deletar uma pergunta padrão");
            return;
        }

        Long id = questions.get(option - 1).getId();

        try {
            FormModel question = formService.findById(id);
            System.out.println("Tem certeza que deseja excluir: " + question.getQuestionText() + "? (S/N)");
            String confirm = sc.nextLine();

            if (confirm.equalsIgnoreCase("S")) {
                formService.deleteQuestion(id);
                System.out.println("Pergunta excluída com sucesso!");
            } else {
                System.out.println("Operação cancelada.");
            }
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
