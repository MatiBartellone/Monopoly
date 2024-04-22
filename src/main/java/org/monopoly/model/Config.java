package org.monopoly.model;

import org.monopoly.model.casilla.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config {
    public static enum ColoresJugadores{AUTO, SOMBRERO, PLANCHA, BARCO}
    public static enum EstadosJugadores{EN_JUEGO, PRESO, QUEBRADO, CRISIS}
    public static enum TiposCasillas{PROPIEDAD, ESTACION, IR_A_CARCEL, MULTA, LOTERIA, CARCEL, SALIDA, PASO}
    public static enum ColoresComprables{FERROCARRIL, MARRON, CELESTE, ROSA, NARANJA, ROJO, AMARILLO, VERDE, AZUL}
    public static enum TiposConstrucciones{CASA, HOTEL}
    public static int DineroInicial = 1500;
    public static int TurnosCarcel = 3;
    public static int MaxJugadores = 4;
    public static int ValorFianza = 50;
    public static int MaxCasas = 4;
    public static int MaxHoteles = 1;
    public static int MaxConstrucciones = MaxCasas + MaxHoteles;
    public static int PagoPorSalida = 200;

    public static List<Construccion> construcciones = new ArrayList<Construccion>(){{
        add(new Construccion(TiposConstrucciones.CASA, 50));
        add(new Construccion(TiposConstrucciones.CASA, 50));
        add(new Construccion(TiposConstrucciones.CASA, 50));
        add(new Construccion(TiposConstrucciones.CASA, 50));
        add(new Construccion(TiposConstrucciones.HOTEL, 50));
    }};
    public static CasillaSinEfecto carcel = new CasillaSinEfecto(TiposCasillas.CARCEL);
    public static List<Casilla> ListaCasillas = new ArrayList<Casilla>(){{
        add(new CasillaSinEfecto(TiposCasillas.SALIDA));

        add(new Propiedad(TiposCasillas.PROPIEDAD, "Mediterraneo", 60, 4, ColoresComprables.MARRON,  construcciones, 50));
        add(new Propiedad(TiposCasillas.PROPIEDAD, "Baltco", 60, 6, ColoresComprables.MARRON,  construcciones, 50));
        add(new Multa(TiposCasillas.MULTA, 200));
        add(new Estacion(TiposCasillas.ESTACION, "Reading", 200, 25, ColoresComprables.FERROCARRIL));
        add(new Propiedad(TiposCasillas.PROPIEDAD, "Central", 100, 10, ColoresComprables.CELESTE,  construcciones, 50));
        add(new Propiedad(TiposCasillas.PROPIEDAD, "Vermont", 100, 12, ColoresComprables.CELESTE, construcciones, 50));
        add(new Loteria(TiposCasillas.LOTERIA, 200));
        add(new Propiedad(TiposCasillas.PROPIEDAD, "Connecticut", 120, 16, ColoresComprables.CELESTE, construcciones, 50));

        add(carcel);

        add(new Propiedad(TiposCasillas.PROPIEDAD, "St.Charles", 140, 20, ColoresComprables.ROSA, construcciones, 100));
        add(new Propiedad(TiposCasillas.PROPIEDAD, "States", 140, 22, ColoresComprables.ROSA, construcciones, 150));
        add(new Propiedad(TiposCasillas.PROPIEDAD, "Virginia", 160, 28, ColoresComprables.ROSA, construcciones, 150));
        add(new Estacion(TiposCasillas.ESTACION, "Pennsylvania", 200, 25, ColoresComprables.FERROCARRIL));
        add(new Propiedad(TiposCasillas.PROPIEDAD, "St.James", 180, 30, ColoresComprables.NARANJA, construcciones, 150));
        add(new Multa(TiposCasillas.MULTA, 200));
        add(new Propiedad(TiposCasillas.PROPIEDAD, "Tennesse", 180, 32, ColoresComprables.NARANJA, construcciones, 150));
        add(new Propiedad(TiposCasillas.PROPIEDAD, "New York", 200, 36, ColoresComprables.NARANJA, construcciones, 150));

        add(new CasillaSinEfecto(TiposCasillas.PASO));

        add(new Propiedad(TiposCasillas.PROPIEDAD, "Kentucky", 220, 38, ColoresComprables.ROJO, construcciones, 150));
        add(new Multa(TiposCasillas.MULTA, 200));
        add(new Propiedad(TiposCasillas.PROPIEDAD, "Indiana", 220, 40, ColoresComprables.ROJO, construcciones, 150));
        add(new Propiedad(TiposCasillas.PROPIEDAD, "Illinois", 240, 44, ColoresComprables.ROJO, construcciones, 150));
        add(new Estacion(TiposCasillas.ESTACION, "B & O", 200, 25, ColoresComprables.FERROCARRIL));
        add(new Propiedad(TiposCasillas.PROPIEDAD, "Atlantic", 260, 46, ColoresComprables.AMARILLO, construcciones, 150));
        add(new Propiedad(TiposCasillas.PROPIEDAD, "Ventor", 260, 48, ColoresComprables.AMARILLO, construcciones, 150));
        add(new Propiedad(TiposCasillas.PROPIEDAD, "Marvin", 280, 52, ColoresComprables.AMARILLO, construcciones, 150));

        add(new IrACarcel(TiposCasillas.IR_A_CARCEL, carcel));

        add(new Propiedad(TiposCasillas.PROPIEDAD, "Pacific", 300, 54, ColoresComprables.VERDE, construcciones, 200));
        add(new Propiedad(TiposCasillas.PROPIEDAD, "North Carolina", 300, 56, ColoresComprables.VERDE, construcciones, 200));
        add(new Loteria(TiposCasillas.LOTERIA, 200));
        add(new Propiedad(TiposCasillas.PROPIEDAD, "Pennsylvania", 320, 60, ColoresComprables.VERDE, construcciones, 200));
        add(new Estacion(TiposCasillas.ESTACION, "Sort Line", 200, 25, ColoresComprables.FERROCARRIL));
        add(new Propiedad(TiposCasillas.PROPIEDAD, "Park Place", 350, 65, ColoresComprables.AZUL, construcciones, 200));
        add(new Multa(TiposCasillas.MULTA, 200));
        add(new Propiedad(TiposCasillas.PROPIEDAD, "Boardwalk", 400, 70, ColoresComprables.AZUL, construcciones, 200));


    }};


    public static Map<Config.ColoresComprables, List<Comprable>> barrios(){
        Map<Config.ColoresComprables, List<Comprable>> TablaColores = new HashMap<>();

        for (Config.ColoresComprables color : Config.ColoresComprables.values()){
            TablaColores.put(color, new ArrayList<>());
        }
        for (Casilla casilla: Config.ListaCasillas) {
            if (casilla instanceof Comprable comprable){
                TablaColores.get(comprable.getColor()).add(comprable);
            }
        }
        return TablaColores;
    }


}