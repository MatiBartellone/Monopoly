package org.monopoly.model;
import org.monopoly.model.Config;
import org.monopoly.model.Juego;
import org.monopoly.model.Jugador;
import org.monopoly.model.casilla.Comprable;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
public class RegistroComprable {
    private HashMap<Comprable, Jugador> tablaPropiedades;
    private HashMap<Config.ColoresPropiedades, Integer> tablaColores;
    private HashMap<Jugador, HashMap<Config.ColoresPropiedades, Integer>> tablaColoresJugadores;

    public RegistroComprable(HashMap<Config.ColoresPropiedades, Integer> tablacolores, List<Jugador> jugadores){
        this.tablaPropiedades = new HashMap<Comprable, Jugador>();
        this.tablaColores = tablacolores;
        this.tablaColoresJugadores = new HashMap<Jugador, HashMap<Config.ColoresPropiedades, Integer>>();
        for (Jugador jugador : jugadores) {
            HashMap<Config.ColoresPropiedades, Integer> tablaJugador;
            tablaJugador = new HashMap<Config.ColoresPropiedades, Integer>();
            this.tablaColoresJugadores.put(jugador, tablaJugador);
        }
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
        HashMap<Config.ColoresPropiedades, Integer> tablaJugador; // Declaración
        tablaJugador = this.tablaColoresJugadores.get(jugador);
        actualizarcantidad(tablaJugador, color);
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
        return (this.tablaColoresJugadores.get(jugador).containsKey(color))
                ? this.tablaColoresJugadores.get(jugador).get(color)
                : 0;
    }
    public boolean poseeSetCompleto(Jugador jugador, Config.ColoresPropiedades color){
        return this.obtenerCantSet(jugador, color) == this.tablaColores.get(color);
    }
}

