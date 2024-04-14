package org.monopoly.model;
import org.monopoly.model.Config;
import org.monopoly.model.Juego;
import org.monopoly.model.Jugador;
import org.monopoly.model.casilla.Comprable;

import java.util.HashMap;
import java.util.Map;
public class RegistroComprable {
    private HashMap<Comprable, Jugador> tablaPropiedades;
    private HashMap<Config.ColoresPropiedades, Integer> tablaColores;
    private HashMap<Jugador, HashMap<Config.ColoresPropiedades, Integer>> tablaColoresJugadores;

    public RegistroComprable(Config config){
        this.tablaPropiedades = new HashMap<Comprable, Jugador>();
        this.tablaColores = new HashMap<Config.ColoresPropiedades, Integer>();
        this.tablaColoresJugadores = new HashMap<Jugador, HashMap<Config.ColoresPropiedades, Integer>>();
    }

    private void actualizarcantidad(HashMap<Config.ColoresPropiedades, Integer> tabla, Config.ColoresPropiedades color){
        if (tabla.containsKey(color)){
            int nueva_cant;
            nueva_cant = tabla.get(color) + 1;
            tabla.put(color, nueva_cant);
        } else {
            tabla.put(color, 1);
        }
    }
    public void registrarCompra(Comprable comprable, Jugador jugador){
        this.tablaPropiedades.put(comprable, jugador);
        Config.ColoresPropiedades color = comprable.getColor();
        actualizarcantidad(this.tablaColores, color);
        if (this.tablaColoresJugadores.containsKey(jugador)){
            HashMap<Config.ColoresPropiedades, Integer> tablaJugador; // Declaración
            tablaJugador = this.tablaColoresJugadores.get(jugador);
            actualizarcantidad(tablaJugador, color);
        } else {
            HashMap<Config.ColoresPropiedades, Integer> tablaParaJugador;
            tablaParaJugador = new HashMap<Config.ColoresPropiedades, Integer>();
            tablaParaJugador.put(color, 1);
            this.tablaColoresJugadores.put(jugador, tablaParaJugador);
        }
    }

    public boolean tieneDueño(Comprable comprable){
        return this.tablaPropiedades.containsKey(comprable);
    }

    public Jugador obtenerDueño(Comprable comprable){
        if (this.tieneDueño(comprable)) {
            return  this.tablaPropiedades.get(comprable);
        }
        return null;
    }

    public int obtenerCantSet(Jugador jugador, Config.ColoresPropiedades color){
        HashMap<Config.ColoresPropiedades, Integer> tablaColorJugador;
        tablaColorJugador = this.tablaColoresJugadores.get(jugador);
        if (tablaColorJugador.containsKey(color)){
            return tablaColorJugador.get(color);
        }
        return 0;
    }
    public boolean poseeSetCompleto(Jugador jugador, Config.ColoresPropiedades color, int cantidad_set){
        if (this.obtenerCantSet(jugador, color) == cantidad_set){
            return true;
        }
        return false;
    }
}

