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

    public Persona(int num_persona, int origen, Hospital hospital) {
        this.idPersona = num_persona;
        this.origen = origen;
        do {
            this.destino = new Random().nextInt(21);
        } while (origen == destino);
        this.hospital = hospital;
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
        int asc = hospital.comprobarAscensor(origen);
        String sentido;
        if (origen < destino) {
            sentido = "S";
        } else {
            sentido = "B";
        }
        while (!(asc != -1 && hospital.getAscensores()[asc].getN_personas() < hospital.getAscensores()[asc].getCapacidad() 
                && (hospital.getAscensores()[asc].getEstado().equals("P") || hospital.getAscensores()[asc].getEstado().equals(sentido)))) {
            if (!hospital.getPlantasHospital()[origen].isBotonPulsado()) {
                hospital.pulsarBoton(origen);
            }
            asc = hospital.comprobarAscensor(origen);
        }
    }
}
