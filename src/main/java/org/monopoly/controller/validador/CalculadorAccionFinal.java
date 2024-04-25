package org.monopoly.controller.validador;

import org.monopoly.controller.accion.Accion;
import org.monopoly.controller.accion.AccionEntrarEnQuiebra;
import org.monopoly.controller.accion.AccionPasarDeTurno;
import org.monopoly.model.Config;
import org.monopoly.model.Juego;
import org.monopoly.model.Jugador;

import java.util.ArrayList;
import java.util.List;

public class CalculadorAccionFinal implements CalculadoraDeAcciones{

    private Juego juego;

    public CalculadorAccionFinal(Juego juego){
        this.juego = juego;
    }
    public List<Accion> accionesPosibles(Jugador jugador) {
        List<Accion> posibles =new ArrayList<>();
        if (jugador.getEstado() == Config.EstadosJugadores.CRISIS){
            posibles.add(new AccionEntrarEnQuiebra(juego));
        }else{
            posibles.add(new AccionPasarDeTurno(juego));
        }
        return posibles;
    }
}
