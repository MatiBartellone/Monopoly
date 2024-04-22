package org.monopoly.controller.accion;

import org.monopoly.controller.validador.ValidadorAccionesCasilla;
import org.monopoly.model.Juego;
import org.monopoly.model.casilla.Casilla;
import org.monopoly.model.casilla.Comprable;

import java.util.List;

public class AccionDeshipotecar extends AccionCasilla{
    public AccionDeshipotecar(Juego juego, List<Casilla> opciones, ValidadorAccionesCasilla validador){
        super(juego, opciones, validador);
    }

    public void accionar(){
        this.juego.deshipotecar((Comprable)casilla);
        this.validador.registrarDeshipoteca((Comprable)this.casilla, this.juego.getJugadorActual());
    }
    public String mostrar(){
        return "Deshipotecar propiedad";
    }
}
