package org.monopoly.model.casilla;

import org.monopoly.model.Config;
import java.util.List;

public abstract class Comprable extends Casilla{
    private String nombre;
    protected Config.ColoresComprables color;
    protected int valorCompra;
    protected int valorRentaBasica;
    protected int valorHipoteca;
    protected boolean estaHipotecada;

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
        int renta = this.calcularAlquiler(admJugador, jugador);
        admJugador.transferir(admJugador.obtenerDuenio(this), jugador, renta);
    }
    abstract int calcularAlquiler(AdmJugador admJugador, Jugador jugador);
}
