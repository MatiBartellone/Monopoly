package org.monopoly.model;

import java.util.*;

public class AdmTurnos {
    private List<Jugador> jugadores;
    private Map<Jugador,Integer> turnosEnCarcel;
    private int turno;
    //SuscriptorTurnosPrision es una interfaz que tiene el metodo updateEstadoCondena(jugador)
    //el adminJugador se debe suscribir para mantenerse al tanto de quienes ya cumplieron sus condenas de prision
    private List<SuscriptorTurnosPrision> suscriptores;

    public AdmTurnos(List<Jugador> jugadores){
        this.turno = 0;
        Collections.shuffle(jugadores);
        this.jugadores = jugadores;
        Collections.shuffle(this.jugadores);
        this.turnosEnCarcel = new HashMap<>();
        for (Jugador jugador : jugadores){turnosEnCarcel.put(jugador,0);}
        suscriptores = new ArrayList<SuscriptorTurnosPrision>();
    }
    public void setSuscriptorTurnosPrision(SuscriptorTurnosPrision suscriptor){suscriptores.add(suscriptor);}

    //ojo tiene que haber un siguiente jugador que no esté quebrado
    //maneja si el jugador que está terminando su turno acaba de cumplir su ultimo turno en prision
    public void siguiente(){
        this.updateSaliente(this.getJugadorActual());
        int iJugador = this.sgteListaCircular();
        while (jugadores.get(iJugador).getEstado()==Config.EstadosJugadores.QUEBRADO){
            iJugador = this.sgteListaCircular();
        }
        this.turno = iJugador;
    }
    public Jugador getJugadorActual(){return this.jugadores.get(this.turno);}
    private int sgteListaCircular(){
        if (this.turno < this.jugadores.size() - 1 ){return this.turno + 1;}
        return 0;
    }
    private void updateSaliente(Jugador saliente){
        if (saliente.getEstado()!= Config.EstadosJugadores.PRESO){return;}
        if (turnosEnCarcel.get(saliente)!=0){
            int turnosEnCarcelRestantes = turnosEnCarcel.get(saliente)-1;
            turnosEnCarcel.put(saliente,turnosEnCarcelRestantes);
            if (turnosEnCarcelRestantes==0){for(SuscriptorTurnosPrision suscriptor: suscriptores){suscriptor.updateEstadoCondena(saliente);}}
        } else {turnosEnCarcel.put(saliente,Config.TurnosCarcel);}
    }
}

