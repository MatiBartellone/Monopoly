package org.monopoly.model;

import org.monopoly.model.casilla.Casilla;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config {
    public static enum ColoresJugadores{AUTO, SOMBRERO, PLANCHA, BARCO}
    public static enum EstadosJugadores{EN_JUEGO, PRESO, QUEBRADO}
    public static enum TiposCasillas{PROPIEDAD, ESTACION, IR_A_CARCEL, MULTA, LOTERIA, CARCEL, SALIDA, PASO}
    public static enum ColoresPropiedades{FERROCARRIL, MARRON, CELESTE, ROSA, NARANJA, ROJO, AMARILLO, VERDE, AZUL}
    public static int DineroInicial = 1500;
    public static int TurnosCarcel = 3;
    public static int MaxJugadores = 4;
    public static int ValorFianza = 50;
    public static int MaxCasas = 4;
    public static int MaxHoteles = 1;

    public static List<Casilla> ListaCasillas = new ArrayList<Casilla>(){{
        add(new Casilla());
        add(new Casilla());
        add(new Casilla());
        add(new Casilla());
    }};
    public static Map<Config.ColoresPropiedades, Integer> TablaColores = new HashMap<Config.ColoresPropiedades, Integer>(){{
        put(ColoresPropiedades.FERROCARRIL, 4);
        put(ColoresPropiedades.MARRON, 2);
        put(ColoresPropiedades.CELESTE, 3);
        put(ColoresPropiedades.ROSA, 3);
        put(ColoresPropiedades.NARANJA, 3);
        put(ColoresPropiedades.ROJO, 3);
        put(ColoresPropiedades.AMARILLO, 3);
        put(ColoresPropiedades.VERDE, 3);
        put(ColoresPropiedades.AZUL, 2);
    }};
}

