package org.monopoly.controller.accion;

import org.monopoly.model.Juego;

public abstract class AccionFinal implements Accion {
    protected Juego juego;

    public AccionFinal(Juego juego){
        this.juego = juego;
    }
    public Etapa getEtapa() {return Etapa.FIN;}

    public abstract void accionar();

    public abstract String getNombre();
}
