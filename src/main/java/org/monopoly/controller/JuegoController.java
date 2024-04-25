package org.monopoly.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.monopoly.controller.accion.*;
import org.monopoly.controller.validador.Validador;
import org.monopoly.controller.validador.ValidadorAccionFinal;
import org.monopoly.controller.validador.ValidadorAccionInicio;
import org.monopoly.controller.validador.ValidadorAccionesCasilla;
import org.monopoly.model.Juego;
import org.monopoly.model.Jugador;
import org.monopoly.model.casilla.Casilla;
import org.monopoly.model.casilla.Comprable;
import org.monopoly.view.BotonView;
import org.monopoly.view.CasillaView;
import org.monopoly.view.JugadorView;
import org.monopoly.view.TableroView;

import java.util.ArrayList;
import java.util.List;

public class JuegoController {
    private Juego juego;
    private Validador validadorInicio;

    private List<JugadorView> jugadorViews;

    @FXML
    private VBox botonera;
    @FXML
    private VBox jugadores;

    @FXML
    private ImageView dado1;

    @FXML
    private ImageView dado2;

    @FXML
    private ImageView jugadorActual;

    @FXML
    private HBox contenedorJuego;

    @FXML
    private VBox contenedorCentral;

    private TableroView tableroView;

    private static final String ESTILO_BOTON = "-fx-min-width: 150; -fx-min-height: 50; -fx-border-radius: 25; -fx-background-radius: 25; -fx-background-color: #F8C471; -fx-border-color: #E59866; -fx-border-width: 3;";
    private static final String ESTILO_BOTON_HOVER = "-fx-min-width: 150; -fx-min-height: 50; -fx-border-radius: 25; -fx-background-radius: 25; -fx-background-color: #A04000; -fx-border-color: #E59866; -fx-border-width: 3;";

    private List<Validador> validadores;

    public void setJuego(Juego juego){
        this.juego = juego;
        this.validadorInicio = new ValidadorAccionInicio(this.juego);
        this.validadores = new ArrayList<>(){{
            add(new ValidadorAccionesCasilla(juego));
            add(new ValidadorAccionFinal(juego));
        }};

        List<String> nombres = new ArrayList<>(){{
            add("MATI");
            add("THIAGO");
            add("IVAN");
            add("ANDREA");
        }};

        this.jugadorViews = setJugadores(this.juego.getJugadores(), nombres);
        TableroView tableroView = new TableroView(juego.getTablero(), this.juego.getJugadores());
        this.tableroView = tableroView;
        contenedorCentral.getChildren().add(tableroView.getTablero());

        actualizarDatos();

        actualizarBotonesInicio();

        contenedorJuego.setOnMouseClicked(e -> {
            for (JugadorView jugadorView : jugadorViews) {
                jugadorView.cerrarPropiedades();
            }
        });

    }

    public List<Accion> opcionesAcciones (Jugador jugador){
        List<Accion> acciones = new ArrayList<>();
        for (Validador v : this.validadores){acciones.addAll(v.accionesPosibles(jugador));}
        return acciones;
    }

    public void actualizarDatos(){
        actualizarJugadorActual();
        actualizarDados();
        actualizarJugadores();
        tableroView.actualizarTablero(this.juego.getJugadores());
    }
    public void actualizarBotonesAccion(){
        setBotonesAccion(this.opcionesAcciones(this.juego.getJugadorActual()));
    }
    public void actualizarBotonesInicio(){
        setBotonesAccion(this.validadorInicio.accionesPosibles(this.juego.getJugadorActual()));
    }

    public void setBotonesAccion(List<Accion> listaAccion){
        for (int i = 0; i < listaAccion.size() ; i++){
            Accion accion = listaAccion.get(i);
            if (accion instanceof AccionCasilla accionCasilla) {
                Button nuevo = BotonView.crearBoton(accion.getNombre(), ESTILO_BOTON, ESTILO_BOTON_HOVER, e -> {
                    botonera.getChildren().clear();
                    setBotonesCasillas(accionCasilla.getOpciones(), accionCasilla);
                });
                this.botonera.getChildren().add(nuevo);
            } else {
                Button nuevo = BotonView.crearBoton(accion.getNombre(), ESTILO_BOTON, ESTILO_BOTON_HOVER ,e -> {
                    botonera.getChildren().clear();
                    accion.accionar();
                    actualizarDatos();
                    if (accion instanceof AccionFinal) actualizarBotonesInicio();
                    else actualizarBotonesAccion();
                });
                this.botonera.getChildren().add(nuevo);
            }
        }
    }
    public void setBotonesCasillas(List<Casilla> casillas, AccionCasilla accion){
        for (int i = 0; i < casillas.size() ; i++){
            Comprable comprable = (Comprable) casillas.get(i);
            Button nuevo = BotonView.crearBoton(comprable.getNombre(), ESTILO_BOTON, ESTILO_BOTON_HOVER, e -> {
                botonera.getChildren().clear();
                accion.setCasilla(comprable);
                accion.accionar();
                this.actualizarDatos();
                this.actualizarBotonesAccion();
            });
            this.botonera.getChildren().add(nuevo);
        }
    }

    private List<JugadorView> setJugadores(List<Jugador> jugadores, List<String> nombres){
        List<JugadorView> jugadorViews = new ArrayList<>();
        for (int i = 0 ; i<jugadores.size() ; i++){
            jugadorViews.add(new JugadorView(this.juego ,jugadores.get(i), nombres.get(i)));
        }
        return jugadorViews;
    }

    private void actualizarJugadores(){
        this.jugadores.getChildren().remove(0, jugadores.getChildren().size());
        for (JugadorView jugadorView : this.jugadorViews){
            jugadorView.actualizarTarjeta();
            this.jugadores.getChildren().add(jugadorView.getTarjeta());
        }
    }

    public void actualizarJugadorActual(){
        for (JugadorView jugadorView : this.jugadorViews){
            if (jugadorView.getJugador() == this.juego.getJugadorActual()){
                this.jugadorActual.setImage(new Image(this.getClass().getResourceAsStream(jugadorView.getImagen())));
            }
        }
    }

    public void actualizarDados(){
        List<ImageView> dados = new ArrayList<>(){{
            add(dado1);
            add(dado2);
        }};
        for (int i = 0 ; i < dados.size() ; i++ ){
            int dado = this.juego.getAdmMovimientos().getDados()[i];
            if (dado != 0) dados.get(i).setImage(new Image(this.getClass().getResourceAsStream("/images/"+dado+".png")));
        }
    }
}