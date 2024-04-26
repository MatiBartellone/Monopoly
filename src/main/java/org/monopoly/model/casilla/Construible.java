package org.monopoly.model.casilla;

public interface Construible {
    public void construir();
    public void destruir();
    public int getCantConstruidos();
    public int getValorConstruir();
    public int getValorDestruir();
    public void demolerConstrucciones();
}
