package org.monopoly.controller.accion;

import org.monopoly.controller.accion.AccionInicio;


import org.monopoly.model.Juego;

public class AccionPagarFianza extends AccionInicio{
    public AccionPagarFianza(Juego juego){
        super(juego);
    }

    public void accionar(){
        this.juego.pagarFianza();
    }

    public String mostrar(){
        return "Pagar Fianza";
    }
}
