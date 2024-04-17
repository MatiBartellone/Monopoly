package org.monopoly.model.casilla;
import org.monopoly.model.Config;
public abstract class Casilla {
    private Config.TiposCasillas tipo;

    public Casilla(Config.TiposCasillas tipo){
        this.tipo = tipo;
    }
    public Config.TiposCasillas getTipo(){
        return this.tipo;
    }
}
