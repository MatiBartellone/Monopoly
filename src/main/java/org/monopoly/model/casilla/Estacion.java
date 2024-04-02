package org.monopoly.model.casilla;

public class Estacion {
    private int rentaBasica;
    public Propiedad(Config.TiposCasillas tipo, String nombre, int rentaBasica, int valorCompra, Config.ColoresCasillas color){
        super(tipo, nombre,  valorCompra, color);
        this.rentaBasica = rentaBasica
    }

    public void aplicarEfecto(Jugador jugador){
        if this.dueño == null || jugador.getCasillaActual() != this || jugador.poseerDeTipo(this.getTipo())  {
            return;
        }
        renta = this.rentaBasica * this.dueño.cantDeTipo(this.getTipo());
        jugador.retirarDinero(renta)
    }
