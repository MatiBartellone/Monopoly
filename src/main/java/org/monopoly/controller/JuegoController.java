package org.monopoly.controller;

import org.monopoly.controller.accion.*;
import org.monopoly.controller.validador.Validador;
import org.monopoly.controller.validador.ValidadorAccionFinal;
import org.monopoly.controller.validador.ValidadorAccionInicio;
import org.monopoly.controller.validador.ValidadorAccionesCasilla;
import org.monopoly.model.Config;
import org.monopoly.model.Juego;
import org.monopoly.model.Jugador;
import org.monopoly.model.casilla.Casilla;
import org.monopoly.model.casilla.Comprable;
import org.monopoly.model.casilla.Monetaria;
import org.monopoly.model.casilla.Propiedad;

import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JuegoController {
    private Juego juego;
    private Validador validadorInicio;


    private List<Validador> validadores;
    public JuegoController(Juego juego){
        this.juego = juego;
        this.validadorInicio = new ValidadorAccionInicio(this.juego);
        this.validadores = new ArrayList<>(){{
            add(new ValidadorAccionesCasilla(juego));
            add(new ValidadorAccionFinal(juego));
        }};
    }

    public void jugarTurno(){
        mostrarInfoJugadores(this.juego, this.juego.getJugadores(), Config.ListaCasillas);
        mostrarInfoTablero(this.juego, this.juego.getJugadores(), Config.ListaCasillas);
        List<Accion> accionesInicio = validadorInicio.accionesPosibles(this.juego.getJugadorActual());

        int accionElegida = this.seleccionarAccion(accionesInicio);

        accionesInicio.get(accionElegida).accionar();

        boolean terminoTurno = false;
        while (!terminoTurno) {
            mostrarInfoJugadores(this.juego, this.juego.getJugadores(), Config.ListaCasillas);
            mostrarInfoTablero(this.juego, this.juego.getJugadores(), Config.ListaCasillas);


            List<Accion> acciones = this.opcionesAcciones(juego.getJugadorActual());

            accionElegida = this.seleccionarAccion(opcionesAcciones(this.juego.getJugadorActual()));

            Accion accion = acciones.get(accionElegida);
            if (accion instanceof AccionCasilla accionCasilla){
                List<Casilla> casillas = accionCasilla.getOpciones();


                int casillaElegida = seleccionarCasilla(casillas);

                accionCasilla.setCasilla(casillas.get(casillaElegida));
            }
            accion.accionar();

            if (accion instanceof AccionFinal) terminoTurno = true;
        }
    }

    public List<Accion> opcionesAcciones (Jugador jugador){
        List<Accion> acciones = new ArrayList<>();
        for (Validador v : this.validadores){acciones.addAll(v.accionesPosibles(jugador));}
        return acciones;
    }

    public int seleccionarAccion(List<Accion> listaAccion){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Jugador actual:" + this.juego.getJugadorActual().getColor());
        System.out.println("Seleccione Accion: ");
        for (int i = 0; i < listaAccion.size() ; i++){
            System.out.println(i + ": " + listaAccion.get(i).mostrar());
        }
        return Integer.parseInt(scanner.nextLine());
    }
    public int seleccionarCasilla(List<Casilla> listaCasilla){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione Casilla: ");
        for (int i = 0; i < listaCasilla.size() ; i++){
            Comprable comparable = (Comprable)  listaCasilla.get(i);
            System.out.println(i + ": " + comparable.getNombre());
        }
        return Integer.parseInt(scanner.nextLine());
    }

    public static void mostrarInfoJugadores(Juego juego, List<Jugador> jugadores, List<Casilla> casillas){

        File original = new File("src/main/java/org/monopoly/infoJugadores.txt");
        File temp = new File("src/main/java/org/monopoly/TempinfoJugadores.txt");
        try {
            FileWriter myWritter = new FileWriter(temp);
            myWritter.write("Jugador actual: " + juego.getAdmTurnos().getJugadorActual().getColor() + "\n");
            for (Jugador j : jugadores) {
                myWritter.write("\n\nJugador: " + j.getColor());
                myWritter.write("\n\tEstado: " + j.getEstado());
                myWritter.write("\n\tDinero: " + juego.getAdmJugador().getBanco().getCuentaJugador(j).getDinero());
                myWritter.write("\n\tPosicion actual: " + j.getCasillaActual().getTipo() + "\tnum: " + casillas.indexOf(j.getCasillaActual()));
                if (j.getEstado() == Config.EstadosJugadores.PRESO) myWritter.write("\n\tTurnos carcel: " + j.getTurnosCarcel());
                myWritter.write("\n\tPropiedades: ");
                for (Casilla c : casillas) {
                    if (c instanceof Comprable comprable)
                        if (juego.getAdmJugador().getRegistroComprables().obtenerDuenio(comprable) == j) {
                            myWritter.write("\n\t" + comprable.getNombre() + "\t color:" + comprable.getColor());
                            if (comprable.estaHipotecada()) myWritter.write("\tHIPOTECADA");
                        }
                }

            }

            myWritter.close();
            original.delete();
            temp.renameTo(original);
        } catch (IOException e){
            System.out.println("ocurrio un error");
            e.printStackTrace();
        }
    }

    public static void mostrarInfoTablero(Juego juego, List<Jugador> jugadores, List<Casilla> casillas){
        try {
            FileWriter myWritter = new FileWriter("src/main/java/org/monopoly/infoTablero.txt");

            for (Casilla c : casillas) {
                myWritter.write( "\n" + casillas.indexOf(c) + ":\t");
                myWritter.write(  "Tipo: " + c.getTipo());
                if (c instanceof Comprable comp) {
                    myWritter.write("\t" + comp.getNombre());
                    myWritter.write("\t" + comp.getColor());
                    myWritter.write("\tValor: " + comp.getValorCompra());
                }
                if (c instanceof Propiedad p)
                    myWritter.write("\tConstrucciones: " + p.getCantConstruidos());
                if (c instanceof Monetaria m)
                    myWritter.write("\tmonto: " + m.getMonto());
            }


            myWritter.close();
        } catch (IOException e){
            System.out.println("ocurrio un error");
            e.printStackTrace();
        }
    }
}