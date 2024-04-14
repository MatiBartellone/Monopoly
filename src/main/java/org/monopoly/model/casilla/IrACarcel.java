package org.monopoly.model.casilla;

import org.monopoly.model.AdmJugador;
import org.monopoly.model.Config;

public class IrACarcel extends Casilla implements Accionable{

    public IrACarcel(Config.TiposCasillas tipoDado){
        super(tipoDado);
    }

    @Override
    public void accionar(AdmJugador administrador) {
        administrador.encarcelar(administrador.getJugadorActual());
    }
}
