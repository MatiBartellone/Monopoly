package org.monopoly.controller.validador;

import org.monopoly.controller.accion.*;
import org.monopoly.model.Config;
import org.monopoly.model.Juego;
import org.monopoly.model.Jugador;
import org.monopoly.model.RegistroComprables;
import org.monopoly.model.casilla.Casilla;
import org.monopoly.model.casilla.Comprable;
import org.monopoly.model.casilla.Propiedad;


import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CalculadoraAccionesCasilla implements CalculadoraDeAcciones {
    private Juego juego;
    private RegistroComprables registro;


    public CalculadoraAccionesCasilla(Juego juego) {
        this.juego = juego;
        this.registro = juego.getRegistroComprables();
    }

    public List<Accion> accionesPosibles(Jugador jugador) {
        List<Accion> acciones = new ArrayList<Accion>();
        return acciones;
    }

    private List<Casilla> comprables(Jugador jugadorActual) {
        List<Casilla> comprables = new ArrayList<>();
        Map<Comprable, Jugador> comprablesJugadores = this.registro.getTablaPropiedades();
        comprablesJugadores.forEach((comprable, jugador) -> {
            if (jugador == jugadorActual) {
                comprables.add(comprable);
            }
        });
        return comprables;
    }

    private List<Casilla> opcionesHipoteca(Jugador jugador) {
        List<Casilla> opcionesFiltradas = new ArrayList<>();
        List<Casilla> comprables = this.comprables(jugador);
        for (Comprable comprable : this.registro.hipotecadas(jugador)) {
            comprables.remove(comprable);
        }
        return opcionesFiltradas;
    }

    private List<Casilla> opcionesDeshipoteca(Jugador jugador) {
        List<Casilla> opcionesFiltradas = new ArrayList<>();
        for (Comprable comprable : this.registro.hipotecadas(jugador)) {
            if (juego.alcanzaDinero(comprable.getValorCompra())) {
                opcionesFiltradas.add(comprable);
            }
        }
        return opcionesFiltradas;
    }


    private List<Casilla> opcionesComprar(Jugador jugador) {
        Comprable comprable = (Comprable) jugador.getCasillaActual();
        return (jugador.estaSobreCasillaComprable()
                && !this.registro.tieneDuenio(comprable)
                && juego.alcanzaDinero(comprable.getValorCompra())) ?
                new ArrayList<>() {{
                    add(comprable);
                }} :
                new ArrayList<>();
    }

    private List<Casilla> opcionesConstruirEnPropiedad(Jugador jugador) {
        List<Casilla> opcionesFiltradas = new ArrayList<>();
        for (Config.ColoresComprables color : Config.ColoresComprables.values()) {
            if (this.registro.poseeSetCompleto(jugador, color)
                    && barrioSinHipotecar(this.registro.casasPorBarrio(color).keySet())) {
                opcionesFiltradas.addAll(this.mantieneDiferencia(color, 1));
            }
        }
        for (Casilla casilla: opcionesFiltradas){
            Propiedad propiedad = (Propiedad) casilla;
            if (!juego.alcanzaDinero(propiedad.getValorConstruir())){
                opcionesFiltradas.remove(propiedad);
            }
        }
        return opcionesFiltradas;
    }

    private List<Casilla> mantieneDiferencia(Config.ColoresComprables color, int diferencia) {
        AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);
        this.registro.casasPorBarrio(color).forEach((comprable, cant) -> {
            if (cant< min.get()) {
                min.set(cant);
            }
        });
        List<Casilla> mantienenDif = new ArrayList<>();
        this.registro.casasPorBarrio(color).forEach((comprable, cant) -> {
            if (cant+1-min.get()<=diferencia){
                mantienenDif.add(comprable);
            }
        });
        return mantienenDif;
    }

    private boolean barrioSinHipotecar(Set<Propiedad> propiedades){
        for (Propiedad propiedad: propiedades){
            if (propiedad.estaHipotecada()){
                return false;
            }
        }
        return true;
    }

    private List<Casilla> mantieneDiferenciaVenta(Config.ColoresComprables color, int diferencia) {
        AtomicInteger max = new AtomicInteger(0);
        this.registro.casasPorBarrio(color).forEach((comprable, cant) -> {
            if (cant > max.get()) {
                max.set(cant);
            }
        });
        List<Casilla> mantienenDif = new ArrayList<>();
        this.registro.casasPorBarrio(color).forEach((comprable, cant) -> {
            if (max.get()-(cant-1) <= diferencia){
                mantienenDif.add(comprable);
            }
        });
        return mantienenDif;
    }

    private List<Casilla> opcionesVentaConstruccion(Jugador jugador){
        List<Casilla> opcion-esFiltradas = new ArrayList<>();
        for(Propiedad propiedad: this.propiedades){
            if (propiedad.getCantConstruidos()>0
                    && juego.alcanzaDinero(propiedad.getValorConstruir())
                    && !propiedad.estaHipotecada()){
                opcionesFiltradas.add(propiedad);
        return opcionesFiltradas;
    }
}