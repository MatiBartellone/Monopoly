package org.monopoly.controller.accion;

import org.monopoly.model.Juego;

public class AccionEntrarEnQuiebra extends AccionFinal {

    public AccionEntrarEnQuiebra(Juego juego){
        super(juego);
    }

    public void accionar(){
        this.juego.entrarEnQuiebra();
    }

    public String getNombre(){
        return "Aceptar La Muerte";
    }
}
