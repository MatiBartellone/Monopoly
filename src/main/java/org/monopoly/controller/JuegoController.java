package org.monopoly.controller;

import org.monopoly.controller.accion.Accion;
import org.monopoly.controller.validador.Validador;
import org.monopoly.controller.validador.ValidadorAccionFinal;
import org.monopoly.controller.validador.ValidadorAccionesCasilla;
import org.monopoly.model.Juego;
import org.monopoly.model.Jugador;

import java.util.ArrayList;
import java.util.List;

public class JuegoController {
    private Juego juego;
    private Validador validadorInicio;
    private List<Validador> validadores;
    public JuegoController(Juego juego){
        this.juego = juego;
        this.validadorInicio = new org.monopoly.controller.ValidadorAccionInicio(this.juego);
        this.validadores = new ArrayList<Validador>(){{
            add(new ValidadorAccionesCasilla(juego));
            add(new ValidadorAccionFinal(juego));
        }};
    }

    public void jugarTurno(){
        List<Accion> accionesInicio = validadorInicio.accionesPosibles();

        int asd = 0;

        accionesInicio.get(asd).accionar();

        boolean terminoTurno = false;
        while (!terminoTurno) {
            List<Accion> acciones = thiAg
        }
    }

    public List<Accion> opcionesAcciones (Jugador jugador){
        List<Accion> acciones = new ArrayList<>();
        for (Validador v : this.validadores){
            acciones.addAll(v.accionesPosibles(jugador));
        }

        return acciones;
    }
}