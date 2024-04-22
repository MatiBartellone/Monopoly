package org.monopoly.controller.accion;

import org.monopoly.model.Juego;
import org.monopoly.controller.accion.AccionInicio;

public class AccionTirarDados extends AccionInicio{

    public AccionTirarDados(Juego juego){
        super(juego);
    }

    public void accionar(){
        this.juego.tirarDados();
    }

    public String mostrar(){
        return "Tirar Dados";
    }
}
