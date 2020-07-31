package Hospital;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
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
public class Hospital {

    private int num_mov;
    private int cont_mov;
    private int numAscActivos;
    private ArrayList<Integer> llamadaAscensores; //guarda desde donde llaman al ascensor
    private ArrayList<ArrayList<Persona>> plantasHospital; //lista de plantas con la lista de pesonas que están cada planta
    private Lock cerrojoPlanta = new ReentrantLock();
    private Lock cerrojoLLamadaAscensor = new ReentrantLock();
    private Lock cerrojoArranqueAscensor = new ReentrantLock();
    private Condition ascensorActivo;
    private Condition ascensorNoActivo;

    public Hospital() {
        this.num_mov = 5000;
        this.cont_mov = 0;
        ArrayList<Persona> planta = new ArrayList<>();
        for (int i = 0; i < 22; i++) {
            plantasHospital.add(planta);
        }
    }

    /**
     * Get the value of num_mov
     *
     * @return the value of num_mov
     */
    public int getNum_mov() {
        return num_mov;
    }

    /**
     * Get the value of cont_mov
     *
     * @return the value of cont_mov
     */
    public int getCont_mov() {
        return cont_mov;
    }

    /**
     * Set the value of cont_mov
     *
     * @param cont_mov new value of cont_mov
     */
    public void setCont_mov(int cont_mov) {
        this.cont_mov = cont_mov;
    }

    public boolean abierto() {
        if (cont_mov >= num_mov) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * mucho texto
     */
    public void gestionHospital() {

    }

    /**
     *
     * @param p
     */
    public void llegarHospital(Persona p) {
        asignarPlanta(p);
        pulsarBoton(p);
    }

    /**
     *
     * @param p
     */
    private void asignarPlanta(Persona p) {
        cerrojoPlanta.lock();
        try {
            plantasHospital.get(p.getOrigen()).add(p);
        } finally {
            cerrojoPlanta.lock();
        }
    }

    /**
     *
     * @param p
     */
    private void pulsarBoton(Persona p) {
        cerrojoLLamadaAscensor.lock();
        try {
            int contador = 0;
            for (int i = 0; i < llamadaAscensores.size(); i++) {
                if (llamadaAscensores.get(i) == p.getDestino()) {
                    contador++;
                }
            }
            if (contador == 0) { //no está pulsado el boton
                llamadaAscensores.add(p.getDestino());
            }
        } finally {
            cerrojoLLamadaAscensor.lock();
        }
    }

    /**
     * Función que arranca el ascensor, si no puede se queda a la espera
     * mediante await.
     *
     * @param a
     */
    public void ascArranca(Ascensor a) {
        cerrojoArranqueAscensor.lock();
        try {
            //ya hay dos ascensores activos
            while (numAscActivos == 2) {
                try {
                    ascensorActivo.await(); //activo = false
                } catch (InterruptedException e) {
                    System.out.println("Error en arranque");
                }
            }
            numAscActivos++;
            ascensorNoActivo.signal(); //activo = true
        } finally {
            cerrojoArranqueAscensor.unlock();
        }
    }

    /**
     * Función para el funcionamiento del ascensor y todo lo que tiene que hacer
     *
     * @param a
     */
    public void funcionamientoAsc(Ascensor a) {

    }

    public void pasarPorPlantas(Ascensor a) {
        if (a.getEstado() == "S") {
            if (a.getPlantaActual() != 20) { //en lugar de 20 habría que poner el máximo de la lista de plantas pendientes
                //planta++
            } else {
                a.setEstado("B");
            }
        } else if (a.getEstado() == "B") {
            if (a.getPlantaActual() != 0) { //en lugar de 0 habría que poner el mínimo de la lista de plantas pendientes
                //planta--
            } else {
                a.setEstado("S");
            }
        }
    }

}
