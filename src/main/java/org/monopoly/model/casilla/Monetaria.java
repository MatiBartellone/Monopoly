package org.monopoly.model.casilla;

import org.monopoly.model.AdmJugador;
import org.monopoly.model.Config;

public abstract class Monetaria extends Casilla implements Accionable{
    private int monto;

    public Monetaria(Config.TiposCasillas tipo, int monto){
        super(tipo);
        this.monto = monto;
    }

    public int getMonto(){
        return this.monto;
    }
}
