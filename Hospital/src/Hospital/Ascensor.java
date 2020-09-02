package Hospital;

import java.util.ArrayList;
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
public class Ascensor extends Thread {

    private int idAscensor;
    private String estado;
    private int capacidad;
    private int plantaActual;
    private boolean esperando;
    private Hospital hospital;
    private ArrayList<Persona> personasDentro;
    private ReentrantLock lockPersonas = new ReentrantLock();
    private ReentrantLock lockPlanta = new ReentrantLock();
    private ReentrantLock lockEstado = new ReentrantLock();
    private ReentrantLock lockEsperando = new ReentrantLock();

    public Ascensor(int id_ascensor, boolean esperando, Hospital hospital) {
        this.idAscensor = id_ascensor;
        this.estado = "P";
        this.capacidad = 8;
        this.plantaActual = new Random().nextInt(hospital.getPlantasHospital().length);
        this.esperando = esperando;
        this.hospital = hospital;
        this.personasDentro = new ArrayList<>();
    }

    public int getIdAscensor() {
        return idAscensor;
    }

    public String getEstado() {
        String s;
        lockEstado.lock();
        try {
            s = estado;
        } finally {
            lockEstado.unlock();
        }
        return s;
    }

    public void setEstado(String estado) {
        lockEstado.lock();
        try {
            this.estado = estado;
        } finally {
            lockEstado.unlock();
        }
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getN_Personas() {
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

    public boolean isEsperando() {
        boolean e;
        lockEsperando.lock();
        try {
            e = esperando;
        } finally {
            lockEsperando.unlock();
        }
        return e;
    }

    public void setEsperando(boolean esperando) {
        lockEsperando.lock();
        try {
            this.esperando = esperando;
        } finally {
            lockEsperando.unlock();
        }
    }

    public ArrayList<Persona> getPersonasDentro() {
        return personasDentro;
    }

    public void anadirPersona(Persona p) {
        lockPersonas.lock();
        try {
            p.setEnAscensor(true);
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

    public synchronized void entrarAscensor() {
        for (int i = 0; i < hospital.getPlantasHospital()[getPlantaActual()].getPersonas().size(); i++) {
            if (getN_Personas() < capacidad) {
                if (hospital.getPlantasHospital()[getPlantaActual()].getPersonas().get(i).getOrigen() < hospital.getPlantasHospital()[getPlantaActual()].getPersonas().get(i).getDestino()) { //subir
                    if (estado.equals("S")) {
                        anadirPersona(hospital.getPlantasHospital()[getPlantaActual()].eliminarPersona(hospital.getPlantasHospital()[getPlantaActual()].getPersonas().get(i).getIdPersona()));
                    } else if (estado.equals("P") || getN_Personas() == 0) {
                        setEstado("S");
                        anadirPersona(hospital.getPlantasHospital()[getPlantaActual()].eliminarPersona(hospital.getPlantasHospital()[getPlantaActual()].getPersonas().get(i).getIdPersona()));
                    }
                } else { //bajar
                    if (estado.equals("B")) {
                        anadirPersona(hospital.getPlantasHospital()[getPlantaActual()].eliminarPersona(hospital.getPlantasHospital()[getPlantaActual()].getPersonas().get(i).getIdPersona()));
                    } else if (estado.equals("P") || getN_Personas() == 0) {
                        setEstado("B");
                        anadirPersona(hospital.getPlantasHospital()[getPlantaActual()].eliminarPersona(hospital.getPlantasHospital()[getPlantaActual()].getPersonas().get(i).getIdPersona()));
                    }
                }
            }
        }
        hospital.getPlantasHospital()[getPlantaActual()].setBotonPulsado(false);
    }

    public void salirAscensor() {
        int shift = 0;
        int x = personasDentro.size();
        for (int i = 0; i < x; i++) {
            if (personasDentro.get(i - shift).getDestino() == plantaActual) {
                eliminarPersona(personasDentro.get(i - shift).getIdPersona());
                shift++;
            }
        }
    }

    public void sacarAscensor() {
        int shift = 0;
        int x = personasDentro.size();
        for (int i = 0; i < x; i++) {
            Persona p = eliminarPersona(personasDentro.get(i - shift).getIdPersona());
            Persona p_new = new Persona(p.getIdPersona(), p.getOrigen(), p.getDestino(), hospital);
            hospital.getPlantasHospital()[plantaActual].anadirPersona(p_new);
            p_new.start();
            shift++;
        }
    }

    private void comprobarSiguienteEstado() {
        ArrayList<Integer> botones = hospital.botonesPulsados();
        switch (estado) {
            case "P":
                if (isEsperando()) {
                    setEstado("P");
                } else {
                    if (botones.isEmpty()) {
                        setEstado("P");
                    } else if (botones.size() == 1) {
                        if (botones.get(0) > getPlantaActual()) {
                            setEstado("S");
                        } else {
                            setEstado("B");
                        }
                    } else {
                        int p = hospital.getPlantasHospital().length * 3;
                        int d = hospital.getPlantasHospital().length + 1;
                        for (int i = 0; i < botones.size(); i++) {
                            int d_aux = Math.abs(plantaActual - botones.get(i));
                            if (d > d_aux) {
                                d = d_aux;
                                p = botones.get(i);
                            }
                        }
                        if (p > getPlantaActual()) {
                            setEstado("S");
                        } else {
                            setEstado("B");
                        }
                    }
                }
                break;
            case "S":
                if (getN_Personas() == 0) {
                    setEstado("P");
                } else {
                    setEstado("S");
                }
                break;
            case "B":
                if (getN_Personas() == 0) {
                    setEstado("P");
                } else {
                    setEstado("B");
                }
                break;
        }
    }

    @Override
    public void run() {
        while (hospital.getMovAscensor() < (hospital.getMax_movimientos() - 1)) {
            switch (estado) {
                case "P":
                    if(!isEsperando()){
                        salirAscensor();
                        entrarAscensor();
                    }
                    try {
                        sleep(100);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "S": 
                    try {
                        sleep(500);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    setPlantaActual(getPlantaActual() + 1);
                    salirAscensor();
                    entrarAscensor();
                    hospital.anadirMovAscensor();
                    break;
                case "B": 
                    try {
                        sleep(500);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    setPlantaActual(getPlantaActual() - 1);
                    salirAscensor();
                    entrarAscensor();
                    hospital.anadirMovAscensor();
                    break;
                case "E":
                    salirAscensor(); //pq puede que haya personas que se quieran bajar en la planta digo yo
                    sacarAscensor();
                    try {
                        sleep(new Random().nextInt(5001) + 10000);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    this.setEstado("P");
                    break;
            }
            comprobarSiguienteEstado();
        }
    }
}
