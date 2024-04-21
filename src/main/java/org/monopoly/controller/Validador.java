package org.monopoly.controller;

import org.monopoly.model.Config;
import org.monopoly.model.Jugador;
import org.monopoly.model.RegistroComprables;
import org.monopoly.model.casilla.Comprable;
import org.monopoly.model.casilla.Propiedad;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class Validador {

    private RegistroComprables registro;
    private Map<Jugador, List<Propiedad>> construccionesDesbloqueadas;
    private Map<Jugador, List<Propiedad>> puedeVender;
    private Map<Jugador, List<Comprable>> puedeHipotecar;
    private Map<Jugador, List<Comprable>> deshipotecasDebloqueadas;

    public Validador(RegistroComprables registro, List<Jugador> jugadores) {
        this.registro = registro;
        this.construccionesDesbloqueadas = new HashMap<>();
        this.puedeVender = new HashMap<>();
        this.puedeHipotecar = new HashMap<>();
        this.deshipotecasDebloqueadas = new HashMap<>();

        // Inicializar las listas vacías para cada jugador
        for (Jugador jugador : jugadores) {
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

    private List<Comprable> puedeComprar(Jugador jugador){
        List<Comprable> comprables = new ArrayList<>();
        if (jugador.getCasillaActual() instanceof Comprable){
            Comprable casilla = (Comprable) jugador.getCasillaActual();
            if (this.registro.tieneDuenio(casilla)){
                comprables.add(casilla);
            }
        }
        return comprables;
    }
    public List<List> accionesPosibles(Jugador jugador){
        List<List> acciones = new ArrayList<>();
        acciones.add(this.puedeComprar(jugador));
        acciones.add(this.construccionesDesbloqueadas.get(jugador));
        acciones.add(this.puedeVender.get(jugador));
        acciones.add(this.puedeHipotecar.get(jugador));
        acciones.add(this.deshipotecasDebloqueadas.get(jugador));
        return acciones;
    }
}
