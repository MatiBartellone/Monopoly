package org.monopoly.controller.accion;

import org.monopoly.model.Juego;
import org.monopoly.model.casilla.Comprable;

public class AccionHipotecar extends AccionCasilla{
    public AccionHipotecar(Juego juego){
        super(juego);
    }
    public void accionar(){
        this.juego.hipotecar((Comprable)casilla);
    }
    public String mostrar(){
        return "Hipotecar propiedad";
    }
}
