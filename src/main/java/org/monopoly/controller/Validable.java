package org.monopoly.controller;

import org.monopoly.model.Jugador;

import java.util.List;

public interface Validador {
    public List<List> accionesPosibles(Jugador jugador);
}
