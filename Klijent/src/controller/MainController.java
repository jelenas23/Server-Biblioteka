/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import cordinator.Coordinator;
import domain.Bibliotekar;
import form.frmMain;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import konstante.Konstante;

/**
 *
 * @author Jelena
 */
public class MainController {
    private final frmMain frmMain;
    Bibliotekar b ;
    public  void otvoriFormu() {
        b = (Bibliotekar) Coordinator.getInstance().getParam(Konstante.trenutni_korisnik);
        frmMain.getLblKorisnik().setText(b.getIme()+" "+b.getPrezime());
        frmMain.setVisible(true);
        frmMain.setLocationRelativeTo(null);
        frmMain.setMax();
    }
    

     public MainController(frmMain frmMain) {
        this.frmMain = frmMain;
        addActionListener();
    }

    private void addActionListener() {
        
        frmMain.getJmiKreirajKnjigu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Coordinator.getInstance().otvoriKreirajKnjiguFormu();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmMain, "Greska");
                }
            }
        });
         frmMain.getJmiPretraziKnjige().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Coordinator.getInstance().otvoriPretrazivanjeKnjigeFormu();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmMain, "Greska");
                }
            }
        });
          frmMain.getJmiKreirajClana().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().otvoriKreirajClanaFormu();
            }
        });
          frmMain.getJmiPretraziClanove().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Coordinator.getInstance().otvoriPretrazivanjeClanovaFormu();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmMain, "Greska");
                }
            }
        });
          
         
    }
     public frmMain getFrmMain() {
        return frmMain;
    }
}
