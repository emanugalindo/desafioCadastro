package view;

public class MenuInicialView {
    public void exibirMenuInicial() {
        System.out.println("\n====================== MENU INICIAL ======================");
        System.out.println("1 - Cadastrar um novo pet");
        System.out.println("2 - Alterar dados de um pet cadastrado");
        System.out.println("3 - Deletar um pet cadastrado");
        System.out.println("4 - Listar todos os pets cadastrados");
        System.out.println("5 - Listar pets por algum critério (idade, nome, raça)");
        System.out.println("6 - Sair");
        System.out.println("Digite a opção escolhida:");
    }

    public void exibirSeparador() {
        System.out.println("==========================================================");
    }

    public void exibirErroCaracterInvalido() {
        exibirSeparador();
        System.out.println("Não digite letras ou caracteres especiais");
    }

    public void exibirErroNumeroInvalido() {
        System.out.println("Digite um número de 1 a 6.");
    }
}
