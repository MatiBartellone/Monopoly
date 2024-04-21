package org.monopoly.controller.accion;

import org.monopoly.model.Juego;
import org.monopoly.model.casilla.Propiedad;

public class AccionVender extends AccionCasilla{

    public AccionVender(Juego juego){
        super(juego);
    }

    public void accionar(){
        this.juego.venderConstruccion((Propiedad) this.casilla);
    }

    public String mostrar(){
        return "Vender Construccion";
    }
}
