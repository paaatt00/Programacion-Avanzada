package Hospital;

import java.util.ArrayList;

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
    private int n_personas; //personas dentro del ascensor
    private int plantaActual;
    private ArrayList<Persona> personasDentro;
    
    public Ascensor(int id_ascensor) {
        this.idAscensor = id_ascensor;
        this.estado = "P";
        this.capacidad = 8;
        this.n_personas = 0;
        this.plantaActual = 0;
        this.personasDentro = new ArrayList<Persona>(); 
    }

    public int getIdAscensor() {
        return idAscensor;
    }

    public void setIdAscensor(int idAscensor) {
        this.idAscensor = idAscensor;
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

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getN_personas() {
        return n_personas;
    }

    public void setN_personas(int n_personas) {
        this.n_personas = n_personas;
    }

    public int getPlantaActual() {
        return plantaActual;
    }

    public void setPlantaActual(int plantaActual) {
        this.plantaActual = plantaActual;
    }

    public ArrayList<Persona> getPersonasDentro() {
        return personasDentro;
    }
    
    @Override
    public void run() {
        
    }
}
