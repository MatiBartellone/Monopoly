package org.monopoly.model;

import org.monopoly.model.casilla.Comprable;
import org.monopoly.model.casilla.Propiedad;

import java.util.List;
import java.util.Map;

public class AdmJugador {
    private List<Jugador> jugadores;
    private Banco banco;
    private RegistroComprables registroComprables;

    public AdmJugador(List<Jugador> jugadores, Map<Config.ColoresComprables, List<Comprable> > tablaBarrios){
        this.jugadores = jugadores;
        this.banco = new Banco(this.jugadores);
        this.registroComprables = new RegistroComprables(tablaBarrios, this.jugadores);
    }

    public void comprar(Jugador jugador, Comprable comprable) {
        this.banco.quitarDinero(jugador, comprable.getValorCompra());
        this.registroComprables.registrarCompra(comprable, jugador);
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
        this.banco.otorgarDinero(jugador, comprable.getValorHipoteca());
        comprable.hipotecar();
    }

    public void deshipotecar(Jugador jugador, Comprable comprable) {
        this.banco.quitarDinero(jugador, comprable.getValorHipoteca());
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

    public boolean alcanzaDinero(Jugador jugador, int cantidad){
        return this.banco.consultarDinero(jugador) > cantidad;
    }

    public int obtenerCantSet(Jugador jugador, Config.ColoresComprables color) {
        return this.registroComprables.obtenerCantSet(jugador, color);
    }

    public Jugador obtenerDuenio(Comprable comprable) {
        return this.registroComprables.obtenerDuenio(comprable);
    }

    public RegistroComprables getRegistroComprables(){
        return this.registroComprables;
    }

    private boolean condicionQuebrados(Jugador jugadorActual){
        for (Jugador jugador : this.jugadores){
            if (jugador.getEstado() != Config.EstadosJugadores.QUEBRADO && jugadorActual != jugador){
                return false;
            }
        }
        return true;
    }

    //public Map<Config.ColoresComprables, Integer> getTablaColoresJugador(Jugador jugador) {
   //     return this.registroComprables.getTablaColoresJugador(jugador);
    //}
    //private boolean condicionConstrucciones(Jugador jugador){
       // casas
   // }

    public boolean esGanador(Jugador jugador){
        //return condicionQuebrados(jugador) || condicionConstrucciones(jugador);
        return false;
    }


}

