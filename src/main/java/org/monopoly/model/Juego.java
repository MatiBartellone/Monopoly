package org.monopoly.model;

import org.monopoly.model.casilla.Casilla;
import org.monopoly.model.casilla.Comprable;
import org.monopoly.model.casilla.Propiedad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Juego {
    //puede que el observerOptions esté suscripto al juego y que cada vez que se realice un método de juego se le avise a los observers
    private final List<Jugador> jugadores;
    private final Tablero tablero;
    private final AdmTurnos admTurnos;
    private final AdmMovimientos admMovimientos;
    private final AdmJugador admJugador;

    public Juego( Tablero tablero, List<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.tablero = new Tablero();
        this.admTurnos = new AdmTurnos(jugadores);
        this.admMovimientos = new AdmMovimientos(tablero);
        this.admJugador = new AdmJugador(jugadores);
    }
    public void siguienteTurno(){this.admTurnos.siguiente();}
    public void tirarDados(){this.admMovimientos.tirarDados();}
    public void pagarFianza(){admJugador.pagarFianza(admTurnos.getJugadorActual());}
    public void mover() {
        if (this.validarEncarcelamiento()) {admMovimientos.mover(admTurnos.getJugadorActual());}
    }

    public void comprar(){
        //¿estaría rompiendo el principio de polK?
        Casilla casilla = admTurnos.getJugadorActual().getCasillaActual();
        if (tablero.esCasillaComprable(casilla)){
            admJugador.comprar(admTurnos.getJugadorActual(),tablero.getComprable(casilla));
        }
    }
    //la propiedad se sacará de los observers que tienen guardadas las opciones de propiedades donde contruir

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
    public boolean terminado(){
        return this.unicoEnJuego() || this.hayGanador();
    }
    private boolean validarEncarcelamiento(){
        Jugador jugador = this.admTurnos.getJugadorActual();
        if (jugador.getEstado() == Config.EstadosJugadores.EN_JUEGO){return true;}
        if (jugador.getTurnosCarcel() == 0 || admMovimientos.sonDadosIguales()){
            admJugador.liberar(jugador);
            admMovimientos.tirarDados();
            return true;
        }
        jugador.setTurnosCarcel(jugador.getTurnosCarcel()-1);
        return false;
    }
    private boolean unicoEnJuego(){
        int enJuego = 0;
        for (Jugador jugador: jugadores){
            if (jugador.getEstado()==Config.EstadosJugadores.EN_JUEGO){enJuego++;}
            if (enJuego > 1){return false;}
        }
        return true;
    }
    private boolean hayGanador(){
        for (Jugador jugador: jugadores){
            if (admJugador.esGanador(jugador)){return true;}
        }
        return false;
    }
}
