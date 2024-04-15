package org.monopoly.model.casilla;
import java.util.ArrayList;
import java.util.List;

public class Propiedad extends Comprable{
    private ArrayList<Construccion> listaConstruccion = new ArrayList<>();
    private int cantConstruidos;
    private int valorTotalConstruidos;
    private int valorConstruir;
    private int valorDestruir;

    public Propiedad(Config.TiposCasillas tipo, String nombre, int valorCompra, int valorRentaBasica, Config.ColoresComprables color, int valorHipoteca, ArrayList<Construccion> listaConstruccion, int valorConstruir, int valorDestruir){
        super(tipo, nombre, valorCompra, valorRentaBasica, color);
        this.valorHipoteca = valorHipoteca;
        this.listaConstruccion = listaConstruccion;
        this.valorConstruir = valorConstruir;
        this.valorDestruir = valorDestruir;
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

    public bool construir(){
        if this.cantConstruidos >= len(this.listaConstruccion)-1{// falta chequear que la diferencia sea menor a 1 entre todas las propiedades < Config.MaxCasas && // es dueño de todas las propiedades del mismo color
            return false;
        }
        valorAlquiler = this.listaConstruccion[cantConstruidos].getValorAlquiler();
        this.valorTotalConstruidos += valorAlquiler;
        this.cantConstruidos++
        return true
    }

    public bool destruir(){
        if this.cantConstruidos > 0 {// falta chequear que la diferencia sea menor a 1 entre todas las propiedades
            return false;
        }
        valorAlquiler = this.listaConstruccion[cantConstruidos].getValorAlquiler();
        this.valorTotalConstruidos -= valorAlquiler;
        this.cantConstruidos--
        return true
    }

    private boolean sePuedeAccionar(AdmJugador admJugador, Jugador jugador){
        return admJugador.tieneDueño(this) && jugador.getCasillaActual() == this
    }

    private void cobrarAlquiler(AdmJugador admJugador, Jugador jugador){
        renta = this.rentaBasica + this.valorTotalConstruidos;
        admJugador(jugador, admJugador.obtenerDueño(this), renta)
    }
}
