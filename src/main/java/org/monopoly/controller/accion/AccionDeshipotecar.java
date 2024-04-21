package org.monopoly.controller.accion;

import org.monopoly.model.Juego;
import org.monopoly.model.casilla.Casilla;
import org.monopoly.model.casilla.Comprable;

public class AccionDeshipotecar extends AccionCasilla{
    public AccionDeshipotecar(Juego juego){
        super(juego);
    }

    public void accionar(){
        this.juego.deshipotecar((Comprable)casilla);
    }
    public String mostrar(){
        return "Deshipotecar propiedad";
    }
}
