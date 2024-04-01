package org.monopoly.model;

import org.monopoly.model.casilla.Casilla;
import org.monopoly.model.casilla.Comprable;

import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Jugador {
    private Config.ColoresJugadores color;
    private int dinero;
    private Casilla casillaActual;
    private List<Comprable> propiedades;
    private Map<Config.ColoresPropiedades, Integer> tablaColores;
    private Config.EstadosJugadores estado;
    private int turnosCarcel;

    public Jugador(Config.ColoresJugadores color, int dineroInicial, Casilla salida){
        this.color = color  ;
        this.dinero = dineroInicial;
        this.casillaActual = salida;
        this.propiedades = new ArrayList<Comprable>();
        this.estado = Config.EstadosJugadores.EN_JUEGO;
        this.turnosCarcel = 0;
        this.tablaColores = new HashMap<Config.ColoresPropiedades, Integer>();
    }

    public void sumarDinero(int monto){
        this.dinero += monto;
    }

    public boolean retirarDinero(int monto){
        if (monto > this.dinero){
            return false;
        }
        this.dinero -= monto;
        return true;
    }

    public void mover(Casilla nuevaCasilla){
        this.casillaActual = nuevaCasilla;
    }

    public void comprar(Comprable nuevaPropiedad){
        this.propiedades.add(nuevaPropiedad);

        Optional<Integer> cantColor = this.tablaColores.get(nuevaPropiedad.color);
        if (cantColor.isPresent()) {
            this.tablaColores.put(nuevaPropiedad.color, cantColor.get() + 1);
        } else {
            this.tablaColores.put(nuevaPropiedad.color, 1);
        }
    }

    public void encarcelar(){
        this.estado = Config.EstadosJugadores.PRESO;
    }
    public void liberar(){
        this.estado = Config.EstadosJugadores.EN_JUEGO;
    }
    public void entrarEnQuiebra(){
        this.estado = Config.EstadosJugadores.QUEBRADO;
    }

    public boolean tieneSetCompleto(Config.ColoresPropiedades color, int cantidadSet){
        return this.tablaColores.get(color) == cantidadSet;
    }

    public Casilla getCasillaActual(){
        return this.casillaActual;
    }

    public int getTurnosCarcel(){
        return this.turnosCarcel;
    }

    public int getDinero(){
        return this.dinero;
    }

    public Config.EstadosJugadores getEstado(){
        return this.estado;
    }
}
