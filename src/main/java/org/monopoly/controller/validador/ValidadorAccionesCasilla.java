package org.monopoly.controller.validador;

import org.monopoly.controller.accion.*;
import org.monopoly.model.Config;
import org.monopoly.model.Juego;
import org.monopoly.model.Jugador;
import org.monopoly.model.RegistroComprables;
import org.monopoly.model.casilla.Casilla;
import org.monopoly.model.casilla.Comprable;
import org.monopoly.model.casilla.Propiedad;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class ValidadorAccionesCasilla implements Validador{
    private Juego juego;
    private RegistroComprables registro;
    private Map<Jugador, List<Casilla>> construccionesDesbloqueadas;
    private Map<Jugador, List<Casilla>> puedeVender;
    private Map<Jugador, List<Casilla>> puedeHipotecar;
    private Map<Jugador, List<Casilla>> deshipotecasDesbloqueadas;

    public ValidadorAccionesCasilla(Juego juego) {
        this.juego = juego;
        this.registro = juego.getRegistroComprables();
        this.construccionesDesbloqueadas = new HashMap<>();
        this.puedeVender = new HashMap<>();
        this.puedeHipotecar = new HashMap<>();
        this.deshipotecasDesbloqueadas = new HashMap<>();

        // Inicializar las listas vacías para cada jugador
        for (Jugador jugador : juego.getJugadores()) {
            construccionesDesbloqueadas.put(jugador, new ArrayList<>());
            puedeVender.put(jugador, new ArrayList<>());
            puedeHipotecar.put(jugador, new ArrayList<>());
            deshipotecasDesbloqueadas.put(jugador, new ArrayList<>());
        }
    }
    public void registrarCompraPropiedad(Comprable comprable, Jugador jugador){
        if (this.registro.poseeSetCompleto(jugador, comprable.getColor())){
            this.registro.casasPorBarrio(comprable.getColor()).forEach((clave, valor) -> {
                this.construccionesDesbloqueadas.get(jugador).add(clave);
            });
        }
        this.puedeHipotecar.get(jugador).add(comprable);
    }

    public void registrarConstruccion(Propiedad propiedad, Jugador jugador){
        this.construccionesDesbloqueadas.get(jugador).remove(propiedad);

        if (this.construccionesDesbloqueadas.get(jugador).isEmpty()){
            this.registro.casasPorBarrio(propiedad.getColor()).forEach((clave, valor) -> {
                this.construccionesDesbloqueadas.get(jugador).add(clave);
            });
        }

        int contruidos = propiedad.getCantConstruidos();
        AtomicBoolean habiaConstrucciones = new AtomicBoolean(false);
        this.registro.casasPorBarrio(propiedad.getColor()).forEach((clave, valor) -> {
            if (clave.getCantConstruidos() == contruidos-1){
                this.puedeVender.get(jugador).remove(clave);
            }
            if (clave.getCantConstruidos() > 0 && clave != propiedad){
                habiaConstrucciones.set(true);
            }
        });

        if (!habiaConstrucciones.get()){
            this.registro.casasPorBarrio(propiedad.getColor()).forEach((clave, valor) -> {
                this.registrarDeshipoteca(clave, jugador);
            });
        }
}


    public void registrarVenta(Propiedad propiedad, Jugador jugador){
        this.puedeVender.get(jugador).remove(propiedad);
        if (this.puedeVender.get(jugador).isEmpty()){
            this.registro.casasPorBarrio(propiedad.getColor()).forEach((clave, valor) -> {
                this.puedeVender.get(jugador).add(clave);
            });
        }

        int contruidos = propiedad.getCantConstruidos();
        AtomicBoolean hayConstrucciones = new AtomicBoolean(false);
        this.registro.casasPorBarrio(propiedad.getColor()).forEach((clave, valor) -> {
            if (clave.getCantConstruidos() == contruidos+1){
                this.construccionesDesbloqueadas.get(jugador).remove(clave);
            }
            if (clave.getCantConstruidos() > 0){
                hayConstrucciones.set(true);
            }
        });

        if (!hayConstrucciones.get()){
            this.registro.casasPorBarrio(propiedad.getColor()).forEach((clave, valor) -> {
                this.registrarHipoteca(clave, jugador);
            });
        }
    }

    public void registrarHipoteca(Comprable comprable, Jugador jugador){
        this.puedeHipotecar.get(jugador).remove(comprable);
        this.deshipotecasDesbloqueadas.get(jugador).add(comprable);
    }
    public void registrarDeshipoteca(Comprable comprable, Jugador jugador){
        this.puedeHipotecar.get(jugador).add(comprable);
        this.deshipotecasDesbloqueadas.get(jugador).remove(comprable);
    }


    public List<Accion> accionesPosibles(Jugador jugador){
        List<Accion> acciones = new ArrayList<Accion>();
        if (!this.opcionesComprar(jugador).isEmpty()){acciones.add(new AccionComprar(this.juego, this.opcionesComprar(jugador),this));}
        if (!this.construccionesDesbloqueadas.get(jugador).isEmpty()){acciones.add(new AccionConstruir(this.juego, this.opcionesConstruir(jugador),this));}
        if (!this.puedeVender.get(jugador).isEmpty()){acciones.add(new AccionVender(this.juego, this.opcionesVenta(jugador), this));}
        if (!this.puedeHipotecar.get(jugador).isEmpty()){acciones.add(new AccionHipotecar(this.juego, this.opcionesHipoteca(jugador), this));}
        if (!this.deshipotecasDesbloqueadas.get(jugador).isEmpty()){acciones.add(new AccionDeshipotecar(this.juego, this.opcionesDeshipoteca(jugador), this));}
        return acciones;
    }

    private List<Casilla> opcionesComprar(Jugador jugador){
        return (jugador.getCasillaActual() instanceof Comprable comprable && !this.registro.tieneDuenio(comprable)) ?
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