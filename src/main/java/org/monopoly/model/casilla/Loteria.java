package org.monopoly.model.casilla;

import org.monopoly.model.AdmJugador;
import org.monopoly.model.Config;

public class Loteria extends Monetaria{
    public Loteria(Config.TiposCasillas tipo, int monto){
        super(tipo, monto);
    }

    @Override
    public void accionar(AdmJugador administrador) {
        administrador.otorgarDinero(administrador.getJugadorActual(), this.getMonto());
    }
}
