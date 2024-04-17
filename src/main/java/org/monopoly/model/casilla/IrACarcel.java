package org.monopoly.model.casilla;

import org.monopoly.model.AdmJugador;
import org.monopoly.model.Config;
import org.monopoly.model.Jugador;

public class IrACarcel extends Casilla implements Accionable{

    public IrACarcel(Config.TiposCasillas tipo){
        super(tipo);
    }

    public void accionar(AdmJugador administrador, Jugador jugador) {
        administrador.encarcelar(jugador);
    }
}
