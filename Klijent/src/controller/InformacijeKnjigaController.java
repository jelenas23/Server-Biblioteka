/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Communication;
import cordinator.Coordinator;
import domain.Knjiga;
import form.frmKnjiga;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;



/**
 *
 * @author Jelena
 */
public class InformacijeKnjigaController{
    private final frmKnjiga frmKnjiga;
    private final Knjiga knjiga;

    public InformacijeKnjigaController(frmKnjiga frmKnjiga, Knjiga knjiga) {
        this.frmKnjiga=frmKnjiga;
        this.knjiga=knjiga;
    }

    public void otvoriFormu() {
        popuniFormu();
        dodajOsluskivace();
        frmKnjiga.getBtnSacuvaj().setEnabled(false);
        frmKnjiga.setLocationRelativeTo(Coordinator.getInstance().getMainContoller().getFrmMain());
        JOptionPane.showMessageDialog(frmKnjiga, "Podaci o izabranoj knjizi.");
        frmKnjiga.setVisible(true);
        
    }

    private void popuniFormu() {
        frmKnjiga.getTxtIDknjige().setText(String.valueOf(knjiga.getIdKnjige()));
        frmKnjiga.getTxtNazivKnjige().setText(String.valueOf(knjiga.getNazivKnjige()));
        frmKnjiga.getTxtGodIzdanja().setText(String.valueOf(knjiga.getGodinaIzdanja()));
        frmKnjiga.getTxtPolica().setText(String.valueOf(knjiga.getPolica()));
        frmKnjiga.getTxtBrPrimeraka().setText(String.valueOf(knjiga.getBrPrimeraka()));
        frmKnjiga.getTxtAutor().setText(knjiga.getAutor().getIme()+" "+knjiga.getAutor().getPrezime());
        frmKnjiga.getTxtZanr().setText(knjiga.getZanr().getNazivZanra());
        
    }

    private void dodajOsluskivace() {
        frmKnjiga.getBtnIzmeni().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                omoguciIzmenu();
            }

            private void omoguciIzmenu() {
                frmKnjiga.getTxtIDknjige().setEditable(false);
                frmKnjiga.getTxtNazivKnjige().setEditable(false);
                frmKnjiga.getTxtAutor().setEditable(false);
                frmKnjiga.getTxtZanr().setEditable(false);
                frmKnjiga.getTxtBrPrimeraka().setEditable(true);
                frmKnjiga.getTxtPolica().setEditable(true);
                frmKnjiga.getTxtGodIzdanja().setEditable(false);
                frmKnjiga.getBtnSacuvaj().setEnabled(true);
            }
        });
        frmKnjiga.getBtnSacuvaj().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    izmeniKnjigu();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmKnjiga, ex.getMessage());
                }
            }

            private void izmeniKnjigu() throws Exception {
                knjiga.setBrPrimeraka(Integer.parseInt(frmKnjiga.getTxtBrPrimeraka().getText().trim()));
                knjiga.setPolica(frmKnjiga.getTxtPolica().getText().trim());
                Communication.getInstance().izmeniKnjigu(knjiga);
                JOptionPane.showMessageDialog(frmKnjiga, "Uspesno ste izmenili podatke o knjizi.");
                 frmKnjiga.getTxtIDknjige().setEditable(false);
                frmKnjiga.getTxtNazivKnjige().setEditable(false);
                frmKnjiga.getTxtAutor().setEditable(false);
                frmKnjiga.getTxtZanr().setEditable(false);
                frmKnjiga.getTxtBrPrimeraka().setEditable(false);
                frmKnjiga.getTxtPolica().setEditable(false);
                frmKnjiga.getTxtGodIzdanja().setEditable(false);
                frmKnjiga.getBtnSacuvaj().setEnabled(false);
                
            }
        });
        frmKnjiga.getBtnObrisi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 try {
                    sigurnoBrisanje();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmKnjiga, ex.getMessage());
                }
            }

            private void sigurnoBrisanje() throws Exception {
                int odg = JOptionPane.showConfirmDialog(frmKnjiga,"Da li sigurno zelite da obrisete knjigu?", null,JOptionPane.YES_NO_OPTION);
                if(odg==JOptionPane.YES_OPTION){
                    obrisiKnjigu();
                }
            }

            private void obrisiKnjigu() throws Exception {
               Communication.getInstance().obrisiKnjigu(knjiga);
               JOptionPane.showMessageDialog(frmKnjiga, "Knjiga "+ knjiga.getNazivKnjige()+" je uspesno obrisana.");
               frmKnjiga.dispose();
            }
        });
    }
}
