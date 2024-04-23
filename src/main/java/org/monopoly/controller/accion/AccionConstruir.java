package org.monopoly.controller.accion;

import org.monopoly.controller.validador.ValidadorAccionesCasilla;
import org.monopoly.model.Juego;
import org.monopoly.model.casilla.Casilla;
import org.monopoly.model.casilla.Comprable;
import org.monopoly.model.casilla.Propiedad;

import java.util.List;

public class AccionConstruir extends AccionCasilla{

    public AccionConstruir(Juego juego, List<Casilla> opciones, ValidadorAccionesCasilla validador){
        super(juego, opciones, validador);
    }

    public void accionar(){
        this.juego.construirConstruccion((Propiedad) this.casilla);
        this.validador.registrarConstruccion((Propiedad) this.casilla, this.juego.getJugadorActual());
    }

    public String getNombre(){
        return "Construir Construccion";
    }
}
