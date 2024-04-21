package org.monopoly.controller.validador;

import org.monopoly.controller.accion.Accion;
import org.monopoly.model.Jugador;

import java.util.List;

public interface Validador {
    public List<Accion> accionesPosibles(Jugador jugador);
}
