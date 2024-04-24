package org.monopoly.controller.accion;

public interface Accion {
    void accionar();
    enum Etapa{INICIO, CASILLA, FIN}
    Etapa getEtapa();
    String getNombre();

}
