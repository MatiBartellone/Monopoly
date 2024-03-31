package org.monopoly.model.casilla;

public class Propiedad extends Comprable{
    private int cantCasas;
    private int valorCasa;

    public Propiedad(Config.TiposCasillas tipo, String nombre, Jugador dueño, int valorHipoteca, int rentaBasica, int valorCompra, Config.ColoresCasillas color, int valorCasa){
        super(tipo, nombre, dueño, valorHipoteca, rentaBasica, valorCompra, color);
        this.cantCasas = 0;
        this.valorCasa = valorCasa;
    }

    private bool esDueño(Jugador jugador){
        return jugador == this.dueño
    }

    public bool permiteConstruir(Jugador jugador){
        return esDueño(jugador) && this.cantCasas > 0 && this.cantCasas // falta chequear que la diferencia sea menor a 1 entre todas las propiedades < Config.MaxCasas && // es dueño de todas las propiedades del mismo color
    }

    public bool permiteDestruir(Jugador jugador){
        return esDueño(jugador) && this.cantCasas > 0 // falta chequear que la diferencia sea menor a 1 entre todas las propiedades
    }
}
