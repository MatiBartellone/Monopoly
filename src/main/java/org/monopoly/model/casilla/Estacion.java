package org.monopoly.model.casilla;

public class Estacion extends Comprable {
    public Propiedad(Config.TiposCasillas tipo, String nombre, Jugador jugador, int rentaBasica, int valorCompra, Config.ColoresCasillas color){
        super(tipo, nombre, jugador, valorCompra, rentaBasica, color);
    }

    public void aplicarEfecto(Jugador jugador){
        if this.dueño == null || jugador.getCasillaActual() != this || jugador.poseerDeTipo(this.getTipo())  {
            return;
        }
        renta = this.rentaBasica * this.dueño.obtenerCantSet(this.getTipo());
        jugador.retirarDinero(renta)
        this.dueño.SumarDinero(renta)
    }