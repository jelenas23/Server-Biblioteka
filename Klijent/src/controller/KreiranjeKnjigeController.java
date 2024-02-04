/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Communication;
import domain.Autor;
import domain.Knjiga;
import domain.Zanr;
import form.frmKreiranjeKnjige;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Jelena
 */
public class KreiranjeKnjigeController {

    private final frmKreiranjeKnjige frmKreiranjeKnjige;
    Knjiga knjiga;
    public KreiranjeKnjigeController(frmKreiranjeKnjige frmKreiranjeKnjige) {
        this.frmKreiranjeKnjige=frmKreiranjeKnjige;
    }

    public void otvoriFormu() throws Exception {
        frmKreiranjeKnjige.setLocationRelativeTo(null);
        popuniCombo();
        dodajOsluskivace();
        frmKreiranjeKnjige.setVisible(true);
    }

    private void dodajOsluskivace() {
        frmKreiranjeKnjige.getBtnSacuvaj().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    sacuvajKnjigu();
                }catch(Exception ex){
                     JOptionPane.showMessageDialog(frmKreiranjeKnjige,ex.getMessage());
                }
                
            }

            private void sacuvajKnjigu() throws Exception {
                String naziv = frmKreiranjeKnjige.getTxtNazivKnjige().getText().trim();
                Autor autor = (Autor) frmKreiranjeKnjige.getCmbAutori().getSelectedItem();
                Zanr zanr=(Zanr) frmKreiranjeKnjige.getCmbZanrovi().getSelectedItem();
                String polica = frmKreiranjeKnjige.getTxtPolica().getText().trim();
                int brPrimeraka=Integer.parseInt(frmKreiranjeKnjige.getTxtBrPrimeraka().getText());
                int godIzadanja = Integer.parseInt(frmKreiranjeKnjige.getTxtGodIzdanja().getText());
                Knjiga novaKnjiga = new Knjiga(naziv,brPrimeraka,autor,zanr,polica,godIzadanja);
                Communication.getInstance().dodajKnjigu(novaKnjiga);
                JOptionPane.showMessageDialog(frmKreiranjeKnjige, "Knjiga je sačuvana.");
                nastavak();
            }

            private void nastavak() {
                int odg = JOptionPane.showConfirmDialog(frmKreiranjeKnjige,"Da li zelite da kreirate novu knjigu?", null,JOptionPane.YES_NO_OPTION);
                if(odg==JOptionPane.YES_OPTION){
                    frmKreiranjeKnjige.getTxtBrPrimeraka().setText("");
                    frmKreiranjeKnjige.getTxtGodIzdanja().setText("");
                    frmKreiranjeKnjige.getTxtNazivKnjige().setText("");
                    frmKreiranjeKnjige.getTxtPolica().setText("");
                    frmKreiranjeKnjige.getCmbAutori().setSelectedIndex(-1);
                    frmKreiranjeKnjige.getCmbZanrovi().setSelectedIndex(-1);
                }else{
                    frmKreiranjeKnjige.dispose();
                }
                
            }
          });
        }
        
         private void popuniCombo() throws Exception {
        frmKreiranjeKnjige.getCmbZanrovi().setModel(new DefaultComboBoxModel(Communication.getInstance().vratiZanrove().toArray()));
        frmKreiranjeKnjige.getCmbAutori().setModel(new DefaultComboBoxModel(Communication.getInstance().vratiAutore().toArray()));
        frmKreiranjeKnjige.getCmbAutori().setSelectedIndex(-1);
        frmKreiranjeKnjige.getCmbZanrovi().setSelectedIndex(-1);
    }
}

   
    

