package org.monopoly.model.casilla;

import org.monopoly.model.Config;

public abstract class Monetaria extends Casilla implements Accionable{
    private int monto;

    public Monetaria(Config.TiposCasillas tipoDado, int monto){
        super(tipoDado);
        this.monto = monto;
    }

    public int getMonto(){
        return this.monto;
    }
}
