package org.monopoly.model.casilla;

import org.monopoly.model.Config;

public class Construccion {
    private Config.TiposConstrucciones tipo;
    private int valorAlquiler;

    public Construccion (Config.TiposConstrucciones tipo, int valorConstruir, int valorDestruir, int valorAlquiler){
        this.tipo = tipo;
        this.valorAlquiler = valorAlquiler;
    }

    public Config.TiposConstrucciones getTipo() {
        return this.tipo;
    }

    public int getValorAlquiler() {
        return this.valorAlquiler;
    }
}