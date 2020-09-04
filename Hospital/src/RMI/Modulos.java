
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.Naming;

/**
 *
 * @author patri
 */
public class Modulos extends javax.swing.JFrame {

    private Interfaz obj;

    /**
     * Creates new form Evacuacion
     */
    public Modulos() {
        initComponents();
        try {
            obj = (Interfaz) Naming.lookup("//127.0.0.1/Modulos");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonEvacuacion = new javax.swing.JButton();
        botonVigilancia = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 0, 0));

        botonEvacuacion.setBackground(new java.awt.Color(255, 0, 0));
        botonEvacuacion.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        botonEvacuacion.setForeground(new java.awt.Color(255, 255, 255));
        botonEvacuacion.setText("¡EVACUACIÓN!");
        botonEvacuacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEvacuacionActionPerformed(evt);
            }
        });

        botonVigilancia.setBackground(new java.awt.Color(153, 0, 0));
        botonVigilancia.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        botonVigilancia.setForeground(new java.awt.Color(255, 255, 255));
        botonVigilancia.setText("VIGILAR");
        botonVigilancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVigilanciaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(botonEvacuacion, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(botonVigilancia, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botonVigilancia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonEvacuacion, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonEvacuacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEvacuacionActionPerformed
        // TODO add your handling code here:
        boolean ev;
        try {
            ev = obj.evacuacion();
            if (ev) {
                System.out.println("Evacuando hospital.");
            } else {
                System.out.println("Algo falla");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }//GEN-LAST:event_botonEvacuacionActionPerformed

    private void botonVigilanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVigilanciaActionPerformed
        // TODO add your handling code here:
        try {
            System.out.println(obj.vigilante());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_botonVigilanciaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Modulos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEvacuacion;
    private javax.swing.JButton botonVigilancia;
    // End of variables declaration//GEN-END:variables

}