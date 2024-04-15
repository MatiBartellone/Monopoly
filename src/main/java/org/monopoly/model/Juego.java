package org.monopoly.model;

import org.monopoly.model.casilla.Casilla;
import org.monopoly.model.casilla.Comprable;
import org.monopoly.model.casilla.Propiedad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Juego {
    //puede que el observerOptions esté suscripto al juego y que cada vez que se realice un método de juego se le avise al
    private final List<Jugador> jugadores;
    private final Tablero tablero;
    private final AdmTurnos admTurnos;
    private final AdmMovimientos admMovimientos;
    private final AdmJugador admJugador;

    public Juego(List<Jugador> jugadores, Tablero tablero, int dineroTotal) {
        this.jugadores = jugadores;
        this.tablero = tablero;
        this.admTurnos = new AdmTurnos(jugadores);
        this.admMovimientos = new AdmMovimientos(tablero);
        this.admJugador = new AdmJugador(jugadores);
    }
    public void siguienteTurno(){this.admTurnos.siguiente();}
    public void tirarDados(){this.admMovimientos.tirarDados();}
    public void pagarFianza(){
        Jugador jugadorActual = admTurnos.getJugadorActual();
        admJugador.pagarFianza(jugadorActual);
        //deberia haber una validación entre pagar la fianza y el resto no?¿devuelvve un error?
        admJugador.liberar(jugadorActual);
    }
    public void mover() {
        if (this.validarEncarcelamiento()) {
            admMovimientos.mover(admTurnos.getJugadorActual());
        }
    }
    private boolean validarEncarcelamiento(){
        Jugador jugador = this.admTurnos.getJugadorActual();
        if (jugador.getEstado() == Config.EstadosJugadores.EN_JUEGO){return true;}
        if (jugador.getTurnosCarcel() == 0 || admMovimientos.sonDadosIguales()){
            admJugador.liberar(jugador);
            return true;
        }
        jugador.setTurnosCarcel(jugador.getTurnosCarcel()-1);
        return false;
    }
    public void comprar(){
        Jugador jugador =admTurnos.getJugadorActual();
        this.admJugador.comprar(jugador,jugador.getCasillaActual());
    }
    public void construirConstruccion(Propiedad lugar){
        admJugador.construirContruccion(admTurnos.getJugadorActual(),lugar);
    }
    public void venderConstruccion(Propiedad lugar){
        admJugador.venderConstruccion(admTurnos.getJugadorActual(), lugar);
    }
    public void hipotecar(Comprable compra){
        admJugador.hipotecar(admTurnos.getJugadorActual(), compra);
    }
    public void deshipotecar(Comprable compra){
        admJugador.deshipotecar(admTurnos.getJugadorActual(),compra);
    }
}
//
