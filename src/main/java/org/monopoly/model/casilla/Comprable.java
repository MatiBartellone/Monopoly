package org.monopoly.model.casilla;

import java.util.List;

public abstract class Comprable extends Casilla implements ConEfecto {
    private String nombre;
    private Jugador dueño;
    private int valorCompra;
    protected Config.ColoresComprables color;

    public Comprable(Config.TiposCasillas tipo, String nombre, Jugador jugador,  int valorCompra, Config.ColoresComprables color){
        super(tipo);
        this.nombre = nombre;
        this.dueño = jugador
        this.valorCompra = valorCompra;
        this.color = color;
    }

    public Jugador getDueño() {
        return this.dueño;
    }

    public int getValorCompra(){
        return this.valorCompra
    }

    public abstract int getRenta();

    public String getNombre() {
        return this.nombre;
    }

    public void comprar(Jugador jugador){
        if this.dueño == null && jugador.getCasillaActual() == this{
            jugador.comprar(this)
            this.dueño = jugador
        }
    }

    public Config.ColoresComprables getColor() {
        return this.color;
    }

    public abstract void aplicarEfecto(Jugador jugador);
}
