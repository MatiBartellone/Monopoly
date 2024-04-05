package org.monopoly.model.casilla;

import java.util.List;

public abstract class Comprable extends Casilla implements ConEfecto {
    private String nombre;
    private Jugador dueño;
    private int valorCompra;
    private int valorRentaBasica;
    protected Config.ColoresComprables color;

    public Comprable(Config.TiposCasillas tipo, String nombre, int valorCompra, int valorRentaBasica, Config.ColoresComprables color){
        super(tipo);
        this.nombre = nombre;
        this.dueño = null;
        this.valorCompra = valorCompra;
        this.valorRentaBasica = valorRentaBasica;
        this.color = color;
    }

    public Jugador getDueño() {
        return this.dueño;
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

    public void setDueño(Jugador dueño) {
        this.dueño = dueño;
    }

    public void comprar(Jugador jugador){
        if this.dueño != null || jugador.getCasillaActual() != this{
            return;
        }
        jugador.comprar(this) // donde chequea que tenga la plata disponible?
        this.dueño = jugador
    }

    public abstract void aplicarEfecto(Jugador jugador);
}
