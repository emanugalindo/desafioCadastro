package model;

public enum Tipo {
    CACHORRO("Cachorro"),
    GATO("Gato");

    private String tipo;

    Tipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
}
