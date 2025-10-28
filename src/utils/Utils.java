package utils;

import model.Endereco;
import model.Sexo;
import model.Tipo;
import view.FormularioView;

import java.util.Scanner;

public class Utils {
    public static final String NAO_INFORMADO = "Não informado";
    Scanner sc = new Scanner(System.in);

    public String verificarNomeValido() {
        try {
            FormularioView.lerPerguntas(1);
            String nome = sc.nextLine().trim();

            if (nome == null || nome.isEmpty()) {
                return NAO_INFORMADO;
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
            System.out.println("Digite o número da casa: ");
            String num = sc.nextLine();

            if (num == null || num.trim().isEmpty()) {
                num = NAO_INFORMADO;
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
            String input = sc.nextLine();
            String pontoVirgula = input.replace(",", ".");
            Double idade = Double.parseDouble(pontoVirgula);

            if (idade == null) {
                return NAO_INFORMADO;
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
            String input = sc.nextLine();
            String pontoVirgula = input.replace(",", ".");
            Double peso = Double.parseDouble(pontoVirgula);

            if (peso == null) {
                return NAO_INFORMADO;
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
            String raca = sc.nextLine();
            if (raca == null || raca.trim().isEmpty()) {
                return NAO_INFORMADO;
            }
            if (!raca.matches("^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$")) {
                throw new IllegalArgumentException();
            }
            return raca;
        } catch (IllegalArgumentException e) {
            return verificarRacaValida();
        }
    }

    public String verificarTipoAnimal() {
        try {
            System.out.println("Digite o tipo do animal (Cachorro ou Gato): ");
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
            return verificarTipoAnimal();
        }
    }
}
