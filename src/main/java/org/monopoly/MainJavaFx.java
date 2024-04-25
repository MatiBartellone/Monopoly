package org.monopoly;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.monopoly.controller.JuegoController;
import org.monopoly.controller.SeleccionadorController;
import org.monopoly.model.Config;
import org.monopoly.model.Juego;
import org.monopoly.model.Jugador;
import org.monopoly.model.Tablero;
import org.monopoly.model.casilla.Casilla;
import org.monopoly.model.casilla.Comprable;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MainJavaFx extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception{
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/escena-inicio.fxml"));
        AnchorPane root = loader.load();

        SeleccionadorController juegoController = loader.getController();
        juegoController.setSeleccionador();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Monopoly");
        stage.show();
    }


    public static void closeStage(){
        stage.close();
    }
}
