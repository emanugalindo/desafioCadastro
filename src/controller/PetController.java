package controller;

import model.Pet;
import services.PetService;

public class PetController {
    public void criarPet() {
        PetService ps = new PetService();

        Pet pet = new Pet(ps.verificarNomeValido(), ps.verificarTipoValido(), ps.verificarSexoValido(),
                ps.verificarEnderecoValido(), ps.verificarIdadevalida(), ps.verificarPesoValido(),
                ps.verificarRacaValida());

    }
}
