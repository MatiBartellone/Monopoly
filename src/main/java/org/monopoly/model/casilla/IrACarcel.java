package org.monopoly.model.casilla;

import org.monopoly.model.AdmJugador;
import org.monopoly.model.Config;
import org.monopoly.model.Jugador;

public class IrACarcel implements Casilla{

    private Config.TiposCasillas tipo;

    public IrACarcel(Config.TiposCasillas tipo){
        this.tipo = tipo;
    }

    public Config.TiposCasillas getTipo() {
        return this.tipo;
    }

    public void accionar(AdmJugador administrador, Jugador jugador) {
        administrador.encarcelar(jugador);
    }
}
