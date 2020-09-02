/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

/**
 *
 * @author patri
 */
public class Rompedor extends Thread {

    private Hospital hospital;

    public Rompedor(Hospital hospital) {
        this.hospital = hospital;
    }

    private void activarAscensores(int id) {
        for (int i = 0; i < hospital.getAscensores().length; i++) {
            if (hospital.getAscensores()[i].getIdAscensor() == id) {
                hospital.getAscensores()[i].setEsperando(true);
                hospital.getAscensores()[i].setEstado("E");
            } else {
                hospital.getAscensores()[i].setEsperando(false);
            }
        }
    }
    
    @Override
    public void run() {
        while (hospital.getMovAscensor() < hospital.getMax_movimientos()) {
            try {
                sleep(new Random().nextInt(10001) + 20000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            int a = new Random().nextInt(hospital.getAscensores().length);
            while (hospital.getAscensores()[a].isEsperando()) {
                a = new Random().nextInt(hospital.getAscensores().length);
            }
            activarAscensores(a);
        }
        hospital.cerrar();
        System.out.println("---------------------------");
    }
}
