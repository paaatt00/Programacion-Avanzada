package Hospital;

import RMI.Servidor;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Patricia
 */
public class Main {

    /**
     * @param args the command line arguments
     */ 
    public static void main(String[] args) throws IOException, InterruptedException{

        Hospital hospital = new Hospital();
        GeneradorPersonas generador = new GeneradorPersonas(hospital);
        generador.start();
        Servidor ser = new Servidor(hospital);
    }
}
