package org.monopoly.controller;

import org.monopoly.model.Jugador;

public class JuegoController {
    private Juego juego;
    private Validable validador;
    public JuegoController(){

    }

    public void accionesPosibles(Jugador jugador){
        this.validador.accionesPosibles(jugador);
    }
}
