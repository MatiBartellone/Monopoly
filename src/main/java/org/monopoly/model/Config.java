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
    public static enum ColoresComprables{FERROCARRIL, MARRON, CELESTE, ROSA, NARANJA, ROJO, AMARILLO, VERDE, AZUL}
    public static enum TiposConstrucciones{CASA, HOTEL}
    public static int DineroInicial = 1500;
    public static int TurnosCarcel = 3;
    public static int MaxJugadores = 4;
    public static int ValorFianza = 50;
    public static int MaxCasas = 4;
    public static int MaxHoteles = 1;
    public static int PagoPorSalida = 200;

    public static List<Casilla> ListaCasillas = new ArrayList<Casilla>(){{
        add(new Casilla(TiposCasillas.SALIDA));
        add(new Casilla(TiposCasillas.CARCEL));
        add(new Casilla(TiposCasillas.PASO));
    }};
    public static Map<Config.ColoresComprables, Integer> TablaColores = new HashMap<Config.ColoresComprables, Integer>(){{
        put(ColoresComprables.FERROCARRIL, 4);
        put(ColoresComprables.MARRON, 2);
        put(ColoresComprables.CELESTE, 3);
        put(ColoresComprables.ROSA, 3);
        put(ColoresComprables.NARANJA, 3);
        put(ColoresComprables.ROJO, 3);
        put(ColoresComprables.AMARILLO, 3);
        put(ColoresComprables.VERDE, 3);
        put(ColoresComprables.AZUL, 2);
    }};
}

