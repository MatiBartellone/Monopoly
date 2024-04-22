package org.monopoly.controller.accion;

import org.monopoly.controller.validador.ValidadorAccionesCasilla;
import org.monopoly.model.Juego;
import org.monopoly.model.casilla.Casilla;
import org.monopoly.model.casilla.Comprable;

import java.util.List;

public abstract class AccionCasilla implements Accion {
    protected Juego juego;
    protected Casilla casilla;
    protected List<Casilla> opciones;
    protected ValidadorAccionesCasilla validador;

    public AccionCasilla(Juego juego, List<Casilla> opciones, ValidadorAccionesCasilla validador){
        this.juego = juego;
        this.opciones = opciones;
    }
    public void setCasilla(Casilla casilla){
        this.casilla = casilla;
    }

    public List<Casilla> getOpciones(){
        return this.opciones;
    }
    public abstract void accionar();
    public abstract String mostrar();
}
