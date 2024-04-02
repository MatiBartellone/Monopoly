package org.monopoly.model;

import java.util.concurrent.ThreadLocalRandom;

public class AdmMovimientos {
    private final Tablero tablero;
    public AdmMovimientos(Tablero tablero){
        this.tablero = tablero;
    }
    public int randomizador(){
        return ThreadLocalRandom.current().nextInt(1,6+1);
    }
    public void mover(Jugador jugador, int pasos){
        jugador.mover(tablero.casillaSiguiente(jugador.getCasillaActual(),pasos));
    }
}
//aqui deberia de ver el estado de los
