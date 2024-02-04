/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Communication;

import domain.Clan;

import form.frmKreiranjeClana;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jelena
 */
public class KreiranjeClanaController {
    private final frmKreiranjeClana frmKreiranjeClana;
    Clan clan;
    public KreiranjeClanaController(frmKreiranjeClana frmKreiranjeClana) {
        this.frmKreiranjeClana = frmKreiranjeClana;
    }
    public void otvoriFormu() {
        frmKreiranjeClana.setLocationRelativeTo(null);
        dodajOsluskivace();
        frmKreiranjeClana.setVisible(true);
        
    }

    private void dodajOsluskivace() {
        frmKreiranjeClana.getBtnSacuvaj().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sacuvajClana();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmKreiranjeClana,ex.getMessage());
                }
            }

            private void sacuvajClana()throws Exception{
                String ime = frmKreiranjeClana.getTxtImeClana().getText();
                String prezime = frmKreiranjeClana.getTxtPrezimeClana().getText();
                String adresa  =frmKreiranjeClana.getTxtAdresa().getText();
                String email = frmKreiranjeClana.getTxtEmail().getText();
                if(ime.equals("") || prezime.equals("") || adresa.equals("") || email.equals("")){
                     throw new Exception("Sva polja moraju biti popunjena!");
                }
                if(!email.contains("@")) {
                throw new Exception("Email mora sadrzati znak '@'");
                }else  { 
                    clan = new Clan(ime,prezime,adresa,email);
                    clan.setEmail(email);
                    Communication.getInstance().dodajClana(clan);
                    JOptionPane.showMessageDialog(frmKreiranjeClana, "Član je sačuvan.");
                    nastavak();
                }
                
            }

            private void nastavak() {
                int odg = JOptionPane.showConfirmDialog(frmKreiranjeClana,"Da li zelite da dodate novog clana?", null,JOptionPane.YES_NO_OPTION);
                if(odg==JOptionPane.YES_OPTION){
                    frmKreiranjeClana.getTxtImeClana().setText("");
                    frmKreiranjeClana.getTxtPrezimeClana().setText("");
                    frmKreiranjeClana.getTxtAdresa().setText("");
                    frmKreiranjeClana.getTxtEmail().setText("");
                }else{
                    frmKreiranjeClana.dispose();
                }
                
            }
        });
    }
}
