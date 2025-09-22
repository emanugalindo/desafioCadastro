package services;

import model.Endereco;
import model.Sexo;
import model.Tipo;
import utils.Constante;
import view.FormularioView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PetService {

    public String verificarNomeValido() {
        try {
            FormularioView.lerPerguntas(1);
            Scanner sc = new Scanner(System.in);
            String nome = sc.nextLine().trim();

            if (nome == null || nome.isEmpty()) {
                return Constante.NAO_INFORMADO;
            }

            if (!nome.contains(" ") || !nome.matches("^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$")) {
                throw new IllegalArgumentException();
            }

            return nome;

        } catch (IllegalArgumentException e) {
            return verificarNomeValido();
        }
    }

    public Tipo verificarTipoValido() {
        try {
            FormularioView.lerPerguntas(2);
            Scanner sc = new Scanner(System.in);
            String tipo = sc.nextLine();

            if (tipo == null || tipo.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }
            if (tipo.equalsIgnoreCase("Cachorro")) {
                return Tipo.CACHORRO;
            }
            if (tipo.equalsIgnoreCase("Gato")) {
                return Tipo.GATO;
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            return verificarTipoValido();
        }
    }

    public Sexo verificarSexoValido() {
        try {
            FormularioView.lerPerguntas(3);
            Scanner sc = new Scanner(System.in);
            String sexo = sc.nextLine();

            if (sexo == null || sexo.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }
            if (sexo.equalsIgnoreCase("Macho")) {
                return Sexo.MACHO;
            }
            if (sexo.equalsIgnoreCase("Femea") || sexo.equalsIgnoreCase("Fêmea")) {
                return Sexo.FEMEA;
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            return verificarSexoValido();
        }
    }

    public Endereco verificarEnderecoValido() {
        try {
            FormularioView.lerPerguntas(4);
            Scanner sc = new Scanner(System.in);
            System.out.println("Digite o número da casa: ");
            String num = sc.nextLine();

            if (num == null || num.trim().isEmpty()) {
                num = Constante.NAO_INFORMADO;
            } else {
                if (!num.trim().matches("^[1-9]\\d*$")) {
                    throw new IllegalArgumentException();
                }
            }

            System.out.println("Digite a cidade: ");
            String cidade = sc.nextLine();
            if (!cidade.matches("^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$") || cidade == null || cidade.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }

            System.out.println("Digite a rua: ");
            String rua = sc.nextLine();
            if (!rua.matches("^[A-Za-zÀ-ÖØ-öø-ÿ0-9\\s,.\\-]+$") || rua == null || rua.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }
            return new Endereco(num, cidade, rua);
        } catch (IllegalArgumentException e) {
            return verificarEnderecoValido();
        }
    }

    public String verificarIdadevalida() {
        try {
            FormularioView.lerPerguntas(5);
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String pontoVirgula = input.replace(",", ".");
            Double idade = Double.parseDouble(pontoVirgula);

            if (idade == null) {
                return Constante.NAO_INFORMADO;
            }
            if (idade > 20 || idade < 0) {
                throw new IllegalArgumentException();
            }

            if (idade < 1) {
                idade *= 10;
                return String.format("%.0f mês(es)", idade);
            }
            return String.format("%.0f ano(s)", idade);
        } catch (NumberFormatException e) {
            return verificarIdadevalida();
        } catch (IllegalArgumentException e) {
            return verificarIdadevalida();
        }
    }

    public String verificarPesoValido() {
        try {
            FormularioView.lerPerguntas(6);
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String pontoVirgula = input.replace(",", ".");
            Double peso = Double.parseDouble(pontoVirgula);

            if (peso == null) {
                return Constante.NAO_INFORMADO;
            }
            if (peso > 60 || peso < 0.5) {
                throw new IllegalArgumentException();
            }
            if (peso < 1) return String.format("%.1fkg", peso);
            return String.format("%.0fkg", peso);
        } catch (NumberFormatException e) {
            return verificarPesoValido();
        } catch (IllegalArgumentException e) {
            return verificarPesoValido();
        }
    }

    public String verificarRacaValida() {
        try {
            FormularioView.lerPerguntas(7);
            Scanner sc = new Scanner(System.in);
            String raca = sc.nextLine();
            if (raca == null || raca.trim().isEmpty()) {
                return Constante.NAO_INFORMADO;
            }
            if (!raca.matches("^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$")) {
                throw new IllegalArgumentException();
            }
            return raca;
        } catch (IllegalArgumentException e) {
            return verificarRacaValida();
        }
    }

    public String tipoAnimal() {
        try {
            System.out.println("Digite o tipo do animal (Cachorro ou Gato): ");
            Scanner sc = new Scanner(System.in);
            String tipo = sc.nextLine();
            if (tipo == null || tipo.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }
            if (tipo.equalsIgnoreCase("Cachorro") || tipo.equalsIgnoreCase("Gato")) {
                return tipo;
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            return tipoAnimal();
        }
    }

    private List<String> buscarTermoString(String termo, int numeroLinha, String tipoAnimal) {
        List<String> arquivosEncontrados = new ArrayList<>();
        try {
            Files.walk(Paths.get("petsCadastrados"))
                    .filter(Files::isRegularFile)
                    .forEach(path -> {
                        try {
                            List<String> linhas = Files.readAllLines(path);
                            if (linhas.size() > 2 && !linhas.get(2).equalsIgnoreCase(tipoAnimal)) {
                                return; // pula para o próximo arquivo
                            }
                            if (numeroLinha < linhas.size() &&
                                    linhas.get(numeroLinha).toLowerCase().contains(termo.toLowerCase())) {

                                String conteudoCompleto = linhas.stream()
                                        .map(linha -> linha.replaceFirst("^\\d+\\s*-\\s*", "")) // Remove "número - "
                                        .collect(Collectors.joining(" - "));

                                arquivosEncontrados.add(conteudoCompleto);
                            }
                        } catch (IOException e) {
                            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
                        }
                    });
        } catch (IOException e) {
            System.out.println("Erro ao acessar os arquivos: " + e.getMessage());
        }
        return arquivosEncontrados;
    }

    private List<String> buscarTermoDouble(double termo, int numeroLinha, String tipoAnimal) {
        List<String> arquivosEncontrados = new ArrayList<>();
        try {
            Files.walk(Paths.get("petsCadastrados"))
                    .filter(Files::isRegularFile)
                    .forEach(path -> {
                        try {
                            List<String> linhas = Files.readAllLines(path);
                            if (linhas.size() > 2 && !linhas.get(2).equalsIgnoreCase(tipoAnimal)) {
                                return; // pula para o próximo arquivo
                            }
                            if (numeroLinha < linhas.size() &&
                                    linhas.get(numeroLinha).contains(String.valueOf(termo))) {

                                String conteudoCompleto = linhas.stream()
                                        .map(linha -> linha.replaceFirst("^\\d+\\s*-\\s*", "")) // Remove "número - "
                                        .collect(Collectors.joining(" - "));

                                arquivosEncontrados.add(conteudoCompleto);
                            }
                        } catch (IOException e) {
                            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
                        }
                    });
        } catch (IOException e) {
            System.out.println("Erro ao acessar os arquivos: " + e.getMessage());
        }
        return arquivosEncontrados;
    }

    public List<String> buscarCombinada() {
        System.out.println("=== BUSCA COMBINADA ===");
        System.out.println("1 - Nome");
        System.out.println("2 - Sexo");
        System.out.println("3 - Idade");
        System.out.println("4 - Peso");
        System.out.println("5 - Raça");
        System.out.println("6 - Endereço");
        System.out.println("0 - Voltar ao menu principal");

        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o tipo do animal (Cachorro ou Gato): ");
        String tipo = sc.nextLine();

        if (!tipo.equalsIgnoreCase("Cachorro") && !tipo.equalsIgnoreCase("Gato")) {
            System.out.println("Tipo inválido. Digite 'Cachorro' ou 'Gato'.");
            return buscarCombinada();
        }

        System.out.println("Deseja usar um ou dois critérios? (1 ou 2): ");
        int numeroCriterios = sc.nextInt();
        sc.nextLine();

        if (numeroCriterios == 1) {
            System.out.println("Escolha o critério de busca (1-7): ");
            int criterio = sc.nextInt();
            sc.nextLine();

            List<String> resultados = obterResultadosPorCriterio(criterio, tipo);

            if (resultados.isEmpty()) {
                System.out.println("\nNenhum animal encontrado com esse critério.");
            } else {
                System.out.println("\nAnimais encontrados:");
                for (int i = 0; i < resultados.size(); i++) {
                    System.out.println((i + 1) + ". " + resultados.get(i));
                }
            }
            return resultados;

        } else if (numeroCriterios == 2) {
            System.out.println("Escolha o primeiro critério de busca (1-7): ");
            int primeiroCriterio = sc.nextInt();
            sc.nextLine();

            System.out.println("Escolha o segundo critério de busca (1-7): ");
            int segundoCriterio = sc.nextInt();
            sc.nextLine();

            List<String> resultadosPrimeiro = obterResultadosPorCriterio(primeiroCriterio, tipo);
            List<String> resultadosSegundo = obterResultadosPorCriterio(segundoCriterio, tipo);

            // Encontra a interseção (animais que atendem ambos os critérios)
            List<String> resultadosCombinados = new ArrayList<>();
            for (String animal : resultadosPrimeiro) {
                if (resultadosSegundo.contains(animal)) {
                    resultadosCombinados.add(animal);
                }
            }

            if (resultadosCombinados.isEmpty()) {
                System.out.println("Nenhum animal encontrado que atenda ambos os critérios.");
            } else {
                System.out.println("Animais encontrados que atendem ambos os critérios:");
                for (int i = 0; i < resultadosCombinados.size(); i++) {
                    System.out.println((i + 1) + ". " + resultadosCombinados.get(i));
                }
            }
            return resultadosCombinados;

        } else {
            System.out.println("Opção inválida.");
            return new ArrayList<>();
        }
    }


    private List<String> obterResultadosPorCriterio(int criterio, String tipoAnimal) {
        Scanner sc = new Scanner(System.in);

        switch (criterio) {
            case 1:
                System.out.println("Digite o nome e/ou sobrenome do animal: ");
                String nome = sc.nextLine();
                if (nome.matches("^[A-Za-zÀ-ÖØ-öø-ÿ]+(?:\\s+[A-Za-zÀ-ÖØ-öø-ÿ]+)?$")) {
                    return buscarTermoString(nome, 0, tipoAnimal);
                }
                break;

            case 2:
                System.out.println("Digite o sexo do animal (Macho ou Fêmea): ");
                String sexo = sc.nextLine();
                if (sexo.equalsIgnoreCase("Macho") || sexo.equalsIgnoreCase("Femea") || sexo.equalsIgnoreCase("Fêmea")) {
                    return buscarTermoString(sexo, 2, tipoAnimal);
                }
                break;

            case 3:
                System.out.println("Digite o endereço: ");
                String endereco = sc.nextLine();
                if (!endereco.trim().isEmpty()) {
                    return buscarTermoString(endereco, 3, tipoAnimal);
                }
                break;

            case 4:
                System.out.println("Digite a idade do animal: ");
                try {
                    double idade = sc.nextDouble();
                    if (idade >= 0 && idade <= 20) {
                        return buscarTermoDouble(idade, 4, tipoAnimal);
                    }
                } catch (Exception e) {
                    System.out.println("Valor inválido para idade.");
                }
                break;

            case 5:
                System.out.println("Digite o peso do animal: ");
                try {
                    double peso = sc.nextDouble();
                    if (peso >= 0.5 && peso <= 60) {
                        return buscarTermoDouble(peso, 5, tipoAnimal);
                    }
                } catch (Exception e) {
                    System.out.println("Valor inválido para peso.");
                }
                break;

            case 6:
                System.out.println("Digite a raça do animal: ");
                String raca = sc.nextLine();
                if (raca.matches("^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$")) {
                    return buscarTermoString(raca, 6, tipoAnimal);
                }
                break;

            case 0:
                System.out.println("Voltando ao menu principal...");
                break;

            default:
                System.out.println("Opção inválida.");
                break;
        }

        return new ArrayList<>();
    }

}
