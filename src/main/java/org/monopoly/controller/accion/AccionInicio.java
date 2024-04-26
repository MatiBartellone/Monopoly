package org.monopoly.controller.accion;

import org.monopoly.model.Juego;

abstract class AccionInicio implements Accion {
    protected Juego juego;

    public AccionInicio(Juego juego){
        this.juego = juego;
    }

    public Etapa getEtapa() {return Etapa.INICIO;}

    public abstract void accionar();

    public abstract String getNombre();
}
