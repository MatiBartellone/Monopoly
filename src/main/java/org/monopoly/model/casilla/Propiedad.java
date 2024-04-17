package org.monopoly.model.casilla;

import org.monopoly.model.AdmJugador;
import org.monopoly.model.Config;
import org.monopoly.model.Jugador;

import java.util.ArrayList;
import java.util.List;

public class Propiedad extends Comprable{
    private List<Construccion> construcciones = new ArrayList<>();
    private int cantConstruidos;
    private int valorConstruir;
    private int valorDestruir;
    private int valorTotalConstruidos;

    public Propiedad(Config.TiposCasillas tipo, String nombre, int valorCompra, int valorRentaBasica, Config.ColoresComprables color, int valorHipoteca, ArrayList<Construccion> listaConstruccion, int valorConstruir, int valorDestruir){
        super(tipo, nombre, valorCompra, valorRentaBasica, color);
        this.construcciones = new ArrayList<>();
        this.valorHipoteca = valorHipoteca;
        this.valorConstruir = valorConstruir;
        this.valorDestruir = valorDestruir;
        this.construcciones = listaConstruccion;
    }

    public int getValorConstruir() {
        return this.valorConstruir;
    }

    public int getValorDestruir() {
        return this.valorDestruir;
    }

    public int getCantConstruidos() {
        return this.cantConstruidos;
    }

    public void construir(){
        int valorAlquiler = this.construcciones.get(cantConstruidos).getValorAlquiler();
        this.valorTotalConstruidos += valorAlquiler;
        this.cantConstruidos++;
    }

    public void destruir(){
        int valorAlquiler = this.construcciones.get(cantConstruidos).getValorAlquiler();
        this.valorTotalConstruidos -= valorAlquiler;
        this.cantConstruidos--;
    }

    protected int calcularAlquiler(AdmJugador admJugador, Jugador jugador){
        return this.valorRentaBasica + this.valorTotalConstruidos;
    }
}
