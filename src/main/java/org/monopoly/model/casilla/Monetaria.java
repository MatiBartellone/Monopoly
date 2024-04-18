package org.monopoly.model.casilla;

import org.monopoly.model.AdmJugador;
import org.monopoly.model.Config;

public abstract class Monetaria implements Casilla{
    private Config.TiposCasillas tipo;
    private int monto;

    public Monetaria(Config.TiposCasillas tipo, int monto){
        this.tipo = tipo;
        this.monto = monto;
    }

    public Config.TiposCasillas getTipo() {
        return this.tipo;
    }

    public int getMonto(){
        return this.monto;
    }
}
