/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Communication;
import cordinator.Coordinator;
import domain.Clan;
import form.frmPretrazivanjeClana;
import form.model.tmNadjeniClanovi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Jelena
 */
public class PretrazivanjeClanovaController {

 private final frmPretrazivanjeClana frmPretrazivanjeClana;

    public PretrazivanjeClanovaController(frmPretrazivanjeClana frmPretrazivanjeClana) {
        this.frmPretrazivanjeClana = frmPretrazivanjeClana;
    }

    public void otvoriFormu() throws Exception {
        frmPretrazivanjeClana.setLocationRelativeTo(null);
        popuniFormu();
        frmPretrazivanjeClana.getTblNadjeniClanovi().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        frmPretrazivanjeClana.setVisible(true);
        
    }

    private void popuniFormu() throws Exception{
        List<Clan> clanovi=Communication.getInstance().vratiClanove();
        tmNadjeniClanovi model=new tmNadjeniClanovi(clanovi);
        
        frmPretrazivanjeClana.getTblNadjeniClanovi().setModel(model);
        dodajOsluskivace();
    }

    private void dodajOsluskivace() {
    frmPretrazivanjeClana.getBtnPretrazi().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                pretraziClana();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(frmPretrazivanjeClana, ex.getMessage());
            }
            
            
            
        }

        private void pretraziClana() throws Exception{
            String ime  =frmPretrazivanjeClana.getTxtIme().getText().trim();
            String prezime  =frmPretrazivanjeClana.getTxtPrezime().getText().trim();
            Clan clan = new Clan();
            clan.setIme(ime);
            clan.setPrezime(prezime);
            List<Clan> clanovi= Communication.getInstance().vratiClanovePoUslovu(clan);
            tmNadjeniClanovi model=new tmNadjeniClanovi(clanovi);
            frmPretrazivanjeClana.getTblNadjeniClanovi().setModel(model);
            if(!clanovi.isEmpty()) JOptionPane.showMessageDialog(frmPretrazivanjeClana, "Sistem je našao članove po zadatoj vrednosti.");
            else throw new Exception("Sistem ne može da nadje članove po zadatoj vrednosti.");
        }
    });
    frmPretrazivanjeClana.getBtnDetalji().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                detaljiClan();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frmPretrazivanjeClana, ex.getMessage());
            }
            
        }

        private void detaljiClan() throws Exception {
            int selektovaniRed = frmPretrazivanjeClana.getTblNadjeniClanovi().getSelectedRow();
            if(frmPretrazivanjeClana.getTblNadjeniClanovi().isRowSelected(selektovaniRed)){
                tmNadjeniClanovi tm = (tmNadjeniClanovi) frmPretrazivanjeClana.getTblNadjeniClanovi().getModel();
                Clan clan  = tm.getClanAt(selektovaniRed);
                Coordinator.getInstance().otvoriFrmClan(clan);
            }else{
                 throw new Exception("Morate selektovati red u tabeli!");
             }
            
        }
    });
    }
      
}
