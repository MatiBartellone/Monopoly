package org.monopoly.model;

import org.monopoly.model.casilla.Casilla;
import org.monopoly.model.casilla.Comprable;
import org.monopoly.model.casilla.Propiedad;

import java.util.*;

public class Juego {
    //puede que el observerOptions esté suscripto al juego y que cada vez que se realice un método de juego se le avise a los observers
    private final List<Jugador> jugadores;
    private final Tablero tablero;
    private final AdmTurnos admTurnos;
    private final AdmMovimientos admMovimientos;
    private final AdmJugador admJugador;

    public Juego( Tablero tablero, List<Jugador> jugadores, Map<Config.ColoresComprables, Integer> tablaColores) {
        this.jugadores = jugadores;
        this.tablero = tablero;
        this.admTurnos = new AdmTurnos(this.jugadores);
        this.admMovimientos = new AdmMovimientos(this.tablero);
        this.admJugador = new AdmJugador(this.jugadores, tablaColores);
    }
    public void siguienteTurno(){this.admTurnos.siguiente();}
    public void tirarDados(){this.admMovimientos.tirarDados();}
    public void pagarFianza(){this.admJugador.pagarFianza(this.admTurnos.getJugadorActual());}
    public void mover() {
        if (this.validarEncarcelamiento()) {
            Jugador jugador = this.admTurnos.getJugadorActual();
            if (this.admMovimientos.mover(jugador)) this.pagarPasoSalida(jugador);
            jugador.getCasillaActual().accionar(this.admJugador,jugador);
        }
    }

    public void comprar(){
        Casilla casilla = this.admTurnos.getCasillaActual();
        if(casilla instanceof Comprable comprable)
            this.admJugador.comprar(this.admTurnos.getJugadorActual(), comprable);
    }
    //la propiedad se sacará de los observers que tienen guardadas las opciones de propiedades donde contruir

    public void construirConstruccion(Propiedad lugar){
        this.admJugador.construirConstruccion(this.admTurnos.getJugadorActual(),lugar);
    }
    public void venderConstruccion(Propiedad lugar){
        this.admJugador.venderConstruccion(this.admTurnos.getJugadorActual(), lugar);
    }
    public void hipotecar(Comprable compra){
        this.admJugador.hipotecar(this.admTurnos.getJugadorActual(), compra);
    }
    public void deshipotecar(Comprable compra){
        this.admJugador.deshipotecar(this.admTurnos.getJugadorActual(),compra);
    }
    public boolean terminado(){
        return this.unicoEnJuego() || this.hayGanador();
    }
    private boolean validarEncarcelamiento(){
        Jugador jugador = this.admTurnos.getJugadorActual();
        if (jugador.getEstado() == Config.EstadosJugadores.EN_JUEGO){return true;}
        if (jugador.getTurnosCarcel() == 0 || this.admMovimientos.sonDadosIguales()){
            this.admJugador.liberar(jugador);
            this.admMovimientos.tirarDados();
            return true;
        }
        jugador.setTurnosCarcel(jugador.getTurnosCarcel()-1);
        return false;
    }
    private boolean unicoEnJuego(){
        int enJuego = 0;
        for (Jugador jugador: jugadores){
            if (jugador.getEstado()==Config.EstadosJugadores.EN_JUEGO ||
                    jugador.getEstado()==Config.EstadosJugadores.PRESO) enJuego++;
            if (enJuego > 1){return false;}
        }
        return true;
    }
    private boolean hayGanador(){
        for (Jugador jugador: jugadores){
            if (this.admJugador.esGanador(jugador)){return true;}
        }
        return false;
    }
    private void pagarPasoSalida(Jugador jugador){ this.admJugador.otorgarDinero(jugador,Config.PagoPorSalida);}
}
