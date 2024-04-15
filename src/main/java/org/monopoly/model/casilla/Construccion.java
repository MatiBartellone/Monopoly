package org.monopoly.model.casilla;

public class Construccion {
    private string tipo;
    private int valorAlquiler;

    public Construccion (String tipo, int valorConstruir, int valorDestruir, int valorAlquiler){
        this.tipo = tipo;
        this.valorAlquiler = valorAlquiler;
    }

    public string getTipo() {
        return tipo;
    }

    public int getValorAlquiler() {
        return valorAlquiler;
    }
}