package org.monopoly.model.casilla;
import java.util.ArrayList;
import java.util.List;

public class Propiedad extends Comprable{
    private int valorHipoteca;
    private boolean estaHipotecada;
    private ArrayList<Construccion> listaConstruccion = new ArrayList<>();
    private int cantConstruidos;
    private int valorTotalConstruidos;

    public Propiedad(Config.TiposCasillas tipo, String nombre, int valorCompra, int valorRentaBasica, Config.ColoresComprables color, int valorHipoteca, ArrayList<Construccion> listaConstruccion){
        super(tipo, nombre, valorCompra, valorRentaBasica, color);
        this.valorHipoteca = valorHipoteca;
        this.listaConstruccion = listaConstruccion;
    }

    public bool construir(Jugador jugador){
        if jugador != this.dueño || this.cantConstruidos >= len(this.listaConstruccion)-1{// falta chequear que la diferencia sea menor a 1 entre todas las propiedades < Config.MaxCasas && // es dueño de todas las propiedades del mismo color
            return false;
        }
        valorConstruir = this.listaConstruccion[this.cantConstruidos].getValorConstruir();
        this.dueño.retirarDinero(valorConstruir);
        valorAlquiler = this.listaConstruccion[cantConstruidos].getValorAlquiler();
        this.valorTotalConstruidos += valorAlquiler;
        this.cantConstruidos++
        return true
    }

    public bool destruir(Jugador jugador){
        if jugador != this.dueño && this.cantConstruidos > 0 {// falta chequear que la diferencia sea menor a 1 entre todas las propiedades
            return false;
        }
        valorDestruir = this.listaConstruccion[this.cantConstruidos].getValorDestruir();
        this.dueño.sumarDinero(valorDestruir);
        valorAlquiler = this.listaConstruccion[cantConstruidos].getValorAlquiler();
        this.valorTotalConstruidos -= valorAlquiler;
        this.cantConstruidos--
        return true
    }

    public void aplicarEfecto(Jugador jugador){
        if this.dueño == null || this.estaHipotecada || jugador == this.dueño || jugador.getCasillaActual() != this {
            return;
        }
        renta = rentaBasica + valorTotalConstruidos;
        jugador.retirarDinero(renta);
        this.dueño.SumarDinero(renta);
    }

    public void hipotecar(Jugador jugador){
        if this.dueño != jugador || this.estaHipotecada || this.cantConstruidos > 0 {
            return;
        }
        this.estaHipotecada = true;
        jugador.sumarDinero(this.valorHipoteca)
    }

    public void deshipotecar(Jugador jugador){
        if this.dueño != jugador || !this.estaHipotecada{
            return;
        }
        this.estaHipotecada = false;
        jugador.retirarDinero(this.valorHipoteca) // donde chequea que tenga la plata disponible?
    }
}
