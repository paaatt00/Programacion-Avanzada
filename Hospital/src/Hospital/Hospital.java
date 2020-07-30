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
public class Hospital {

    private int num_mov;
    private int cont_mov;
    private ArrayList<Integer> llamada_ascensores; //guarda desde donde llaman al ascensor
    private ArrayList<ArrayList<Persona>> plantas_hospital; //lista de plantas con la lista de pesonas que están cada planta

    public Hospital(int num_mov, String cont_mov) {
        this.num_mov = 5000;
        this.cont_mov = 0;
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

}
