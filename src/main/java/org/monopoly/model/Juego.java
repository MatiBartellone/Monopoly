package org.monopoly.model;

import org.monopoly.model.casilla.Casilla;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Juego {
    private final Set<Jugador> jugadores;
    private final Tablero tablero;
    private final AdmTurnos admTurnos;
    private final AdmMovimientos admMovimientos;
    private final Banco banco;

    //para el constructor lo que haremos es que el juego reciba ya la lista de jugadores, pues el juego conyroller debe verificar que sea una cantidad válida
    //entonces ya recibe una lista de
    public Juego(Set<Jugador> jugadores, Tablero tablero, int dineroTotal) {
        this.jugadores = jugadores;
        this.tablero = tablero;
        this.admTurnos = new AdmTurnos(jugadores);
        this.admMovimientos = new AdmMovimientos(tablero);
        this.banco = new Banco(dineroTotal);
    }
    //para mover el el jugador el juego debe enviarle el jugador
    public void cambiarTurno(){
        this.admTurnos.Siguiente();
    }
    public int tirarDados(){
        return this.admMovimientos.randomizador();
    }
    //no puedo suponer desde juego que no se está tratando de mover a un jugador que esté preso??
    public void mover(int pasos){this.admMovimientos.mover(this.admTurnos.getJugadorActual(),pasos);}
    public boolean ejecutarCompra(Comprable comprable, Jugador jugador){
        jugador.comprar(comprable);
        tablero.obtenerCasilla().comprar(jugador);

    }

}
//
