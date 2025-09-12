package model;

public enum Sexo {
    MACHO("Macho"),
    FEMEA("Fêmea");

    private String sexo;

    Sexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDescricao() {
        return sexo;
    }
}
