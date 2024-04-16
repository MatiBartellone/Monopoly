package org.monopoly.model;

import java.util.concurrent.ThreadLocalRandom;

public class AdmMovimientos {
    private final Tablero tablero;
    private final int cantDados = 2;
    private int[] dados;
    public AdmMovimientos(Tablero tablero){
        this.tablero = tablero;
        this.dados= new int[cantDados];
    }
    public void tirarDados(){
        for(int i=0;i < dados.length; i++){this.dados[i]= ThreadLocalRandom.current().nextInt(1, 6 + 1);}
    }
    public int[] getDados(){return dados;}
    public boolean sonDadosIguales(){
        int valor=dados[0];
        for(int dado: dados){if (dado!=valor){return false;}}
        return true;
    }
    public void mover(Jugador jugador){
        jugador.mover(tablero.casillaSiguiente(jugador.getCasillaActual(),this.sumarDados(dados)));
    }
    private int sumarDados(int[] dados) {
        int suma = 0;
        for (int dado : dados) {suma += dado;}
        return suma;
    }
}

