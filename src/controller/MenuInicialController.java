package controller;

import view.MenuInicialView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuInicialController {
    private int opcao = 1;
    private MenuInicialView menuInicial = new MenuInicialView();
    private Scanner sc = new Scanner(System.in);
    PetController pc = new PetController();

    public void iniciarMenu() {
        while (opcao != 6) {
            try {
                menuInicial.exibirMenuInicial();
                opcao = sc.nextInt();
                menuInicial.exibirSeparador();
                switch (opcao) {
                    case 1:
                        pc.criarPet();
                        break;
                    case 2:
                        pc.alterarPet();
                        break;
                    case 3:
                        System.out.println("Deletando");
                        break;
                    case 4:
                        pc.listarTodosPets();
                        break;
                    case 5:
                        pc.buscarPet();
                        break;
                    case 6:
                        System.out.println("Saindo....");
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            } catch (InputMismatchException e) {
                menuInicial.exibirErroCaracterInvalido();
                sc.nextLine();
                opcao = 1;
            } catch (IllegalArgumentException e) {
                menuInicial.exibirErroNumeroInvalido();
                opcao = 1;
            }
        }
    }
}
