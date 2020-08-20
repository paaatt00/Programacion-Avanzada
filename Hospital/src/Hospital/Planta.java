/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital;

import java.util.ArrayList;

/**
 *
 * @author Patricia
 */
public class Planta {
    
    private int idPlanta;
    private boolean botonPulsado;
    private ArrayList<Persona> personas;

    public Planta(int idPlanta) {
        this.idPlanta = idPlanta;
        this.botonPulsado = false;
        this.personas = new ArrayList<Persona>();
    }

    public int getIdPlanta() {
        return idPlanta;
    }

    public boolean isBotonPulsado() {
        return botonPulsado;
    }

    public void setBotonPulsado(boolean botonPulsado) {
        this.botonPulsado = botonPulsado;
    }

    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(ArrayList<Persona> personas) {
        this.personas = personas;
    }
}
