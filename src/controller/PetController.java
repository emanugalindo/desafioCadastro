package controller;

import model.Pet;
import services.PetService;
import utils.Utils;

import java.nio.file.Files;
import java.nio.file.Paths;

public class PetController {
    PetService ps = new PetService();
    Utils utils = new Utils();

    public void criarPet() {
        Pet pet = new Pet(utils.verificarNomeValido(), utils.verificarTipoValido(), utils.verificarSexoValido(),
                utils.verificarEnderecoValido(), utils.verificarIdadevalida(), utils.verificarPesoValido(),
                utils.verificarRacaValida());

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

    public void alterarPet(){
        ps.alterarPets();
    }

    public void deletarPet() {
        ps.excluirPet();
    }
}
