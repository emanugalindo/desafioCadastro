package model;

public class Endereco {
    private String num;
    private String cidade;
    private String rua;
    private String endereco;

    public Endereco(String num, String cidade, String rua) {
        this.num = num;
        this.cidade = cidade;
        this.rua = rua;
        this.endereco = this.rua + ", " + this.num + ", " + this.cidade;
    }

    public Endereco() {
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {

        this.num = num;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", this.rua, this.num, this.cidade);
    }
}
