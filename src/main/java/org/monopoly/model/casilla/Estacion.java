package org.monopoly.model.casilla;

public class Estacion {
    public Propiedad(Config.TiposCasillas tipo, String nombre, int rentaBasica, int valorCompra, Config.ColoresCasillas color){
        super(tipo, nombre, rentaBasica, valorCompra);
    }

    public void aplicarEfecto(Jugador jugador){
        if this.dueño == null || jugador.getCasillaActual() != this || jugador.poseerDeTipo(this.getTipo())  {
            return;
        }
        renta = this.rentaBasica * this.dueño.cantDeTipo(this.getTipo());
        jugador.retirarDinero(renta)
    }
