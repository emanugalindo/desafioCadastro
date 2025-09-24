package model;

public enum Sexo {
    MACHO("Macho"),
    FEMEA("Femea");

    private String sexo;

    Sexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return sexo;
    }
}
