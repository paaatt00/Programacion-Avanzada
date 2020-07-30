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

    private int id_ascensor;
    private String estado;
    private int capacidad;
    private int n_personas;
    private int planta_actual;
    private ArrayList<Integer> plantas_pendientes;
    private Hospital hospital;

    public Ascensor(int id_ascensor, Hospital hospital) {
        this.id_ascensor = id_ascensor;
        this.estado = "P";
        this.capacidad = 8;
        this.n_personas = 0;
        this.planta_actual = 0;
        this.hospital = hospital;
    }

    /**
     * Get the value of id_ascensor
     *
     * @return the value of id_ascensor
     */
    public int getId_ascensor() {
        return id_ascensor;
    }

    /**
     * Set the value of id_ascensor
     *
     * @param id_ascensor new value of id_ascensor
     */
    public void setId_ascensor(int id_ascensor) {
        this.id_ascensor = id_ascensor;
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
     * Get the value of planta_actual
     *
     * @return the value of planta_actual
     */
    public int getPlanta_actual() {
        return planta_actual;
    }

    /**
     * Set the value of planta_actual
     *
     * @param planta_actual new value of planta_actual
     */
    public void setPlanta_actual(int planta_actual) {
        this.planta_actual = planta_actual;
    }

    /**
     * Get the value of plantas_pendientes
     *
     * @return the value of plantas_pendientes
     */
    public ArrayList<Integer> getPlantas_pendientes() {
        return plantas_pendientes;
    }

    /**
     * Set the value of plantas_pendientes
     *
     * @param plantas_pendientes new value of plantas_pendientes
     */
    public void setPlantas_pendientes(ArrayList<Integer> plantas_pendientes) {
        this.plantas_pendientes = plantas_pendientes;
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

}
