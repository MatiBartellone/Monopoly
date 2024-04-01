package org.monopoly.model;

import org.monopoly.model.casilla.Casilla;

import java.util.ArrayList;
import java.util.List;

public class Config {
    public static enum ColoresJugadores{AUTO, SOMBRERO, PLANCHA, BARCO}
    public static enum EstadosJugadores{EN_JUEGO, PRESO, QUEBRADO}
    public static enum TiposCasillas{PROPIEDAD, ESTACION, IR_A_CARCEL, MULTA, LOTERIA, CARCEL, SALIDA, PASO}
    public static enum ColoresPropiedades{FERROCARRIL, MARRON, CELESTE, ROSA, NARANJA, ROJO, AMARILLO, VERDE, AZUL}
    public static int DineroInicial = 1500;
    public static List<Casilla> ListaCasillas = new ArrayList<Casilla>(){{
        add(new Casilla());
        add(new Casilla());
        add(new Casilla());
        add(new Casilla());
    }};
}
