package org.monopoly;

import org.monopoly.controller.JuegoController;
import org.monopoly.model.Config;
import org.monopoly.model.Juego;
import org.monopoly.model.Jugador;
import org.monopoly.model.Tablero;
import org.monopoly.model.casilla.Casilla;
import org.monopoly.model.casilla.Comprable;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Casilla> casillas = Config.ListaCasillas;

        Map<Config.ColoresComprables, List<Comprable>> tablaColores = Config.barrios();

        List<Jugador> jugadores = seleccionarJugadores(casillas.get(0));

        Tablero tablero = new Tablero(casillas);

        Juego juego = new Juego(tablero, jugadores, tablaColores);
        JuegoController controller = new JuegoController(juego);

        while(!juego.terminado()){
            controller.jugarTurno();
        }
    }

    private static List<Jugador> seleccionarJugadores(Casilla salida){
        Map<String, Config.ColoresJugadores> posiblesJugadores = new HashMap<>(){{
            put("SOMBRERO", Config.ColoresJugadores.SOMBRERO);
            put("AUTO", Config.ColoresJugadores.AUTO);
            put("BARCO", Config.ColoresJugadores.BARCO);
            put("PLANCHA", Config.ColoresJugadores.PLANCHA);
        }};
        List<Jugador> jugadores = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione cant jugadores (2 a 4)");
        int cant = Integer.parseInt(scanner.nextLine());
        for (int i = 1 ; i <= cant ; i++) {
            System.out.println("Seleccione jugador " + i);
            for (String j : posiblesJugadores.keySet()){
                System.out.println( j);
            }
            String seleccionado = scanner.nextLine();
            jugadores.add(new Jugador(posiblesJugadores.get(seleccionado), salida));
            posiblesJugadores.remove(seleccionado);
        }
        return jugadores;
    }
}