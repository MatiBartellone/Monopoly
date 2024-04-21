package org.monopoly.controller.accion;

import org.monopoly.model.Juego;

public class AccionComprar extends AccionCasilla{

    public AccionComprar(Juego juego){
        super(juego);
    }

    public void accionar(){
        this.juego.comprar();
    }

    public String mostrar(){
        return "Comprar";
    }
}
