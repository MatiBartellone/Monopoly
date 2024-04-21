package org.monopoly.controller.accion;

import org.monopoly.model.Juego;
import org.monopoly.controller.accion.AccionInicio;

public class AccionTirarDatos extends AccionInicio{

    public AccionTirarDatos(Juego juego){
        super(juego);
    }

    public void accionar(){
        this.juego.tirarDados();
    }

    public String mostrar(){
        return "Tirar Dados";
    }
}
