package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pet {
    private String nome;
    private Tipo tipo;
    private Sexo sexo;
    private Endereco endereco;
    private String idade;
    private String peso;
    private String raca;

    public Pet(String nome, Tipo tipo, Sexo sexo, Endereco endereco, String idade, String peso, String raca) {
        this.nome = nome;
        this.tipo = tipo;
        this.sexo = sexo;
        this.endereco = endereco;
        this.idade = idade;
        this.peso = peso;
        this.raca = raca;
    }

    public Pet() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public void salvarPet(){
        File diretorio = new File("petsCadastrados");
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        LocalDateTime dataHora = LocalDateTime.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String dataHoraFomatada = dataHora.format(formatador);

        File animal = new File("petsCadastrados/" + dataHoraFomatada + "-" + this.nome.replace(" ", "").toUpperCase() + ".txt");
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(animal, true))){
            bw.write("1 - " + this.nome);
            bw.newLine();
            bw.write("2 - " + this.tipo);
            bw.newLine();
            bw.write("3 - " + this.sexo);
            bw.newLine();
            bw.write("4 - " + this.endereco);
            bw.newLine();
            bw.write("5 - " + this.idade);
            bw.newLine();
            bw.write("6 - " + this.peso);
            bw.newLine();
            bw.write("7 - " + this.raca);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro na criação ou escrita no arquivo " + e.getMessage());
            return;
        }
        System.out.println("Arquivo criado com sucesso");
    }

    @Override
    public String toString() {
        return "Pet{" +
                "nome='" + nome + '\'' +
                ", tipo=" + tipo +
                ", sexo=" + sexo +
                ", endereco=" + endereco +
                ", idade='" + idade + '\'' +
                ", peso='" + peso + '\'' +
                ", raca='" + raca + '\'' +
                '}';
    }
}
