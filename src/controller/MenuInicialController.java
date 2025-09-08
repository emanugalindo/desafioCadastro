package controller;

import view.MenuInicialView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuInicialController {
    private int opcao = 1;
    private MenuInicialView menuInicial = new MenuInicialView();
    private Scanner sc = new Scanner(System.in);

    public void iniciarMenu() {
        while (opcao != 6) {
            try {
                menuInicial.exibirMenuInicial();
                opcao = sc.nextInt();
                menuInicial.exibirSeparador();
                switch (opcao) {
                    case 1:
                        System.out.println("Cadastrando");
                        break;
                    case 2:
                        System.out.println("Alterando");
                        break;
                    case 3:
                        System.out.println("Deletando");
                        break;
                    case 4:
                        System.out.println("Listando pets");
                        break;
                    case 5:
                        System.out.println("Listando pets por critério");
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
