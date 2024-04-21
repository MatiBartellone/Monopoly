package org.monopoly.controller.accion;

import org.monopoly.model.Juego;

public class AccionPasarDeTurno extends AccionFinal{

    public AccionPasarDeTurno(Juego juego) {
        super(juego);
    }

    public void accionar(){
        juego.siguienteTurno();
    }

    public String mostrar(){
        return "Pasar De Turno";
    }
}
