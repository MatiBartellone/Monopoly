package org.monopoly.model;

import org.monopoly.model.casilla.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Config {
    public static enum ColoresJugadores{MORTY, SUMMER, JERRY, BETH, RICK, CARRETILLA, SOMBRERO, BARCO, SRMONOPOLY, AUTO, HOMERO, AYUDANTE, BART,BOLADENIEVE, LISA}
    public static enum EstadosJugadores{EN_JUEGO, PRESO, QUEBRADO, CRISIS}
    public static enum TiposCasillas{PROPIEDAD, ESTACION, IR_A_CARCEL, MULTA, LOTERIA, CARCEL, SALIDA, PASO}
    public static enum ColoresComprables{black, brown, lightblue, pink, orange, red, yellow, green, blue}
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
    public static List<Propiedad> ListaPropiedades = new ArrayList<Propiedad>(){{
        add(new Propiedad(TiposCasillas.PROPIEDAD, "Mediterraneo", 60, 4, ColoresComprables.brown,  construcciones, 50));


        add(new Propiedad(TiposCasillas.PROPIEDAD, "Connecticut", 120, 16, ColoresComprables.lightblue, construcciones, 50));
        add(new Propiedad(TiposCasillas.PROPIEDAD, "St.Charles", 140, 20, ColoresComprables.pink, construcciones, 100));


        add(new Propiedad(TiposCasillas.PROPIEDAD, "New York", 200, 36, ColoresComprables.orange, construcciones, 150));
        add(new Propiedad(TiposCasillas.PROPIEDAD, "Kentucky", 220, 38, ColoresComprables.red, construcciones, 150));


        add(new Propiedad(TiposCasillas.PROPIEDAD, "Marvin", 280, 52, ColoresComprables.yellow, construcciones, 150));

        add(new Propiedad(TiposCasillas.PROPIEDAD, "Pennsylvania", 320, 60, ColoresComprables.green, construcciones, 200));
        add(new Propiedad(TiposCasillas.PROPIEDAD, "Park Place", 350, 65, ColoresComprables.blue, construcciones, 200));

    }};

    public static List<Estacion> ListaEstaciones= new ArrayList<Estacion>(){{
        add(new Estacion(TiposCasillas.ESTACION, "Reading", 200, 25, ColoresComprables.black));
        add(new Estacion(TiposCasillas.ESTACION, "Pennsylvania", 200, 25, ColoresComprables.black));
        add(new Estacion(TiposCasillas.ESTACION, "B & O", 200, 25, ColoresComprables.black));
        add(new Estacion(TiposCasillas.ESTACION, "Sort Line", 200, 25, ColoresComprables.black));
    }};

    public static void agregarPropiedad(List<Casilla> lista, AtomicInteger indice, int cantidad){
        for(int i = indice.get(); i < cantidad + indice.get(); i++) {
            lista.add(ListaPropiedades.get(i));
        }
        indice.addAndGet(cantidad);
    }
    public static List<Casilla> ListaCasillas(){
         List<Casilla> listaCasillas = new ArrayList<Casilla>();
         listaCasillas.add(new CasillaSinEfecto(TiposCasillas.SALIDA));
         AtomicInteger indice = new AtomicInteger(0);
        listaCasillas.add(new Multa(TiposCasillas.MULTA, 1000));
        listaCasillas.add(new Multa(TiposCasillas.MULTA, 1000));
        listaCasillas.add(new Multa(TiposCasillas.MULTA, 1000));
         agregarPropiedad(listaCasillas, indice, 3);
        listaCasillas.add(new Multa(TiposCasillas.MULTA, 1000));
        listaCasillas.add(ListaEstaciones.get(0));
         listaCasillas.add(carcel);

        agregarPropiedad(listaCasillas, indice, 3);
        listaCasillas.add(new Multa(TiposCasillas.MULTA, 1000));
        agregarPropiedad(listaCasillas, indice, 2);
        listaCasillas.add(new Multa(TiposCasillas.MULTA, 1000));
        listaCasillas.add(new Multa(TiposCasillas.MULTA, 1000));


         return listaCasillas;
    }


    public static Map<Config.ColoresComprables, List<Comprable>> barrios(){
        Map<Config.ColoresComprables, List<Comprable>> TablaColores = new HashMap<>();

        for (Config.ColoresComprables color : Config.ColoresComprables.values()){
            TablaColores.put(color, new ArrayList<>());
        }
        for (Comprable propiedad: Config.ListaPropiedades) {
            TablaColores.get(propiedad.getColor()).add(propiedad);
        }
        for (Comprable estacion: Config.ListaEstaciones) {
            TablaColores.get(estacion.getColor()).add(estacion);
        }
        return TablaColores;
    }
}
