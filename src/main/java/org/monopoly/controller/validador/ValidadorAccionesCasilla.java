package org.monopoly.controller.validador;

import org.monopoly.controller.accion.*;
import org.monopoly.model.Config;
import org.monopoly.model.Juego;
import org.monopoly.model.Jugador;
import org.monopoly.model.RegistroComprables;
import org.monopoly.model.casilla.Casilla;
import org.monopoly.model.casilla.Comprable;
import org.monopoly.model.casilla.Construible;
import org.monopoly.model.casilla.Propiedad;


import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class CalculadoraAccionesCasilla implements CalculadoraDeAcciones{
    private Juego juego;
    private RegistroComprables registro;
    private List<Propiedad> propiedades;
    private List<Casilla> construccionesDesbloqueadas;
    private List<Casilla> puedeVender;
    private List<Casilla> puedeHipotecar;
    private List<Casilla> deshipotecasDesbloqueadas;

    public CalculadoraAccionesCasilla(Juego juego) {
        this.juego = juego;
        this.registro = juego.getRegistroComprables();
    }
    public List<Accion> accionesPosibles(Jugador jugador){
        List<Accion> acciones = new ArrayList<Accion>();
        return acciones;
    }
    private List<Comprable> comprables(Jugador jugadorActual){
        List<Comprable> comprables = new ArrayList<>();
        Map<Comprable, Jugador> comprablesJugadores = this.registro.getTablaPropiedades();
        comprablesJugadores.forEach((comprable, jugador) -> {
          if (jugador == jugadorActual) {
              comprables.add((comprable));
          }
        });
        return comprables;
    }

    private List<Casilla> opcionesHipoteca(Jugador jugador){
        return List<Casilla> opcionesFiltradas = this.registro.hipotecadas(jugador);
    }

    private List<Casilla> opcionesDeshipoteca(Jugador jugador){
        List<Casilla> opcionesFiltradas = new ArrayList<>();
        for (Comprable comprable : this.registro.hipotecadas(jugador)){
            if (juego.alcanzaDinero(comprable.getValorCompra())){
                opcionesFiltradas.add()
            }
        }
        return ;
    }


    private List<Casilla> opcionesComprar(Jugador jugador){
        Comprable comprable = (Comprable) jugador.getCasillaActual();
        return (jugador.estaSobreCasillaComprable()
                && !this.registro.tieneDuenio(comprable)
                && juego.alcanzaDinero(comprable.getValorCompra())) ?
                new ArrayList<>(){{ add(comprable);}} :
                new ArrayList<>();
    }

    private List<Casilla> opcionesConstruirEnPropiedad(Jugador jugador){
        List<Casilla> opcionesFiltradas = new ArrayList<>();
        for(Config.ColoresComprables color: Config.ColoresComprables.values()){
            if(this.registro.poseeSetCompleto(jugador, color)
            && barrioSinHipotecar(this.registro.casasPorBarrio(color).keySet())
            ){
            }
        }


        for(Propiedad propiedad: this.propiedades){
            if (juego.alcanzaDinero(propiedad.getValorConstruir())){
                opcionesFiltradas.add(propiedad);
            }
        }
        return opcionesFiltradas;
    }

    private boolean barrioSinHipotecar(Set<Propiedad> propiedades){
        for (Propiedad propiedad: propiedades){
            if (propiedad.estaHipotecada()){
                return false;
            }
        }
        return true;
    }


    private List<Casilla> opcionesVentaConstruccion(Jugador jugador){
        List<Casilla> opcionesFiltradas = new ArrayList<>();
        for(Propiedad propiedad: this.propiedades){
            if (propiedad.getCantConstruidos()>0
                    && juego.alcanzaDinero(propiedad.getValorConstruir())
                    && !propiedad.estaHipotecada()){
                opcionesFiltradas.add(propiedad);
        return opcionesFiltradas;
    }



}