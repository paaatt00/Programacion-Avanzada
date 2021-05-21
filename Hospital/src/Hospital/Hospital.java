package Hospital;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
    private int max_movimientos = 6000;
    private Planta plantasHospital[] = new Planta[n_plantas + 1]; //lista de plantas con la lista de pesonas que están cada planta
    private Ascensor ascensores[] = new Ascensor[n_ascensores];
    private ReentrantLock lockMovAscensor = new ReentrantLock();
    private ReentrantLock lockTxt = new ReentrantLock();
    private ReentrantLock lockEvacuacion = new ReentrantLock();
    private FileWriter archivo;
    private BufferedWriter bw;
    private boolean esEvacuado = false;

    public Hospital() throws IOException {
        for (int i = 0; i < n_plantas + 1; i++) {
            plantasHospital[i] = new Planta(i);
        }
        for (int i = 0; i < n_ascensores - 1; i++) {
            ascensores[i] = new Ascensor(i, false, this);
            ascensores[i].start();
        }
        ascensores[n_ascensores - 1] = new Ascensor(n_ascensores - 1, true, this);
        ascensores[n_ascensores - 1].start();
        Rompedor r = new Rompedor(this);
        r.start();
        String ruta = "archivo.txt";
        archivo = new FileWriter(ruta);
        bw = new BufferedWriter(archivo);
        imprimir();
    }

    public synchronized int getMax_movimientos() {
        return max_movimientos;
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
            movAscensor++;
            imprimir();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            lockMovAscensor.unlock();
        }
    }

    public Ascensor[] getAscensores() {
        return ascensores;
    }

    public synchronized Planta[] getPlantasHospital() {
        return plantasHospital;
    }

    public boolean isEsEvacuado() {
        boolean e;
        lockEvacuacion.lock();
        try {
            e = esEvacuado;
        } finally {
            lockEvacuacion.unlock();
        }
        return e;
    }

    public void setEsEvacuado(boolean esEvacuado) {
        lockEvacuacion.lock();
        try {
            this.esEvacuado = esEvacuado;
        } finally {
            lockEvacuacion.unlock();
        }
    }

    public void cerrar() {
        lockTxt.lock();
        try {
            bw.close();
            for (int i = 0; i < n_plantas; i++) {
                for (int j = 0; j < this.getPlantasHospital()[i].getPersonas().size(); j++) {
                    this.getPlantasHospital()[i].getPersonas().get(j).setEnAscensor(true);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            lockTxt.unlock();
        }
    }

    public synchronized void pulsarBoton(int idPlanta) {
        plantasHospital[idPlanta].setBotonPulsado(true);
    }

    public ArrayList<Integer> botonesPulsados() {
        ArrayList<Integer> botones = new ArrayList<>();
        for (int i = 0; i < n_plantas + 1; i++) {
            if (plantasHospital[i].isBotonPulsado()) {
                botones.add(i);
            }
        }
        return botones;
    }

    public void evacuacion() {
        setEsEvacuado(true);
        for (int i = 0; i < n_plantas; i++) {
            for (int j = 0; j < plantasHospital[i].getPersonas().size(); j++) {
                plantasHospital[i].getPersonas().get(j).setDestino(0);
                if (i == 0) {
                    plantasHospital[i].getPersonas().get(j).setEnAscensor(true);
                }
            }
        }
        plantasHospital[0].eliminarPersonas();
    }

    public String vigilancia() {
        String x = "";
        x = x + "______________________________MODULO VIGILANTE____________________________________\n";
        for (int i = 0; i < n_ascensores; i++) {
            x = x + "Ascensor: " + getAscensores()[i].getIdAscensor() + "    Estado: " + getAscensores()[i].getEstado() + "    Nº personas: " + getAscensores()[i].getN_Personas() + "  |  Planta Actual: " + getAscensores()[i].getPlantaActual() + "\n";
            x = x + "// PERSONAS DENTRO \n";
            for (int j = 0; j < getAscensores()[i].getPersonasDentro().size(); j++) {
                x = x + getAscensores()[i].getPersonasDentro().get(j).getIdPersona() + "    Origen: " + getAscensores()[i].getPersonasDentro().get(j).getOrigen() + "    Destino: " + getAscensores()[i].getPersonasDentro().get(j).getDestino() + "\n";
            }
            x = x + "_____________________________________\n";
        }
        for (int i = n_plantas; i >= 0; i--) { //recorremos al reves
            x = x + "____ " + i + " ____\n";
            for (int j = 0; j < getPlantasHospital()[i].getPersonas().size(); j++) {
                Persona p = getPlantasHospital()[i].getPersonas().get(j);
                x = x + p.getIdPersona() + "    Origen: " + p.getOrigen() + "    Destino: " + p.getDestino() + "\n";
            }
            x = x + "\n";
        }
        x = x + "________________________________________________________________________________________________________\n\n";
        return x;
    }

    public boolean ascensoresVacios() {
        for (int i = 0; i < n_ascensores; i++) {
            if (!ascensores[i].getPersonasDentro().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void imprimir() {
        lockTxt.lock();
        try {
            System.out.println("Movimiento " + getMovAscensor() + ":");
            System.out.println("Piso    Asc.1    Asc.2    Asc.3    Botón pulsado:    Destinos Asc.1    Destinos Asc.2    Destinos Asc.3    Personas planta");
            bw.write("Movimiento " + getMovAscensor() + ":\n");
            bw.write("Piso    Asc.1    Asc.2    Asc.3    Botón pulsado:    Destinos Asc.1    Destinos Asc.2    Destinos Asc.3    Personas planta\n");
            for (int i = n_plantas; i >= 0; i--) {
                System.out.print(" ");
                bw.write(" ");
                if (plantasHospital[i].getIdPlanta() < 10) {
                    System.out.print(" ");
                    bw.write(" ");
                }
                System.out.print(plantasHospital[i].getIdPlanta() + "      ");
                bw.write(plantasHospital[i].getIdPlanta() + "      ");
                for (int j = 0; j < n_ascensores; j++) {
                    if (ascensores[j].getPlantaActual() == plantasHospital[i].getIdPlanta()) {
                        if (ascensores[j].getEstado().equals("E")) {
                            System.out.print(" E ");
                            bw.write(" E ");
                        } else {
                            System.out.print(ascensores[j].getEstado() + "#" + ascensores[j].getN_Personas());
                            bw.write(ascensores[j].getEstado() + "#" + ascensores[j].getN_Personas());
                        }
                    } else {
                        System.out.print(" | ");
                        bw.write(" | ");
                    }
                    System.out.print("      ");
                    bw.write("      ");
                }
                if (plantasHospital[i].isBotonPulsado() == true) {
                    System.out.print("   --Sí");
                    bw.write("   --Sí");
                } else {
                    System.out.print("     No");
                    bw.write("     No");
                }
                System.out.print("          ");
                bw.write("          ");
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
                        bw.write(" ");
                    }
                    for (int k = 0; k < personasAsc.size(); k++) {
                        System.out.print("P" + personasAsc.get(k).getIdPersona());
                        bw.write("P" + personasAsc.get(k).getIdPersona());
                        if (personasAsc.size() - 1 != k) {
                            System.out.print(", ");
                            bw.write(", ");
                        }
                    }
                    for (int k = 0; k < n_espacios; k++) {
                        System.out.print(" ");
                        bw.write(" ");
                    }
                    System.out.print("    ");
                    bw.write("    ");
                }
                System.out.print("  ");
                bw.write("  ");
                for (int j = 0; j < plantasHospital[i].getPersonas().size(); j++) {
                    System.out.print("P" + plantasHospital[i].getPersonas().get(j).getIdPersona());
                    bw.write("P" + plantasHospital[i].getPersonas().get(j).getIdPersona());
                    if (plantasHospital[i].getPersonas().size() - 1 != j) {
                        System.out.print(", ");
                        bw.write(", ");
                    }
                }
                System.out.println("");
                bw.write("\n");
            }
            for (int i = 0; i < n_ascensores; i++) {
                System.out.print("Ascensor " + (i + 1) + ": [");
                bw.write("Ascensor " + (i + 1) + ": [");
                for (int j = 0; j < this.getAscensores()[i].getN_Personas(); j++) {
                    System.out.print("P" + this.getAscensores()[i].getPersonasDentro().get(j).getIdPersona());
                    bw.write("P" + this.getAscensores()[i].getPersonasDentro().get(j).getIdPersona());
                    if (this.getAscensores()[i].getN_Personas() - 1 != j) {
                        System.out.print(", ");
                        bw.write(", ");
                    }
                }
                System.out.println("]");
                bw.write("]\n");
            }
            System.out.println("");
            bw.write("\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            lockTxt.unlock();
        }
    }
}
