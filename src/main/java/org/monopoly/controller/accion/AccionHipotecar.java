package org.monopoly.controller.accion;

import org.monopoly.controller.validador.ValidadorAccionesCasilla;
import org.monopoly.model.Juego;
import org.monopoly.model.casilla.Casilla;
import org.monopoly.model.casilla.Comprable;

import java.util.List;

public class AccionHipotecar extends AccionCasilla{
    public AccionHipotecar(Juego juego, List<Casilla> opciones, ValidadorAccionesCasilla validador){
        super(juego, opciones, validador);
    }
    public void accionar(){
        this.juego.hipotecar((Comprable)casilla);
        this.validador.registrarHipoteca((Comprable) this.casilla, this.juego.getJugadorActual());
    }
    public String mostrar(){
        return "Hipotecar propiedad";
    }
}
