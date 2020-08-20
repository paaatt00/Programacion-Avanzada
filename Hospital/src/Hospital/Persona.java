package Hospital;

import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Patricia
 */
public class Persona extends Thread {

    private int idPersona;
    private int origen;
    private int destino;

    public Persona(int num_persona, int origen) {
        this.idPersona = num_persona;
        this.origen = origen;
        do {
            this.destino = new Random().nextInt(21);
        } while (origen == destino);
    }

    public int getIdPersona() {
        return idPersona;
    }

    public int getOrigen() {
        return origen;
    }

    public int getDestino() {
        return destino;
    }

    @Override
    public void run() {

    }
}
