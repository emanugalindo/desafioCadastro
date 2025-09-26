package services;

import model.Endereco;
import model.Pet;
import model.Sexo;
import model.Tipo;
import utils.Constante;
import view.FormularioView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
                return String.format("%.1f ano(s)", idade);
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

    public ArrayList<Pet> buscarPets() {
        File[] files = new File("petsCadastrados").listFiles();

        ArrayList<Pet> petsLista = new ArrayList<>();
        if (files == null) {
            System.out.println("Diretório 'petsCadastrados' não encontrado ou vazio");
            return petsLista;
        }

        for (File file : files) {
            if (file.getName().endsWith(".txt")) {
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String nome = br.readLine().split(" - ")[1];
                    String tipo = br.readLine().split(" - ")[1];
                    String sexo = br.readLine().split(" - ")[1];
                    String endereco = br.readLine().split(" - ")[1];
                    String idade = br.readLine().split(" - ")[1];
                    String peso = br.readLine().split(" - ")[1];
                    String raca = br.readLine().split(" - ")[1];

                    String enderecoStrings[] = endereco.split(",");
                    Endereco enderecoPet = new Endereco(enderecoStrings[0].trim(), enderecoStrings[1].trim(), enderecoStrings[2].trim());

                    Pet pet = new Pet(nome, Tipo.valueOf(tipo.toUpperCase()), Sexo.valueOf(sexo.toUpperCase()), enderecoPet, idade, peso, raca);

                    petsLista.add(pet);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return petsLista;
    }

    public void menu() {
        ArrayList<Pet> petsLista;
        List<Pet> petsEncontrados = List.of();

        petsLista = buscarPets();
        Scanner sc = new Scanner(System.in);
        int destacarC1 = 0;
        int destacarC2 = 0;

        System.out.println("Deseja procurar por: \n1 - Tipo de Pet \n2 - Data do arquivo");
        System.out.println("Digite a opção escolhida: ");
        int escolha = sc.nextInt();
        sc.nextLine();

        if (escolha == 1) {
            System.out.println("Selecione para filtrar - Gato ou Cachorro: ");
            String escolhaTipo = sc.nextLine();

            if (!escolhaTipo.equalsIgnoreCase("Gato") && !escolhaTipo.equalsIgnoreCase("Cachorro")) {
                System.out.println("Tipo inválido! Digite 'Gato' ou 'Cachorro'");
                menu();
                return;
            }

            petsEncontrados = buscarTipo(escolhaTipo, petsLista);
        } else if (escolha == 2) {
            System.out.println("Digite o ano (4 dígitos): ");
            String ano = sc.nextLine();

            System.out.println("Digite o mês (2 dígitos): ");
            String mes = sc.nextLine();

            if (!ano.matches("\\d{4}") || !mes.matches("\\d{2}")) {
                System.out.println("Formato inválido! Use AAAA para ano e MM para mês.");
                menu();
                return;
            }

            String data = ano + mes;

            petsEncontrados = buscarData(data);
        } else {
            System.out.println("Opção inválida");
            menu();
        }

        System.out.println("\nEscolha o primeiro critério");
        criterios();
        int criterio1 = sc.nextInt();
        sc.nextLine();

        if (criterio1 < 0 || criterio1 > 6) {
            System.out.println("Critério inválido!");
            menu();
            return;
        }

        System.out.println("\nEscolha o segundo critério");
        criterios();
        int criterio2 = sc.nextInt();

        if (criterio2 < 0 || criterio2 > 6) {
            System.out.println("Critério inválido!");
            menu();
            return;
        }

        sc.nextLine();
        System.out.println();

        if (criterio1 == 1 || criterio2 == 1) {
            System.out.println("Digite o nome e/ou sobrenome do pet: ");
            String nome = sc.nextLine().toLowerCase().trim();

            if (nome.isBlank()) {
                System.out.println("Digite um nome");
            } else {
                petsEncontrados = buscarNome(nome, petsEncontrados);
            }
            if (criterio1 == 1) destacarC1 = 1;
            if (criterio2 == 1) destacarC2 = 1;
        }

        if (criterio1 == 2 || criterio2 == 2) {
            System.out.println("Digite o sexo do pet (Macho|Fêmea): ");
            String sexo = sc.nextLine().toLowerCase().trim();

            if (sexo.isBlank()) {
                System.out.println("Digite um sexo");
            } else {
                petsEncontrados = buscarSexo(sexo, petsEncontrados);
            }
            if (criterio1 == 2) destacarC1 = 2;
            if (criterio2 == 2) destacarC2 = 2;
        }

        if (criterio1 == 3 || criterio2 == 3) {
            System.out.println("Digite a idade do pet: ");
            double idade = sc.nextDouble();
            sc.nextLine();
            petsEncontrados = buscarIdade(idade, petsEncontrados);

            if (criterio1 == 3) destacarC1 = 4;
            if (criterio2 == 3) destacarC2 = 4;
        }

        if (criterio1 == 4 || criterio2 == 4) {
            System.out.println("Digite peso do pet: ");
            double peso = sc.nextDouble();
            sc.nextLine();
            petsEncontrados = buscarPeso(peso, petsEncontrados);

            if (criterio1 == 4) destacarC1 = 5;
            if (criterio2 == 4) destacarC2 = 5;
        }

        if (criterio1 == 5 || criterio2 == 5) {
            System.out.println("Digite a raça do pet: ");
            String raca = sc.nextLine().toLowerCase().trim();

            if (raca.isBlank()) {
                System.out.println("Digite uma raça");
            } else {
                petsEncontrados = buscarRaca(raca, petsEncontrados);
            }

            if (criterio1 == 5) destacarC1 = 6;
            if (criterio2 == 5) destacarC2 = 6;
        }

        if (criterio1 == 6 || criterio2 == 6) {
            System.out.println("Digite o endereço: ");
            String endereco = sc.nextLine().toLowerCase().trim();

            if (endereco.isBlank()) {
                System.out.println("Digite um endereço");
            } else {
                petsEncontrados = buscarEndereco(endereco, petsEncontrados);
            }

            if (criterio1 == 6) destacarC1 = 3;
            if (criterio2 == 6) destacarC2 = 3;
        }

        exibirPets(petsEncontrados, destacarC1, destacarC2);
    }

    private void criterios() {
        System.out.println("1 - Nome e/ou sobrenome");
        System.out.println("2 - Sexo");
        System.out.println("3 - Idade");
        System.out.println("4 - Peso");
        System.out.println("5 - Raça");
        System.out.println("6 - Endereço");
        System.out.println("0 - Nenhuma das opções");
        System.out.print("Opção escolhida: ");
    }

    private List<Pet> buscarTipo(String tipo, List<Pet> pets) {
        return pets.stream()
                .filter(pet -> pet.getTipo().toString().equalsIgnoreCase(tipo)).toList();
    }

    private List<Pet> buscarNome(String nome, List<Pet> pets) {
        return pets.stream()
                .filter(pet -> pet.getNome().toLowerCase().contains(nome)).toList();
    }

    private List<Pet> buscarSexo(String sexo, List<Pet> pets) {
        String sexoNormalizado = sexo.replace("ê", "e");
        return pets.stream()
                .filter(pet -> pet.getSexo().toString().toLowerCase().contains(sexoNormalizado.toLowerCase())).toList();
    }

    private List<Pet> buscarIdade(double idade, List<Pet> pets) {
        return pets.stream()
                .filter(pet -> {
                    try {
                        return Double.parseDouble(pet.getIdade().replace("ano(s)", "")) == idade;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })
                .toList();
    }

    private List<Pet> buscarPeso(double peso, List<Pet> pets) {
        return pets.stream()
                .filter(pet -> {
                    try {
                        return Double.parseDouble(pet.getPeso().replace("kg", "")) == peso;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })
                .toList();
    }

    private List<Pet> buscarRaca(String raca, List<Pet> pets) {
        return pets.stream()
                .filter(pet -> pet.getRaca().toLowerCase().contains(raca)).toList();
    }

    private List<Pet> buscarEndereco(String endereco, List<Pet> pets) {
        return pets.stream()
                .filter(pet -> pet.getEndereco().toString().toLowerCase().contains(endereco.toLowerCase())).toList();
    }

    private List<Pet> buscarData(String data) {
        ArrayList<Pet> petsEncontrados = new ArrayList<>();
        File diretorio = new File("petsCadastrados");
        File[] arquivos = diretorio.listFiles();

        if (arquivos == null) {
            System.out.println("Diretório 'petsCadastrados' não encontrado ou vazio");
            return petsEncontrados;
        }

        boolean arquivoEncontrado = false;

        for (File arquivo : arquivos) {
            if (arquivo.getName().contains(data) && arquivo.getName().endsWith(".txt")) {
                arquivoEncontrado = true;
                try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                    String nome = br.readLine().split(" - ")[1];
                    String tipo = br.readLine().split(" - ")[1];
                    String sexo = br.readLine().split(" - ")[1];
                    String endereco = br.readLine().split(" - ")[1];
                    String idade = br.readLine().split(" - ")[1];
                    String peso = br.readLine().split(" - ")[1];
                    String raca = br.readLine().split(" - ")[1];

                    String[] enderecoStrings = endereco.split(",");
                    Endereco enderecoPet = new Endereco(enderecoStrings[0].trim(),
                            enderecoStrings[1].trim(),
                            enderecoStrings[2].trim());

                    Pet pet = new Pet(nome, Tipo.valueOf(tipo.toUpperCase()),
                            Sexo.valueOf(sexo.toUpperCase()), enderecoPet,
                            idade, peso, raca);
                    petsEncontrados.add(pet);
                } catch (IOException e) {
                    System.err.println("Erro ao ler o arquivo: " + arquivo.getName());
                    e.printStackTrace();
                }
            }
        }

        if (!arquivoEncontrado) {
            System.out.println("Nenhum arquivo encontrado para a data: " + data);
        }

        return petsEncontrados;
    }


    public void exibirPets(List<Pet> pets, int destacarC1, int destacarC2) {
        if (pets == null) buscarPets();

        if (pets.isEmpty()) {
            System.out.println("Nenhum pet corresponde aos critérios informados\n");
            return;
        }

        int cont = 1;

        System.out.println("\n======== PETS ENCONTRADOS ========");
        for (Pet pet : pets) {
            String nome = destacarC1 == 1 || destacarC2 == 1 ? "\033[0;1m" + pet.getNome() + "\033[0;0m" : pet.getNome();
            String sexo = destacarC1 == 2 || destacarC2 == 2 ? "\033[0;1m" + pet.getSexo().toString() + "\033[0;0m" : pet.getSexo().toString();
            String endereco = destacarC1 == 3 || destacarC2 == 3 ? "\033[0;1m" + pet.getEndereco().toString() + "\033[0;0m" : pet.getEndereco().toString();
            String idade = destacarC1 == 4 || destacarC2 == 4 ? "\033[0;1m" + pet.getIdade() + "\033[0;0m" : pet.getIdade();
            String peso = destacarC1 == 5 || destacarC2 == 5 ? "\033[0;1m" + pet.getPeso() + "\033[0;0m" : pet.getPeso();
            String raca = destacarC1 == 6 || destacarC2 == 6 ? "\033[0;1m" + pet.getRaca() + "\033[0;0m" : pet.getRaca();

            System.out.printf("%d.  %s - %s - %s - %s - %s - %s - %s%n", cont++, nome, pet.getTipo(), sexo, endereco, idade, peso, raca);
        }
    }
}
