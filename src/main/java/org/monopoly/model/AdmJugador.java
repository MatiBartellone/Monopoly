package org.monopoly.model;

import org.monopoly.model.casilla.Propiedad;

import java.util.List;

public class AdmJugador {
    private List<Jugador> jugadores;
    private Banco banco;
    private RegistroComprables registroComprables;

    public AdmJugador(List<Jugador> jugadores){
        this.jugadores = jugadores;
        this.banco = new Banco(jugadores);
        this.registroComprables = new RegistroComprables();
    }

    public void comprar(Jugador jugador, Comparable comprable) {}

    public void construirConstruccion(Jugador jugador, Propiedad propiedad) {}

    public void venderConstruccion(Jugador jugador, Propiedad propiedad) {}

    public void hipotecar(Jugador jugador, Comparable comparable) {}

    public void deshipotecar(Jugador jugador, Comparable comparable) {}

    public void encarcelar(Jugador jugador) {
        jugador.setTurnosCarcel(Config.TurnosCarcel);
        jugador.setEstado(Config.EstadosJugadores.PRESO);
    }

    public void liberar(Jugador jugador) {
        jugador.setTurnosCarcel(0);
        jugador.setEstado(Config.EstadosJugadores.EN_JUEGO);
    }

    public void entrarEnQuiebra(Jugador jugador) {
        jugador.setEstado(Config.EstadosJugadores.QUEBRADO);
    }

    public void pagarFianza(Jugador jugador) {
        this.banco.quitarDinero(jugador, Config.ValorFianza);
        this.liberar(jugador);
    }

    public void otorgarDinero(Jugador jugador, int monto) {
        this.banco.otorgarDinero(jugador, monto);
    }

    public void quitarDinero(Jugador jugador, int monto) {
        this.banco.quitarDinero(jugador, monto);
    }

    public void transferir(Jugador receptor, Jugador emisor, int monto) {
        this.banco.transferir(receptor, emisor, monto);
    }

    public int obtenerCantSet(Jugador jugador, Config.ColoresPropiedades color) {
        return this.registroComprables.obtenerCantSet(jugador, color);
    }

    public Jugador obtenerDuenio(Comparable comparable) {
        return this.registroComprables.obtenerDuenio(comparable);
    }


}

