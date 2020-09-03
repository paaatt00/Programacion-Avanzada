/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import Hospital.Hospital;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author patri
 */
public class Servidor {
    
    private Hospital hospital; 

    public Servidor(Hospital hospital) {
        this.hospital = hospital;
        try {
            ModuloInterfaz obj = new ModuloInterfaz(hospital);
            Registry registry = LocateRegistry.createRegistry(1099);
            Naming.rebind("//127.0.0.1/Modulos", obj);
        } catch (Exception e) {
            System.out.println(e.getMessage()); 
        }
    }
}
