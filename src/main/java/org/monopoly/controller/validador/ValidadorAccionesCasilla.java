package org.monopoly.controller.validador;

import org.monopoly.controller.accion.*;
import org.monopoly.model.Config;
import org.monopoly.model.Juego;
import org.monopoly.model.Jugador;
import org.monopoly.model.RegistroComprables;
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
    private Map<Jugador, List<Propiedad>> construccionesDesbloqueadas;
    private Map<Jugador, List<Propiedad>> puedeVender;
    private Map<Jugador, List<Comprable>> puedeHipotecar;
    private Map<Jugador, List<Comprable>> deshipotecasDebloqueadas;

    public ValidadorAccionesCasilla(Juego juego) {
        this.juego = juego;
        this.registro = juego.getRegistroComprables();
        this.construccionesDesbloqueadas = new HashMap<>();
        this.puedeVender = new HashMap<>();
        this.puedeHipotecar = new HashMap<>();
        this.deshipotecasDebloqueadas = new HashMap<>();

        // Inicializar las listas vacías para cada jugador
        for (Jugador jugador : juego.getJugadores()) {
            construccionesDesbloqueadas.put(jugador, new ArrayList<>());
            puedeVender.put(jugador, new ArrayList<>());
            puedeHipotecar.put(jugador, new ArrayList<>());
            deshipotecasDebloqueadas.put(jugador, new ArrayList<>());
        }
    }
    public void registrarCompraPropiedad(Propiedad propiedad, Jugador jugador){
        if (this.registro.poseeSetCompleto(jugador, propiedad.getColor())){
            this.registro.casasPorBarrio(propiedad.getColor()).forEach((clave, valor) -> {
                this.construccionesDesbloqueadas.get(jugador).add(clave);
            });
        }
        this.puedeHipotecar.get(jugador).add(propiedad);
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

    public void registrarHipoteca(Propiedad propiedad, Jugador jugador){
        this.puedeHipotecar.get(jugador).remove(propiedad);
        this.deshipotecasDebloqueadas.get(jugador).add(propiedad);
    }
    public void registrarDeshipoteca(Propiedad propiedad, Jugador jugador){
        this.puedeHipotecar.get(jugador).add(propiedad);
        this.deshipotecasDebloqueadas.get(jugador).remove(propiedad);
    }


    public List<Accion> accionesPosibles(Jugador jugador){
        List<Accion> acciones = new ArrayList<Accion>();
        if (!this.opcionesComprar(jugador).isEmpty()){acciones.add(new AccionComprar(this.juego));}
        if (!this.construccionesDesbloqueadas.get(jugador).isEmpty()){acciones.add(new AccionConstruir(this.juego));}
        if (!this.puedeVender.get(jugador).isEmpty()){acciones.add(new AccionVender(this.juego));}
        if (!this.puedeHipotecar.get(jugador).isEmpty()){acciones.add(new AccionHipotecar(this.juego));}
        if (!this.deshipotecasDebloqueadas.get(jugador).isEmpty()){acciones.add(new AccionDeshipotecar(this.juego));}
        return acciones;
    }

    public List<Comprable> opcionesComprar(Jugador jugador){
        List<Comprable> comprables = new ArrayList<>();
        if (jugador.getCasillaActual() instanceof Comprable){
            Comprable casilla = (Comprable) jugador.getCasillaActual();
            if (this.registro.tieneDuenio(casilla)){ comprables.add(casilla); }
        }
        return comprables;
    }

    public List<Propiedad> opcionesConstruir(Jugador jugador){
        List<Propiedad> opcionesFiltradas = new ArrayList<>();
        for(Propiedad propiedad: this.construccionesDesbloqueadas.get(jugador)){
            if (propiedad.getValorCompra()<jugador.)
        }
        return ;
    }

    public List<Propiedad> opcionesVenta(Jugador jugador){
        return this.puedeVender.get(jugador);
    }

    public List<Comprable> opcionesHipoteca(Jugador jugador){
        return this.puedeHipotecar.get(jugador);
    }

    public List<Comprable> opcionesDeshipoteca(Jugador jugador){
        return this.deshipotecasDebloqueadas.get(jugador);
    }

    private List<> filtroDinero(List<Comprable> opciones, int plata){
        List<Comprable> opcionesFiltradas = new ArrayList<>();
        for(Comprable comprable: opciones){
            if (this.)
        }

    }

}