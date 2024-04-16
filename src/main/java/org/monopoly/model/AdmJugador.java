package org.monopoly.model;

import org.monopoly.model.casilla.Comprable;
import org.monopoly.model.casilla.Propiedad;

import java.util.List;
import java.util.Map;

public class AdmJugador {
    private List<Jugador> jugadores;
    private Banco banco;
    private RegistroComprables registroComprables;

    public AdmJugador(List<Jugador> jugadores, Map<Config.ColoresPropiedades, Integer> tablaColores){
        this.jugadores = jugadores;
        this.banco = new Banco(jugadores);
        this.registroComprables = new RegistroComprables(tablaColores, jugadores);
    }

    public void comprar(Jugador jugador, Comprable comprable) {
        this.banco.quitarDinero(jugador, comprable.getValorCompra());
        this.registroComprables.registrarCompra(jugador, comprable.getValorHipoteca());
    }

    public void construirConstruccion(Jugador jugador, Propiedad propiedad) {
        this.banco.quitarDinero(jugador, propiedad.getValorConstruir());
        propiedad.construir();
    }

    public void venderConstruccion(Jugador jugador, Propiedad propiedad) {
        this.banco.otorgarDinero(jugador, propiedad.getValorDestruir());
        propiedad.destruir();
    }

    public void hipotecar(Jugador jugador, Comprable comprable) {
        this.banco.otorgarDinero(jugador, comparable.getValorHipoteca());
        comprable.hipotecar();
    }

    public void deshipotecar(Jugador jugador, Comprable comprable) {
        this.banco.quitarDinero(jugador, comparable.getValorHipoteca());
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

    public void quitarDinero(Jugador jugador, int monto) {
        this.banco.quitarDinero(jugador, monto);
    }

    public void transferir(Jugador receptor, Jugador emisor, int monto) {
        this.banco.transferir(receptor, emisor, monto);
    }

    public int obtenerCantSet(Jugador jugador, Config.ColoresPropiedades color) {
        return this.registroComprables.obtenerCantSet(jugador, color);
    }

    public Jugador obtenerDuenio(Comprable comprable) {
        return this.registroComprables.obtenerDuenio(comprable);
    }


}

