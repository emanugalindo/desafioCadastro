package view;

import java.io.BufferedReader;
import java.io.FileReader;

public class FormularioView {
    public void verFormulario(){
        try(BufferedReader bf = new BufferedReader(new FileReader("src/formulario.txt"))){
            String linha;
            while ((linha = bf.readLine()) != null){
                System.out.println(linha);
            }
        } catch (Exception e) {
            System.out.println("Formulário não encontrado");
        }
    }
}
