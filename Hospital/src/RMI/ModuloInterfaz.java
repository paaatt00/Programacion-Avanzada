/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import Hospital.Hospital;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Patricia
 */
public class ModuloInterfaz extends UnicastRemoteObject implements Interfaz {

    private Hospital hospital;

    public ModuloInterfaz(Hospital hospital) throws RemoteException {
        this.hospital = hospital;
    }

    public boolean evacuacion() throws RemoteException {
        hospital.evacuacion();
        return true;
    }

    public String vigilante() throws RemoteException {
        return hospital.vigilancia();
    }
}
