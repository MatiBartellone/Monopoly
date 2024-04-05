package org.monopoly.model.casilla;

public class Construccion {
    private string tipo;
    private int valorConstruir;
    private int valorDestruir;
    private int valorAlquiler;

    public Construccion (String tipo, int valorConstruir, int valorDestruir, int valorAlquiler){
        this.tipo = tipo;
        this.valorConstruir = valorConstruir;
        this.valorDestruir = valorDestruir;
        this.valorAlquiler = valorAlquiler;
    }

    public string getTipo() {
        return tipo;
    }

    public int getValorConstruir() {
        return valorConstruir;
    }

    public int getValorDestruir() {
        return valorDestruir;
    }

    public int getValorAlquiler() {
        return valorAlquiler;
    }
}