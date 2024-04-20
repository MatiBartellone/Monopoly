package org.monopoly.model.casilla;

import org.monopoly.model.AdmJugador;
import org.monopoly.model.Config;
import org.monopoly.model.Jugador;

import java.util.List;
import java.util.Objects;

public abstract class Comprable implements Casilla{

    private Config.TiposCasillas tipo;
    private String nombre;
    protected Config.ColoresComprables color;
    protected int valorCompra;
    protected int valorRentaBasica;
    protected int valorHipoteca;
    protected boolean estaHipotecada;

    public Comprable(Config.TiposCasillas tipo, String nombre, int valorCompra, int valorRentaBasica, Config.ColoresComprables color){
        this.tipo = tipo;
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

    public int getValorHipoteca(){
        return this.valorHipoteca;
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

    public Config.TiposCasillas getTipo() {
        return this.tipo;
    }

    public void accionar(AdmJugador admJugador, Jugador jugador) {
        int renta = this.calcularAlquiler(admJugador, jugador);
        if (admJugador.alcanzaDinero(jugador, renta)) {
            admJugador.transferir(admJugador.obtenerDuenio(this), jugador, renta);
            return;
        }
        jugador.setEstado(Config.EstadosJugadores.CRISIS);
    }
    abstract int calcularAlquiler(AdmJugador admJugador, Jugador jugador);

    @Override
    public int hashCode() {
        return Objects.hash(this.nombre, this.color);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Comprable otroComprable = (Comprable) obj;
        return Objects.equals(this.nombre, otroComprable.nombre) &&
                this.color == otroComprable.color;
    }
}
