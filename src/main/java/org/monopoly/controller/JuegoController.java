package org.monopoly.controller;

import org.monopoly.model.Juego;
import org.monopoly.model.Jugador;

import java.util.List;

public class JuegoController {
    private Juego juego;
    private Validador validadorInicio;
    private List<Validador> validadores;
    public JuegoController(){
        this.validadorInicio= new ValidadorAccionInicio();
        this.List<Validador>
    }

    public void accionesPosibles(Jugador jugador){
        this.validador.accionesPosibles(jugador);
    }
}