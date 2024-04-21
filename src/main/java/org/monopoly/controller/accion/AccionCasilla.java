package org.monopoly.controller.accion;

import org.monopoly.model.Juego;
import org.monopoly.model.casilla.Casilla;

public abstract class AccionCasilla implements Accion {
    protected Juego juego;
    protected Casilla casilla;

    public AccionCasilla(Juego juego){
        this.juego = juego;
    }
    public void setCasilla(Casilla casilla){
        this.casilla = casilla;
    }
    public abstract void accionar();
    public abstract String mostrar();
}
