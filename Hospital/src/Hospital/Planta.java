/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Patricia
 */
public class Planta {

    private int idPlanta;
    private boolean botonPulsado;
    private ArrayList<Persona> personas;
    private ReentrantLock lockBotonPulsado = new ReentrantLock();
    private ReentrantLock lockPersonas = new ReentrantLock();

    public Planta(int idPlanta) {
        this.idPlanta = idPlanta;
        this.botonPulsado = false;
        this.personas = new ArrayList<Persona>();
    }

    public int getIdPlanta() {
        return idPlanta;
    }

    public boolean isBotonPulsado() {
        boolean boton;
        lockBotonPulsado.lock();
        try {
            boton = botonPulsado;
        } finally {
            lockBotonPulsado.unlock();
        }
        return boton;
    }

    public void setBotonPulsado(boolean botonPulsado) {
        lockBotonPulsado.lock();
        try {
            this.botonPulsado = botonPulsado;
        } finally {
            lockBotonPulsado.unlock();
        }
    }

    public ArrayList<Persona> getPersonas() {
        ArrayList<Persona> p;
        lockPersonas.lock();
        try {
            p = personas;
        } finally {
            lockPersonas.unlock();
        }
        return p;
    }

    public void anadirPersona(Persona p) {
        lockPersonas.lock();
        try {
            personas.add(p);
        } finally {
            lockPersonas.unlock();
        }
    }

    public Persona eliminarPersona(int id) {
        Persona p = null;
        lockPersonas.lock();
        try {
            for (int i = 0; i < personas.size(); i++) {
                if (personas.get(i).getIdPersona() == id) {
                    p = personas.get(i);
                    personas.remove(i);
                    break;
                }
            }
        } finally {
            lockPersonas.unlock();
        }
        return p;
    }
}
