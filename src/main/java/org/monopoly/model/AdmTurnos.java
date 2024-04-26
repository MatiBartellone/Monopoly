package org.monopoly.model;

import org.monopoly.model.casilla.Casilla;
import java.util.*;

public class AdmTurnos {
    private List<Jugador> jugadores;
    private int turno;

    public AdmTurnos(List<Jugador> jugadores){
        this.turno = 0;
        Collections.shuffle(jugadores);
        this.jugadores = jugadores;
        Collections.shuffle(this.jugadores);
    }
    //ojo tiene que haber un siguiente jugador que no esté quebrado
    //maneja si el jugador que está terminando su turno acaba de cumplir su ultimo turno en prision
    public void siguiente(){
        int iJugador = this.sgteListaCircular();
        this.turno = iJugador;
        while (jugadores.get(iJugador).getEstado() == Config.EstadosJugadores.QUEBRADO){
            iJugador = this.sgteListaCircular();
            this.turno = iJugador;
        }
    }
    public Jugador getJugadorActual(){return this.jugadores.get(this.turno);}

    public Casilla getCasillaActual() {return this.getJugadorActual().getCasillaActual(); }
    private int sgteListaCircular(){
        if (this.turno < this.jugadores.size() - 1 ){return this.turno + 1;}
        return 0;
    }
}

