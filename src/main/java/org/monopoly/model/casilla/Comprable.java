package org.monopoly.model.casilla;

import java.util.List;

public abstract class Comprable extends Casilla{
    private String nombre;
    protected Config.ColoresComprables color;
    private int valorCompra;
    private int valorRentaBasica;
    private int valorHipoteca;
    private boolean estaHipotecada;

    public Comprable(Config.TiposCasillas tipo, String nombre, int valorCompra, int valorRentaBasica, Config.ColoresComprables color){
        super(tipo);
        this.nombre = nombre;
        this.color = color;
        this.valorCompra = valorCompra;
        this.valorRentaBasica = valorRentaBasica;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getValorCompra(){
        return this.valorCompra;
    }

    public Config.ColoresComprables getColor() {
        return this.color;
    }

    public void hipotecar(){
        this.estaHipotecada = true;
    }

    public void deshipotecar(){
        this.estaHipotecada = false;
    }

    public void accionar(AdmJugador admJugador, Jugador jugador){
        if !sePuedeAccionar(admJugador, jugador){
            return;
        }
        this.cobrarAlquiler(admJugador, jugador);
    }

    private abstract boolean sePuedeAccionar(AdmJugador admJugador, Jugador jugador);
    private abstract void cobrarAlquiler(AdmJugador admJugador, Jugador jugador);
}
