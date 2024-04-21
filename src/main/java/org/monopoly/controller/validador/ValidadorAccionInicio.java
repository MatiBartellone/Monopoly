package org.monopoly.controller;

import org.monopoly.controller.accion.Accion;
import org.monopoly.controller.accion.AccionPagarFianza;
import org.monopoly.controller.accion.AccionTirarDatos;
import org.monopoly.controller.validador.Validador;
import org.monopoly.model.Config;
import org.monopoly.model.Juego;
import org.monopoly.model.Jugador;

import java.util.ArrayList;
import java.util.List;

public class ValidadorAccionInicio implements Validador {

    private Juego juego;

    public ValidadorAccionInicio(Juego juego){
        this.juego = juego;
    }

    public List<Accion> accionesPosibles(Jugador jugador) {
        List<Accion> acciones = new ArrayList<>();
        acciones.add( new AccionTirarDatos(this.juego));
        if (jugador.getEstado() == Config.EstadosJugadores.PRESO && this.juego.alcanzaDinero(Config.ValorFianza))
            acciones.add( new AccionPagarFianza(this.juego));
        return acciones;
    }
}
