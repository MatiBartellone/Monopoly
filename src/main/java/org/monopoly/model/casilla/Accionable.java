package org.monopoly.model.casilla;

import org.monopoly.model.AdmJugador;
import org.monopoly.model.Jugador;

public interface Accionable {
    void accionar(AdmJugador administrador, Jugador jugador);
}
