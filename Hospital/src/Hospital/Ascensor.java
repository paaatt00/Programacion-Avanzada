package Hospital;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Patricia
 */
public class Ascensor extends Thread {

    private int idAscensor;
    private String estado;
    private int capacidad;
    private int plantaActual;
    private ArrayList<Persona> personasDentro;
    private ReentrantLock lockPersonas = new ReentrantLock();
    private ReentrantLock lockPlanta = new ReentrantLock();

    public Ascensor(int id_ascensor) {
        this.idAscensor = id_ascensor;
        this.estado = "P";
        this.capacidad = 8;
        this.plantaActual = 0;
        this.personasDentro = new ArrayList<>();
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getN_personas() {
        int n;
        lockPersonas.lock();
        try {
            n = personasDentro.size();
        } finally {
            lockPersonas.unlock();
        }
        return n;
    }

    public int getPlantaActual() {
        int p;
        lockPlanta.lock();
        try {
            p = plantaActual;
        } finally {
            lockPlanta.unlock();
        }
        return p;
    }

    private void setPlantaActual(int plantaActual) {
        lockPlanta.lock();
        try {
            this.plantaActual = plantaActual;
        } finally {
            lockPlanta.unlock();
        }
    }

    public ArrayList<Persona> getPersonasDentro() {
        return personasDentro;
    }

    public void anadirPersona(Persona p) {
        lockPersonas.lock();
        try {
            personasDentro.add(p);
        } finally {
            lockPersonas.unlock();
        }
    }

    public Persona eliminarPersona(int id) {
        Persona p = null;
        lockPersonas.lock();
        try {
            for (int i = 0; i < personasDentro.size(); i++) {
                if (personasDentro.get(i).getIdPersona() == id) {
                    p = personasDentro.get(i);
                    personasDentro.remove(i);
                    break;
                }
            }
        } finally {
            lockPersonas.unlock();
        }
        return p;
    }

    @Override
    public void run() {
        switch (estado) {
            case "P": //cuando el ascensor está vacío y no ha recibido ninguna llamada
                //uwu
                break;
            case "S": //cuando sube
                plantaActual++;
                break;
            case "B": //cuando baja 
                plantaActual--;
                break;
            case "E": 
                //sacar personas del ascensor
                //mover personas a la planta
                try {
                sleep(new Random().nextInt(5000) + 10000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            break;

        }
    }
}
