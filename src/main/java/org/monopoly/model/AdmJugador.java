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

    public void comprar(Jugador jugador, Comparable comprable) {
        if (this.banco.quitarDinero(jugador, comprable.getValorCompra()))
            this.registroComprables.registrarCompra(jugador, comprable.getValorHipoteca());
    }

    public void construirConstruccion(Jugador jugador, Propiedad propiedad) {
        if (this.banco.quitarDinero(jugador, propiedad.getValorConstruir()))
            propiedad.construir();
    }

    public void venderConstruccion(Jugador jugador, Propiedad propiedad) {
        this.banco.otorgarDinero(jugador, propiedad.getValorDestruir());
        propiedad.destruir();
    }

    public void hipotecar(Jugador jugador, Comparable comparable) {
        this.banco.otorgarDinero(jugador, comparable.getValorHipoteca());
        comprable.hipotecar();
    }

    public void deshipotecar(Jugador jugador, Comparable comparable) {
        if (this.banco.quitarDinero(jugador, comparable.getValorHipoteca()))
            comprable.deshipotecar();
    }

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

    public boolean quitarDinero(Jugador jugador, int monto) {
        return this.banco.quitarDinero(jugador, monto);
    }

    public boolean transferir(Jugador receptor, Jugador emisor, int monto) {
        return this.banco.transferir(receptor, emisor, monto);
    }

    public int obtenerCantSet(Jugador jugador, Config.ColoresPropiedades color) {
        return this.registroComprables.obtenerCantSet(jugador, color);
    }

    public Jugador obtenerDuenio(Comparable comparable) {
        return this.registroComprables.obtenerDuenio(comparable);
    }


}

