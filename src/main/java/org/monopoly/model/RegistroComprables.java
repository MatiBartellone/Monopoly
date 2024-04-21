package org.monopoly.model;
import org.monopoly.model.Config;
import org.monopoly.model.Juego;
import org.monopoly.model.Jugador;
import org.monopoly.model.casilla.Comprable;
import org.monopoly.model.casilla.Propiedad;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
public class RegistroComprables {
    private Map<Comprable, Jugador> tablaPropiedades;
    private Map<Config.ColoresComprables, Integer> tablaColores;
    private Map<Jugador, Map<Config.ColoresComprables, Integer>> tablaColoresJugadores;

    public RegistroComprables(Map<Config.ColoresComprables, Integer> tablacolores, List<Jugador> jugadores){
        this.tablaPropiedades = new HashMap<Comprable, Jugador>();
        this.tablaColores = tablacolores;
        this.tablaColoresJugadores = new HashMap<Jugador, Map<Config.ColoresComprables, Integer>>();
        for (Jugador jugador : jugadores) {
            Map<Config.ColoresComprables, Integer> tablaJugador = new HashMap<Config.ColoresComprables, Integer>();
            this.tablaColoresJugadores.put(jugador, tablaJugador);
        }
    }

    private void actualizarcantidad(Map<Config.ColoresComprables, Integer> tabla, Config.ColoresComprables color){
        if (tabla.containsKey(color)){
            int nueva_cant = tabla.get(color) + 1;
            tabla.put(color, nueva_cant);
        } else {
            tabla.put(color, 1);
        }
    }
    public void registrarCompra(Comprable comprable, Jugador jugador){
        this.tablaPropiedades.put(comprable, jugador);
        Config.ColoresComprables color = comprable.getColor();
        Map<Config.ColoresComprables, Integer> tablaJugador = this.tablaColoresJugadores.get(jugador);
        actualizarcantidad(tablaJugador, color);
    }

    public boolean tieneDuenio(Comprable comprable){
        return this.tablaPropiedades.containsKey(comprable);
    }

    public Jugador obtenerDuenio(Comprable comprable){
        return (this.tieneDuenio(comprable)) ? this.tablaPropiedades.get(comprable) : null;
    }

    public int obtenerCantSet(Jugador jugador, Config.ColoresComprables color){
        return this.tablaColoresJugadores.get(jugador).getOrDefault(color, 0);
    }
    public boolean poseeSetCompleto(Jugador jugador, Config.ColoresComprables color){
        return this.obtenerCantSet(jugador, color) == this.tablaColores.get(color);
    }

    public HashMap<Propiedad, Integer> casasPorBarrio(Config.ColoresComprables color){
        
    }
}