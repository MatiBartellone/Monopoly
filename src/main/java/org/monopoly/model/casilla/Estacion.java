package org.monopoly.model.casilla;

public class Estacion extends Comprable {

    private int cantEstaciones;
    public Propiedad(Config.TiposCasillas tipo, String nombre, int valorCompra, int valorRentaBasica, Config.ColoresComprables color){
        super(tipo, nombre, valorCompra, rentaBasica, color);
    }

    public void aumentarEstaciones(){
        this.cantEstaciones++
    }

    public void disminuirEstaciones(){
        this.cantEstaciones--
    }

    private boolean sePuedeAccionar(AdmJugador admJugador, Jugador jugador){
        return admJugador.tieneDueño(this) && jugador.getCasillaActual() == this && admJugador.obtenerCantSet(jugador, this.color) == 0
    }

    private void cobrarAlquiler(AdmJugador admJugador, Jugador jugador){
        renta = this.rentaBasica * admJugador.obtenerCantSet(jugador, this.color);
        admJugador(jugador, admJugador.obtenerDueño(this), renta)
    }
}