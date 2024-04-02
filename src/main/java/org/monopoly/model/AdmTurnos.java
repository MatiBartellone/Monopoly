package org.monopoly.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class AdmTurnos {
    private final List<Jugador> jugadores;
    private int turno;
    public AdmTurnos(Set<Jugador> jugadores){
        this.turno = 0;
        this.jugadores = new ArrayList<Jugador>(jugadores);
        Collections.shuffle(this.jugadores);
    }
    public void Siguiente(){
        if (this.turno < this.jugadores.size() - 1 ){
            this.turno += 1;
        }else {
           this.turno = 0;
        }
    }
    public Jugador getJugadorActual(){
        return this.jugadores.get(this.turno);
    }
    public void eliminarJugador(Jugador perdedor){
        this.jugadores.removeIf(elemento -> elemento==perdedor);
    }
}
//nota, en juego se tendria que eliminar a todos los quebrados verificando su estado
//nota, si un jugador entra en quiebra tambien se le tiene que quitar del set de jugadores del juego
