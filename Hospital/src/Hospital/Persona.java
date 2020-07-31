package Hospital;

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

    private String idPersona;
    private int origen;
    private int destino;
    private String sentido;
    private Hospital hospital;

    public Persona(String num_persona, Hospital hospital) {
        this.idPersona = "P" + num_persona;
        this.hospital = hospital;
        this.origen = (int) (20 * Math.random());
        this.destino = (int) (20 * Math.random());
        while (origen == destino) {
            destino = (int) (20 * Math.random());
        }
        //sentido
        if ((origen - destino) > 0) {
            sentido = "B";
        } else {
            sentido = "S";
        }
    }

    /**
     * Get the value of idPersona
     *
     * @return the value of idPersona
     */
    public String getIdPersona() {
        return idPersona;
    }

    /**
     * Set the value of idPersona
     *
     * @param idPersona new value of idPersona
     */
    public void setIdPersona(String idPersona) {
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
     * Get the value of sentido
     *
     * @return the value of sentido
     */
    public String getSentido() {
        return sentido;
    }

    /**
     * Set the value of sentido
     *
     * @param sentido new value of sentido
     */
    public void setSentido(String sentido) {
        this.sentido = sentido;
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
