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
    private int n_personas;
    private int plantaActual;
    private ArrayList<Integer> plantasPendientes;
    private Hospital hospital;
    
    public Ascensor(int id_ascensor, Hospital hospital) {
        this.idAscensor = id_ascensor;
        this.estado = "P";
        this.capacidad = 8;
        this.n_personas = 0;
        this.plantaActual = 0;
        this.hospital = hospital;
    }

    /**
     * Get the value of idAscensor
     *
     * @return the value of idAscensor
     */
    public int getIdAscensor() {
        return idAscensor;
    }

    /**
     * Set the value of idAscensor
     *
     * @param idAscensor new value of idAscensor
     */
    public void setIdAscensor(int idAscensor) {
        this.idAscensor = idAscensor;
    }

    /**
     * Get the value of estado
     *
     * @return the value of estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Set the value of estado
     *
     * @param estado new value of estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Get the value of capacidad
     *
     * @return the value of capacidad
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Set the value of capacidad
     *
     * @param capacidad new value of capacidad
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * Get the value of n_personas
     *
     * @return the value of n_personas
     */
    public int getN_personas() {
        return n_personas;
    }

    /**
     * Set the value of n_personas
     *
     * @param n_personas new value of n_personas
     */
    public void setN_personas(int n_personas) {
        this.n_personas = n_personas;
    }

    /**
     * Get the value of plantaActual
     *
     * @return the value of plantaActual
     */
    public int getPlantaActual() {
        return plantaActual;
    }

    /**
     * Set the value of plantaActual
     *
     * @param plantaActual new value of plantaActual
     */
    public void setPlantaActual(int plantaActual) {
        this.plantaActual = plantaActual;
    }

    /**
     * Get the value of plantasPendientes
     *
     * @return the value of plantasPendientes
     */
    public ArrayList<Integer> getPlantasPendientes() {
        return plantasPendientes;
    }

    /**
     * Set the value of plantasPendientes
     *
     * @param plantasPendientes new value of plantasPendientes
     */
    public void setPlantasPendientes(ArrayList<Integer> plantasPendientes) {
        this.plantasPendientes = plantasPendientes;
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
        hospital.ascArranca(this);
        while (hospital.abierto()) {
            while (estado != "E") {
                hospital.funcionamientoAsc(this);
            }
            if (estado == "E") {
                hospital.ascArranca(this);
            }
        }
    }
}
