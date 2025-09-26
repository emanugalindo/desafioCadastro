package controller;

import model.Pet;
import services.PetService;

import java.nio.file.Files;
import java.nio.file.Paths;

public class PetController {
    PetService ps = new PetService();

    public void criarPet() {
        Pet pet = new Pet(ps.verificarNomeValido(), ps.verificarTipoValido(), ps.verificarSexoValido(),
                ps.verificarEnderecoValido(), ps.verificarIdadevalida(), ps.verificarPesoValido(),
                ps.verificarRacaValida());

        pet.salvarPet();
    }

    public void buscarPet() {

        if (Files.exists(Paths.get("petsCadastrados"))) {
            ps.menu();
        } else {
            System.out.println("Nenhum pet cadastrado ainda.");
        }
    }

    public void listarTodosPets(){
        ps.exibirPets(ps.buscarPets(), 0, 0);
    }
}
