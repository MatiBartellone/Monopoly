package org.monopoly.model.casilla;

import org.monopoly.model.AdmJugador;
import org.monopoly.model.Config;

public class Multa extends Monetaria {
    public Multa(Config.TiposCasillas tipo, int monto){
        super(tipo, monto);
    }

    public void accionar(AdmJugador administrador) {
        administrador.quitarDinero(administrador.getJugadorActual(), this.getMonto());
    }

}
