package Hospital;

import java.util.ArrayList;
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

    private int n_plantas = 20;
    private int n_ascensores = 3;
    private int movAscensor = 0;
    private ArrayList<Integer> llamadaAscensores; //guarda desde donde llaman al ascensor
    private Planta plantasHospital[] = new Planta[n_plantas + 1]; //lista de plantas con la lista de pesonas que están cada planta
    private Ascensor ascensores[] = new Ascensor[n_ascensores];
    private ReentrantLock lockMovAscensor = new ReentrantLock();

    public Hospital() {
        for (int i = 0; i < n_plantas + 1; i++) {
            plantasHospital[i] = new Planta(i);
        }
        for (int i = 0; i < n_ascensores; i++) {
            ascensores[i] = new Ascensor(i);
        }
        /*
        ascensores[0] = new Ascensor(1, 3);
        ascensores[1] = new Ascensor(2, 5);
        ascensores[2] = new Ascensor(3, 8);
         */
        imprimir();
    }

    public int getMovAscensor() {
        int m;
        lockMovAscensor.lock();
        try {
            m = movAscensor;
        } finally {
            lockMovAscensor.unlock();
        }
        return m;
    }

    public void anadirMovAscensor() {
        lockMovAscensor.lock();
        try {
            movAscensor = movAscensor++;
        } finally {
            lockMovAscensor.unlock();
        }
    }

    public Ascensor[] getAscensores() {
        return ascensores;
    }

    public Planta[] getPlantasHospital() {
        return plantasHospital;
    }
    
    //cada vez que se llegue a la planta más alta o más baja el ascensor tiene que modificar el valor de las variables de la planta más alta y más baja
    public synchronized void pulsarBoton(int idPlanta) {
        plantasHospital[idPlanta].setBotonPulsado(true);
        for (int i = 0; i < n_ascensores; i++) {
            if (ascensores[i].getEstado().equals("P")) {
                if (ascensores[i].getPlantaActual() > idPlanta) {
                    ascensores[i].setEstado("B");
                } else if (ascensores[i].getPlantaActual() < idPlanta) {
                    ascensores[i].setEstado("S");
                }
            }
        }
    }

    public int comprobarAscensor(int idPlanta) { //utilizar locks
        for (int i = 0; i < n_ascensores; i++) {
            if (ascensores[i].getPlantaActual() == idPlanta) {
                return i;
            }
        }
        return -1;
    }

    public void imprimir() {
        System.out.println("Piso    Asc.1    Asc.2    Asc.3    Botón pulsado:    Destinos Asc.1    Destinos Asc.2    Destinos Asc.3    Personas planta");
        for (int i = n_plantas; i >= 0; i--) {
            System.out.print(" ");
            if (plantasHospital[i].getIdPlanta() < 10) {
                System.out.print(" ");
            }
            System.out.print(plantasHospital[i].getIdPlanta() + "      ");
            for (int j = 0; j < n_ascensores; j++) {
                if (ascensores[j].getPlantaActual() == plantasHospital[i].getIdPlanta()) {
                    if (ascensores[j].getEstado().equals("E")) {
                        System.out.print(" E ");
                    } else {
                        System.out.print(ascensores[j].getEstado() + "#" + ascensores[j].getN_personas());
                    }
                } else {
                    System.out.print(" | ");
                }
                System.out.print("      ");
            }
            if (plantasHospital[i].isBotonPulsado() == true) {
                System.out.print("     Sí");
            } else {
                System.out.print("     No");
            }
            System.out.print("          ");
            for (int j = 0; j < n_ascensores; j++) {
                ArrayList<Persona> personasAsc = new ArrayList<>();
                for (int k = 0; k < ascensores[j].getPersonasDentro().size(); k++) {
                    if (ascensores[j].getPersonasDentro().get(k).getDestino() == i) {
                        personasAsc.add(ascensores[j].getPersonasDentro().get(k));
                    }
                }
                int n_espacios = 7;
                if (personasAsc.size() > 1) {
                    n_espacios = Math.max((14 - (personasAsc.size() * String.valueOf(ascensores[j].getPersonasDentro().get(0).getIdPersona()).length() + (personasAsc.size() - 1) * 2)) / 2, 0);
                } else if (personasAsc.size() == 1) {
                    n_espacios = (14 - personasAsc.size() * String.valueOf(ascensores[j].getPersonasDentro().get(0).getIdPersona()).length()) / 2;
                }
                for (int k = 0; k < n_espacios; k++) {
                    System.out.print(" ");
                }
                for (int k = 0; k < personasAsc.size(); k++) {
                    System.out.print("P" + personasAsc.get(k).getIdPersona());
                    if (personasAsc.size() - 1 != k) {
                        System.out.print(", ");
                    }
                }
                for (int k = 0; k < n_espacios; k++) {
                    System.out.print(" ");
                }
                System.out.print("    ");
            }
            for (int j = 0; j < plantasHospital[i].getPersonas().size(); j++) {
                System.out.print("P" + plantasHospital[i].getPersonas().get(j).getIdPersona());
                if (plantasHospital[i].getPersonas().size() - 1 != j) {
                    System.out.print(", ");
                }
            }
            System.out.println("");
        }
    }
}
