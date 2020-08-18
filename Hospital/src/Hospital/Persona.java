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
    private Hospital hospital;

    public Persona(int num_persona, Hospital hospital, int origen) {
        this.idPersona = num_persona;
        this.hospital = hospital;
        this.origen = origen;
        do {
            this.destino = new Random().nextInt(21);
        } while (origen == destino);
    }

    /**
     * Get the value of idPersona
     *
     * @return the value of idPersona
     */
    public int getIdPersona() {
        return idPersona;
    }

    /**
     * Set the value of idPersona
     *
     * @param idPersona new value of idPersona
     */
    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    /**
     * Get the value of origen
     *
     * @return the value of origen
     */
    public int getOrigen() {
        return origen;
    }

    /**
     * Set the value of origen
     *
     * @param origen new value of origen
     */
    public void setOrigen(int origen) {
        this.origen = origen;
    }

    /**
     * Get the value of destino
     *
     * @return the value of destino
     */
    public int getDestino() {
        return destino;
    }

    /**
     * Set the value of destino
     *
     * @param destino new value of destino
     */
    public void setDestino(int destino) {
        this.destino = destino;
    }

    /**
     * Get the value of hospital
     *
     * @return the value of hospital
     */
    public Hospital getHospital() {
        return hospital;
    }

    /**
     * Set the value of hospital
     *
     * @param hospital new value of hospital
     */
    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    @Override
    public void run() {

    }
}
