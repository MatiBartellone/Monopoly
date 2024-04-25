package org.monopoly.controller.validador;

import org.monopoly.model.RegistroComprables;
import org.monopoly.model.Jugador;
import org.monopoly.model.Juego;
import org.monopoly.model.casilla.Casilla;
import org.monopoly.model.casilla.Comprable;
import org.monopoly.model.casilla.Propiedad;

import java.util.ArrayList;
import java.util.List;

public class Calculadora implements Validador{
    private Juego juego;
    private RegistroComprables registro;

    private List<Casilla> opcionesComprar(Jugador jugador){
        Comprable comprable = (Comprable) jugador.getCasillaActual();
        return (jugador.estaSobreCasillaComprable()
                && !this.registro.tieneDuenio(comprable)
                && juego.alcanzaDinero(comprable.getValorCompra())) ?
                new ArrayList<>(){{ add(comprable);}} :
                new ArrayList<>();
    }
    private construccionesDesbloqueadas(Jugador jugador){

    }
    private List<Casilla> opcionesComprar(Jugador jugador){
        Comprable comprable = (Comprable) jugador.getCasillaActual();
        return (jugador.estaSobreCasillaComprable()
                && !this.registro.tieneDuenio(comprable)
                && juego.alcanzaDinero(comprable.getValorCompra())) ?
                new ArrayList<>(){{ add(comprable);}} :
                new ArrayList<>();
    }

    private List<Casilla> opcionesConstruir(Jugador jugador){
        List<Casilla> opcionesFiltradas = new ArrayList<>();
        for(Casilla casilla: this.construccionesDesbloqueadas.get(jugador)){
            Propiedad propiedad = (Propiedad) casilla;
            if (juego.alcanzaDinero(propiedad.getValorConstruir())){
                opcionesFiltradas.add(propiedad);
            }
        }
        return opcionesFiltradas;
    }

    private List<Casilla> opcionesVenta(Jugador jugador){
        return this.puedeVender.get(jugador);
    }

    private List<Casilla> opcionesHipoteca(Jugador jugador){
        return this.puedeHipotecar.get(jugador);
    }

    private List<Casilla> opcionesDeshipoteca(Jugador jugador){
        List<Casilla> opcionesFiltradas = new ArrayList<>();
        for(Casilla casilla: this.deshipotecasDesbloqueadas.get(jugador)){
            Comprable comprable = (Comprable) casilla;
            if (juego.alcanzaDinero(comprable.getValorHipoteca())){
                opcionesFiltradas.add(comprable);
            }
        }
        return opcionesFiltradas;
    }
}
