package org.monopoly.model;
import org.monopoly.model.Config;
import org.monopoly.model.casilla.Casilla;

import java.util.List;

public class Tablero {
    private List<Casilla> listaCasillas;

    public Tablero(){
        this.listaCasillas = Config.ListaCasillas;
    }

    public List<Casilla> getListaCasillas(){
        return this.listaCasillas;
    }

    public Casilla casillaSiguiente(Casilla casillaActual, int avances){
        int index = this.listaCasillas.indexOf(casillaActual) + avances;
        if (index>=this.listaCasillas.size()){
            index = index - this.listaCasillas.size();
        }
        return this.listaCasillas.get(index);
    }

    public boolean pasoPorSalida(Casilla casillaActual, int avances){
        return this.listaCasillas.indexOf(casillaActual) + avances >= this.listaCasillas.size();
    }
}
