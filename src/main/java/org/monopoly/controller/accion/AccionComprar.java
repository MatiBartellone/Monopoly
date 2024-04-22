package org.monopoly.controller.accion;

import org.monopoly.controller.validador.ValidadorAccionesCasilla;
import org.monopoly.model.Juego;
import org.monopoly.model.casilla.Casilla;
import org.monopoly.model.casilla.Comprable;
import org.monopoly.model.casilla.Propiedad;

import java.util.List;

public class AccionComprar extends AccionCasilla{

    public AccionComprar(Juego juego, List<Casilla> opciones, ValidadorAccionesCasilla validador){
        super(juego, opciones, validador);
    }

    public void accionar(){
        this.juego.comprar();
        this.validador.registrarCompraPropiedad((Comprable) this.casilla, this.juego.getJugadorActual());
    }

    public String mostrar(){
        return "Comprar";
    }
}
