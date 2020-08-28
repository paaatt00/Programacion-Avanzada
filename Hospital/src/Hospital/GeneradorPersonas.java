/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital;

import java.util.Random;

/**
 *
 * @author Patricia
 */
public class GeneradorPersonas extends Thread {

    private Hospital hospital;

    public GeneradorPersonas(Hospital hospital) {
        this.hospital = hospital;
    }

    @Override
    public void run() {
        for (int i = 0; i < 6000; i++) {
            int origen = new Random().nextInt(21);
            Persona p = new Persona(i, origen, hospital);
            try {
                sleep(new Random().nextInt(1501) + 500);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
