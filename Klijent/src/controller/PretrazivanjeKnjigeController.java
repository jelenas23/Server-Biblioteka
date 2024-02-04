/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Communication;
import cordinator.Coordinator;
import domain.Clan;
import domain.Knjiga;
import form.frmPretrazivanjeKnjige;
import form.model.tmNadjeneKnjige;
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
public class PretrazivanjeKnjigeController {

    private final frmPretrazivanjeKnjige frmPretrazivanjeKnjige;
    
    public PretrazivanjeKnjigeController(frmPretrazivanjeKnjige frmPretrazivanjeKnjige) {
        this.frmPretrazivanjeKnjige=frmPretrazivanjeKnjige;
    }

    public void otvoriFormu() throws Exception {
       frmPretrazivanjeKnjige.setLocationRelativeTo(null);
        popuniFormu();
        frmPretrazivanjeKnjige.setVisible(true);
        frmPretrazivanjeKnjige.getBtnDetalji().setEnabled(true);
        dodajOsluskivace();
    }

    private void popuniFormu() throws Exception {
        List<Knjiga> knjige=Communication.getInstance().vratiKnjige();
        tmNadjeneKnjige model=new tmNadjeneKnjige(knjige);
        frmPretrazivanjeKnjige.getTblNadjeneKnjige().setModel(model);
       frmPretrazivanjeKnjige.getTblNadjeneKnjige().setCellSelectionEnabled(false);
        frmPretrazivanjeKnjige.getTblNadjeneKnjige().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       frmPretrazivanjeKnjige.getTblNadjeneKnjige().setRowSelectionAllowed(true);
        frmPretrazivanjeKnjige.getTblNadjeneKnjige().setFocusable(true);
        dodajOsluskivace();
    }

    private void dodajOsluskivace() {
        frmPretrazivanjeKnjige.getBtnPretrazi().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                pretraziKnjigu();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(frmPretrazivanjeKnjige, ex.getMessage());
            }
            
            
            
        }

        private void pretraziKnjigu() throws Exception{
            String naziv  =frmPretrazivanjeKnjige.getTxtPretrazivanje().getText().trim();
            System.out.println(naziv + "je naziv unetog kriterijuma");
            Knjiga k=new Knjiga();
            k.setNazivKnjige(naziv);
            System.out.println(k + "iz objekta knjiga");
            List<Knjiga> knjige= Communication.getInstance().vratiKnjigePoUslovu(k);
            tmNadjeneKnjige model=new tmNadjeneKnjige(knjige);
            frmPretrazivanjeKnjige.getTblNadjeneKnjige().setModel(model);
            if(!knjige.isEmpty()) JOptionPane.showMessageDialog(frmPretrazivanjeKnjige, "Sistem je našao knjige po zadatoj vrednosti.");
            else {
                throw new Exception("Sistem ne može da nadje knjige po zadatoj vrednosti.");
            }
        }
    });
        frmPretrazivanjeKnjige.getBtnDetalji().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    detaljiKnjiga();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmPretrazivanjeKnjige, ex.getMessage());
                }
        }

            private void detaljiKnjiga() throws Exception {
                int selektovaniRed = frmPretrazivanjeKnjige.getTblNadjeneKnjige().getSelectedRow();
                if(frmPretrazivanjeKnjige.getTblNadjeneKnjige().isRowSelected(selektovaniRed)){
                tmNadjeneKnjige tm = (tmNadjeneKnjige) frmPretrazivanjeKnjige.getTblNadjeneKnjige().getModel();
                Knjiga knjiga  = tm.getKnjigaAt(selektovaniRed);
                Coordinator.getInstance().otvoriFrmKnjiga(knjiga);
                }else{
                throw new Exception("Morate selektovati red u tabeli!");
                }
            }
            
            
            
        });
    }
    
}
