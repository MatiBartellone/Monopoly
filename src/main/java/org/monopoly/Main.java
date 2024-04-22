package org.monopoly;

import org.monopoly.controller.JuegoController;
import org.monopoly.model.Config;
import org.monopoly.model.Juego;
import org.monopoly.model.Jugador;
import org.monopoly.model.Tablero;
import org.monopoly.model.casilla.Casilla;
import org.monopoly.model.casilla.Comprable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        List<Casilla> casillas = Config.ListaCasillas;

        Map<Config.ColoresComprables, List<Comprable>> tablaColores = Config.TablaColores;

        List<Jugador> jugadores = new ArrayList<>(){{
            add(new Jugador(Config.ColoresJugadores.SOMBRERO, casillas.get(0)));
            add(new Jugador(Config.ColoresJugadores.AUTO, casillas.get(0)));
        }};

        Tablero tablero = new Tablero(casillas);

        Juego juego = new Juego(tablero, jugadores, tablaColores);
        JuegoController controller = new JuegoController(juego);

        while(!juego.terminado()){
            controller.jugarTurno();
        }
    }
}