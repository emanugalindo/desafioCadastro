package controller;

import model.Pet;
import services.PetService;

import java.nio.file.Files;
import java.nio.file.Paths;

public class PetController {
    public void criarPet() {
        PetService ps = new PetService();

        Pet pet = new Pet(ps.verificarNomeValido(), ps.verificarTipoValido(), ps.verificarSexoValido(),
                ps.verificarEnderecoValido(), ps.verificarIdadevalida(), ps.verificarPesoValido(),
                ps.verificarRacaValida());

        pet.salvarPet();
    }

    public void buscarPet() {
        PetService ps = new PetService();

        if (Files.exists(Paths.get("petsCadastrados"))) {
            ps.buscarCombinada();
        } else {
            System.out.println("Nenhum pet cadastrado ainda.");
        }
    }
}
