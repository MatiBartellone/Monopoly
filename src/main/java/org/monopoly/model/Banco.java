package org.monopoly.model;

public class Banco {
    private int dinero;

    public Banco(int dineroTotal){
        this.dinero = dineroTotal;
    }

    public void otorgarDinero(Jugador jugador, int monto){
        jugador.sumarDinero(monto);
        dinero -= monto;
    }
    public boolean recibirDinero(Jugador jugador, int monto){
        if (jugador.getDinero() < monto){
            return false;
        }
        jugador.retirarDinero(monto);
        dinero += monto;
        return true;
    }
}
