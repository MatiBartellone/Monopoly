package org.monopoly.model.casilla;
import java.util.ArrayList;
import java.util.List;

public class Propiedad extends Comprable{
    private ArrayList<Construccion> listaConstruccion = new ArrayList<>();
    private int cantConstruidos;
    private int valorConstruir;
    private int valorDestruir;
    private int valorTotalConstruidos;

    public Propiedad(Config.TiposCasillas tipo, String nombre, int valorCompra, int valorRentaBasica, Config.ColoresComprables color, int valorHipoteca, ArrayList<Construccion> listaConstruccion, int valorConstruir, int valorDestruir){
        super(tipo, nombre, valorCompra, valorRentaBasica, color);
        this.valorHipoteca = valorHipoteca;
        this.valorConstruir = valorConstruir;
        this.valorDestruir = valorDestruir;
        this.listaConstruccion = listaConstruccion;
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
        valorAlquiler = this.listaConstruccion[cantConstruidos].getValorAlquiler();
        this.valorTotalConstruidos += valorAlquiler;
        this.cantConstruidos++
    }

    public void destruir(){
        valorAlquiler = this.listaConstruccion[cantConstruidos].getValorAlquiler();
        this.valorTotalConstruidos -= valorAlquiler;
        this.cantConstruidos--
    }

    private boolean sePuedeAccionar(AdmJugador admJugador, Jugador jugador){
        return admJugador.tieneDueño(this) && jugador.getCasillaActual() == this
    }

    private void cobrarAlquiler(AdmJugador admJugador, Jugador jugador){
        renta = this.rentaBasica + this.valorTotalConstruidos;
        admJugador(jugador, admJugador.obtenerDueño(this), renta)
    }
}
