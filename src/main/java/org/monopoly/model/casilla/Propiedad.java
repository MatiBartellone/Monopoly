package org.monopoly.model.casilla;
import java.util.ArrayList;
import java.util.List;

public class Propiedad extends Comprable{
    private Config.arrRenta arrRenta
    private int valorHipoteca;
    private boolean estaHipotecada;
    private int cantCasas;
    private int cantHotel;

    public Propiedad(Config.TiposCasillas tipo, String nombre, Jugador jugador, int valorHipoteca, Config.arrRenta arrRenta, int valorCompra, Config.ColoresCasillas color, int valorCasa){
        super(tipo, nombre, jugador, valorCompra, color);
        this.arrRenta = arrRenta
        this.valorHipoteca = valorHipoteca;
        this.estaHipotecada = false;
        this.cantCasas = 0;
        this.cantHotel = 0;
    }

    public bool construir(Jugador jugador){
        if jugador != this.dueño || this.cantCasas < // falta chequear que la diferencia sea menor a 1 entre todas las propiedades < Config.MaxCasas && // es dueño de todas las propiedades del mismo color
    }

    public bool destruir(Jugador jugador){
        return jugador != this.dueño && this.cantCasas > 0 // falta chequear que la diferencia sea menor a 1 entre todas las propiedades
    }

    public void aplicarEfecto(Jugador jugador){
        if this.dueño == null || this.estaHipotecada || jugador == this.dueño || jugador.getCasillaActual() != this {
            return;
        }
        renta = arrRenta[this.cantCasas + this.cantHotel]
        jugador.retirarDinero(renta)
    }

    public void hipotecar(Jugador jugador){
        if this.dueño != jugador || this.estaHipotecada{
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
        jugador.retirarDinero(this.valorHipoteca)
    }
}
