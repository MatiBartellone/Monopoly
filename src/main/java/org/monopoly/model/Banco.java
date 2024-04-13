package org.monopoly.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Banco {
    private Map<Jugador, CuentaBancaria> cuentasJugadores;

    public Banco(List<Jugador> jugadores){
        this.cuentasJugadores = new HashMap<Jugador, CuentaBancaria>();
        for (Jugador j : jugadores){
            this.cuentasJugadores.put(j, new CuentaBancaria(Config.DineroInicial));
        }
    }

    public void otorgarDinero(Jugador jugador, int monto){
        this.cuentasJugadores.get(jugador).sumarDinero(monto);
    }
    public boolean recibirDinero(Jugador jugador, int monto){
        CuentaBancaria cuenta = this.cuentasJugadores.get(jugador);
        if (!cuenta.poseeDinero(monto)) return false;
        cuenta.retirarDinero(monto);
        return true;
    }

    public boolean transferir(Jugador receptor, Jugador emisor, int monto){
        if (this.recibirDinero(emisor, monto)) return false;
        this.otorgarDinero(receptor, monto);
        return true;
    }
}
