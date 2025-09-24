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

        ArrayList<Pet> petsLista = null;
        if (files != null) {
            petsLista = new ArrayList<>();
        }

        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().endsWith(".txt")) {
                try (BufferedReader br = new BufferedReader(new FileReader(files[i]))) {
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
        List<Pet> petsEncontrados;

        petsLista = buscarPets();
        Scanner sc = new Scanner(System.in);

        System.out.println("Selecione para filtrar - Gato ou Cachorro: ");
        String escolhaTipo = sc.nextLine();

        System.out.println("\nEscolha o primeiro critério");
        criterios();
        int criterio1 = sc.nextInt();

        System.out.println("\nEscolha o segundo critério");
        criterios();
        int criterio2 = sc.nextInt();

        petsEncontrados = buscarTipo(escolhaTipo, petsLista);

        sc.nextLine();
        System.out.println();

        if (criterio1 == 1 || criterio2 == 1) {
            System.out.println("Digite o nome e/ou sobrenome do pet: ");
            String nome = sc.nextLine().toLowerCase().trim();

            if (nome.isBlank()) System.out.println("Digite um nome");
            else petsEncontrados = buscarNome(nome, petsEncontrados);
        }

        if (criterio1 == 2 || criterio2 == 2) {
            System.out.println("Digite o sexo do pet (Macho|Fêmea): ");
            String sexo = sc.nextLine().toLowerCase().trim();

            if (sexo.isBlank()) System.out.println("Digite um sexo");
            else petsEncontrados = buscarSexo(sexo, petsEncontrados);
        }

        if (criterio1 == 3 || criterio2 == 3) {
            System.out.println("Digite a idade do pet: ");
            double idade = sc.nextDouble();
            sc.nextLine();
            petsEncontrados = buscarIdade(idade, petsEncontrados);
        }

        if (criterio1 == 4 || criterio2 == 4) {
            System.out.println("Digite peso do pet: ");
            double peso = sc.nextDouble();
            sc.nextLine();
            petsEncontrados = buscarPeso(peso, petsEncontrados);
        }

        if (criterio1 == 5 || criterio2 == 5) {
            sc.nextLine();
            System.out.println("Digite a raça do pet: ");
            String raca = sc.nextLine().toLowerCase().trim();

            if (raca.isBlank()) System.out.println("Digite uma raça");
            else petsEncontrados = buscarRaca(raca, petsEncontrados);
        }

        if (criterio1 == 6 || criterio2 == 6) {
            System.out.println("Digite o endereço: ");
            String endereco = sc.nextLine().toLowerCase().trim();

            if (endereco.isBlank()) System.out.println("Digite um endereço");
            else petsEncontrados = buscarEndereco(endereco, petsEncontrados);
        }

        exibirPets(petsEncontrados);
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

    public void exibirPets(List<Pet> pets) {
        if (pets == null) buscarPets();

        if (pets.isEmpty()) {
            System.out.println("Nenhum pet corresponde aos critérios informados\n");
            return;
        }

        int cont = 1;

        System.out.println("\n======== PETS ENCONTRADOS ========");
        for (Pet pet : pets) {
            System.out.printf(
                    "%d.  %s - %s - %s - %s - %s - %s - %s%n",
                    cont++, pet.getNome(), pet.getTipo(), pet.getSexo(),
                    pet.getEndereco(), pet.getIdade(), pet.getPeso(),
                    pet.getRaca()
            );
        }
    }
}
