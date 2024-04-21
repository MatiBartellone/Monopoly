package org.monopoly.controller.accion;

import org.monopoly.model.Juego;
import org.monopoly.model.casilla.Propiedad;

public class AccionConstruir extends AccionCasilla{

    public AccionConstruir(Juego juego){
        super(juego);
    }

    public void accionar(){
        this.juego.construirConstruccion((Propiedad) this.casilla);
    }

    public String mostrar(){
        return "Construir Construccion";
    }
}
