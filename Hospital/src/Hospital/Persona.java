package Hospital;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

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
    private boolean enAscensor = false;
    private Hospital hospital;
    private ReentrantLock lock = new ReentrantLock();

    public Persona(int num_persona, int origen, Hospital hospital) {
        this.idPersona = num_persona;
        this.origen = origen;
        do {
            this.destino = new Random().nextInt(21);
        } while (origen == destino);
        this.hospital = hospital;
    }
    
    public Persona(int num_persona, int origen, int destino, Hospital hospital) {
        this.idPersona = num_persona;
        this.origen = origen;
        this.destino = destino;
        this.hospital = hospital;
    }

    public synchronized int getIdPersona() {
        return idPersona;
    }

    public synchronized int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public synchronized int getDestino() {
        return destino;
    }

    public boolean isEnAscensor() {
        boolean b;
        lock.lock();
        try {
            b = enAscensor;
        } finally {
            lock.unlock();
        }
        return b;
    }

    public void setEnAscensor(boolean enAscensor) {
        lock.lock();
        try {
            this.enAscensor = enAscensor;
        } finally {
            lock.unlock();
        }
    } 

    @Override
    public void run() {
        while (!isEnAscensor()) {
            try {
                if (!hospital.getPlantasHospital()[origen].isBotonPulsado()) {
                    hospital.pulsarBoton(origen);
                }
                sleep(100);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
