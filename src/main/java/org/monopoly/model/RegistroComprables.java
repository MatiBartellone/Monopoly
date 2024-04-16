package org.monopoly.model;
import org.monopoly.model.Config;
import org.monopoly.model.Juego;
import org.monopoly.model.Jugador;
import org.monopoly.model.casilla.Comprable;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
public class RegistroComprable {
    private Map<Comprable, Jugador> tablaPropiedades;
    private Map<Config.ColoresPropiedades, Integer> tablaColores;
    private Map<Jugador, Map<Config.ColoresPropiedades, Integer>> tablaColoresJugadores;

    public RegistroComprable(HashMap<Config.ColoresPropiedades, Integer> tablacolores, List<Jugador> jugadores){
        this.tablaPropiedades = new HashMap<Comprable, Jugador>();
        this.tablaColores = tablacolores;
        this.tablaColoresJugadores = new HashMap<Jugador, Map<Config.ColoresPropiedades, Integer>>();
        for (Jugador jugador : jugadores) {
            Map<Config.ColoresPropiedades, Integer> tablaJugador = new HashMap<Config.ColoresPropiedades, Integer>();
            this.tablaColoresJugadores.put(jugador, tablaJugador);
        }
    }

    private void actualizarcantidad(Map<Config.ColoresPropiedades, Integer> tabla, Config.ColoresPropiedades color){
        if (tabla.containsKey(color)){
            int nueva_cant = tabla.get(color) + 1;
            tabla.put(color, nueva_cant);
        } else {
            tabla.put(color, 1);
        }
    }
    public void registrarCompra(Comprable comprable, Jugador jugador){
        this.tablaPropiedades.put(comprable, jugador);
        Config.ColoresPropiedades color = comprable.getColor();
        Map<Config.ColoresPropiedades, Integer> tablaJugador = this.tablaColoresJugadores.get(jugador);
        actualizarcantidad(tablaJugador, color);
    }

    public boolean tieneDuenio(Comprable comprable){
        return this.tablaPropiedades.containsKey(comprable);
    }

    public Jugador obtenerDuenio(Comprable comprable){
        if (this.tieneDuenio(comprable)) {
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

