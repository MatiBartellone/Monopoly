package org.monopoly.model.casilla;

import org.monopoly.model.Config;

public class Estacion extends Comprable {

    private int cantEstaciones;
    public Estacion(Config.TiposCasillas tipo, String nombre, int valorCompra, int valorRentaBasica, Config.ColoresComprables color){
        super(tipo, nombre, valorCompra, valorRentaBasica, color);
    }

    public void aumentarEstaciones(){
        this.cantEstaciones++;
    }

    public void disminuirEstaciones(){
        this.cantEstaciones--;
    }

    protected int calcularAlquiler(AdmJugador admJugador, Jugador jugador){
        return this.valorRrentaBasica * admJugador.obtenerCantSet(jugador, this.color);
    }
}