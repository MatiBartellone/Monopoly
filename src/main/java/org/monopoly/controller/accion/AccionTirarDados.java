package org.monopoly.controller.accion;

import org.monopoly.model.Juego;

public class AccionTirarDados extends AccionInicio{

    public AccionTirarDados(Juego juego){
        super(juego);
    }

    public void accionar(){
        this.juego.tirarDados();
        this.juego.mover();
    }

    public String getNombre(){
        return "Tirar Dados";
    }
}
