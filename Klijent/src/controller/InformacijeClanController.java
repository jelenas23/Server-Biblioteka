/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Communication;
import cordinator.Coordinator;
import domain.Clan;
import form.frmClan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JOptionPane;

/**
 *
 * @author Jelena
 */
public class InformacijeClanController {
private final frmClan frmClan;
private final Clan clan;
    public InformacijeClanController(frmClan frmClan, Clan clan) {
        this.frmClan = frmClan;
        this.clan=clan;
    }

    public void otvoriFormu() {
        popuniFormu();
        dodajOsluskivace();
        frmClan.getBtnSacuvaj().setEnabled(false);
        frmClan.setLocationRelativeTo(Coordinator.getInstance().getMainContoller().getFrmMain());
        frmClan.setVisible(true);
    }

    private void popuniFormu() {
        frmClan.getTxtIDClana().setText(String.valueOf(clan.getIdClana()));
        frmClan.getTxtImeClana().setText(clan.getIme());
        frmClan.getTxtPrezimeClana().setText(clan.getPrezime());
        frmClan.getTxtEmailClana().setText(clan.getEmail());
        frmClan.getTxtAdresa().setText(clan.getAdresa()); 
    }

    private void dodajOsluskivace() {
        frmClan.getBtnIzmeni().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                omoguciIzmenu();
            }

            private void omoguciIzmenu() {
                frmClan.getTxtImeClana().setEditable(false);
                frmClan.getTxtPrezimeClana().setEditable(false);
                frmClan.getTxtEmailClana().setEditable(true);
                frmClan.getTxtAdresa().setEditable(true);
                 frmClan.getBtnSacuvaj().setEnabled(true);
            }
        });
        frmClan.getBtnObrisi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sigurnoBrisanje();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmClan, "Greska prilikom brisanja clana.");
                }
            }

            private void obrisiClana() throws Exception {
              Communication.getInstance().obrisiClana(clan);
              JOptionPane.showMessageDialog(frmClan, "Clan "+ clan.getIme()+" "+clan.getPrezime()+" je uspesno obrisan.");
              frmClan.dispose();
            }

            private void sigurnoBrisanje() throws Exception {
                int odg = JOptionPane.showConfirmDialog(frmClan,"Da li sigurno zelite da obrisete clana?", null,JOptionPane.YES_NO_OPTION);
                if(odg==JOptionPane.YES_OPTION){
                    obrisiClana();
                }else{
                   
                }
            }
        });
        frmClan.getBtnSacuvaj().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    izmeniClana();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmClan,ex.getMessage());
                }
            }

            private void izmeniClana() throws Exception {
             // clan.setIme(frmClan.getTxtImeClana().getText().trim());
              //clan.setPrezime(frmClan.getTxtPrezimeClana().getText().trim());
              clan.setEmail(frmClan.getTxtEmailClana().getText().trim());
              clan.setAdresa(frmClan.getTxtAdresa().getText().trim());
              Communication.getInstance().izmeniClana(clan);
              JOptionPane.showMessageDialog(frmClan, "Uspesno ste izmenili podatke o članu.");
              frmClan.getTxtImeClana().setEditable(false);
              frmClan.getTxtPrezimeClana().setEditable(false);
              frmClan.getTxtEmailClana().setEditable(false);
              frmClan.getTxtAdresa().setEditable(false);
              frmClan.getBtnObrisi().setEnabled(true);
              frmClan.getBtnSacuvaj().setEnabled(false);
            }
        });
        frmClan.getBtnZaduzenja().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    zaduzenjaClana();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmClan, "Greska prilikom otvaranja zaduzenja.");
                }
            }

            private void zaduzenjaClana() throws Exception {
                Coordinator.getInstance().otvoriFrmZaduzenja(clan);
            }
        });
    
    }
    
}
