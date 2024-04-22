package org.monopoly.controller;

import org.monopoly.controller.accion.*;
import org.monopoly.controller.validador.Validador;
import org.monopoly.controller.validador.ValidadorAccionFinal;
import org.monopoly.controller.validador.ValidadorAccionesCasilla;
import org.monopoly.model.Juego;
import org.monopoly.model.Jugador;
import org.monopoly.model.casilla.Casilla;
import org.monopoly.model.casilla.Comprable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JuegoController {
    private Juego juego;
    private Validador validadorInicio;


    private List<Validador> validadores;
    public JuegoController(Juego juego){
        this.juego = juego;
        this.validadorInicio = new org.monopoly.controller.ValidadorAccionInicio(this.juego);
        this.validadores = new ArrayList<>(){{
            add(new ValidadorAccionesCasilla(juego));
            add(new ValidadorAccionesCasilla(juego));
        }};
    }

    public void jugarTurno(){
        List<Accion> accionesInicio = validadorInicio.accionesPosibles(this.juego.getJugadorActual());

        int accionElegida = this.seleccionarAccion(accionesInicio);

        accionesInicio.get(accionElegida).accionar();

        boolean terminoTurno = false;
        while (!terminoTurno) {
            List<Accion> acciones = this.opcionesAcciones(juego.getJugadorActual());

            accionElegida = this.seleccionarAccion(accionesInicio);

            Accion accion = acciones.get(accionElegida);
            if (accion instanceof AccionCasilla accionCasilla){
                List<Casilla> casillas = accionCasilla.getOpciones();


                int casillaElegida = 2;

                accionCasilla.setCasilla(casillas.get(casillaElegida));
            }
            accion.accionar();

            if (accion instanceof AccionFinal) terminoTurno = true;
        }
    }

    public int seleccionarAccion(List<Accion> listaAccion){
        Scanner scanner = new Scanner(System.in);
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

    public List<Accion> opcionesAcciones (Jugador jugador){
        List<Accion> acciones = new ArrayList<>();
        for (Validador v : this.validadores){acciones.addAll(v.accionesPosibles(jugador));}
        return acciones;
    }
}