package org.monopoly.model.casilla;

public class Propiedad extends Comprable{
    private int valorHipoteca;
    private boolean estaHipotecada;
    private int cantCasas;
    private int valorCasa;

    public Propiedad(Config.TiposCasillas tipo, String nombre, int valorHipoteca, int rentaBasica, int valorCompra, Config.ColoresCasillas color, int valorCasa){
        super(tipo, nombre, rentaBasica, valorCompra, color);
        this.valorHipoteca = valorHipoteca;
        this.estaHipotecada = false;
        this.cantCasas = 0;
        this.valorCasa = valorCasa;
    }

    public bool permiteConstruir(Jugador jugador){
        return jugador == this.dueño && this.cantCasas < // falta chequear que la diferencia sea menor a 1 entre todas las propiedades < Config.MaxCasas && // es dueño de todas las propiedades del mismo color
    }

    public bool permiteDestruir(Jugador jugador){
        return jugador == this.dueño && this.cantCasas > 0 // falta chequear que la diferencia sea menor a 1 entre todas las propiedades
    }

    public void aplicarEfecto(Jugador jugador){
        if this.dueño == null || jugador == this.dueño || jugador.getCasillaActual() != this {
            return;
        }
        renta = this.rentaBasica + this.cantCasas * this.valorCasa;
        jugador.retirarDinero(renta)
    }
}
