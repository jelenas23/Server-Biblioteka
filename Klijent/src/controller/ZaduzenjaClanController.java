/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDateChooserCellEditor;
import com.toedter.calendar.demo.DateChooserPanel;
import communication.Communication;
import domain.Clan;
import domain.Knjiga;
import domain.Status;
import domain.Zaduzenje;
import form.frmZaduzenja;
import form.model.tmZaduzenja;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

/**
 *
 * @author Jelena
 */
public class ZaduzenjaClanController {
    //srediti da kolona ne bude editable ako se stavi datum od danasnji da bude
    private final frmZaduzenja frmZaduzenja;
    private final Clan clan;
    Map<Integer, Knjiga> map = new HashMap<>();
    List<Zaduzenje> zaduzenja = new ArrayList<>();
    List<Zaduzenje> nova = new ArrayList<>();
    public ZaduzenjaClanController(frmZaduzenja frmZaduzenja, Clan clan) {
        this.frmZaduzenja = frmZaduzenja;
        this.clan = clan;
    }

    public void otvoriFormu() throws Exception {
        frmZaduzenja.setLocationRelativeTo(null);
        popuniFormu();
        frmZaduzenja.getTblZaduzenja().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        frmZaduzenja.setVisible(true);
        dodajOsluskivace();
    }

    private void popuniFormu() throws Exception {
        frmZaduzenja.getLblIme().setText(clan.getIme());
        frmZaduzenja.getLblPrezime().setText(clan.getPrezime());
        frmZaduzenja.getLblEmail().setText(clan.getEmail());
        zaduzenja=Communication.getInstance().vratiZaduzenja(clan);
        
        List<Knjiga> knjige = Communication.getInstance().vratiKnjige();
        knjigeUMapu(knjige);
        //postavljanje knjige u zaduzenje
        if(!zaduzenja.isEmpty()){
            for(Zaduzenje z:zaduzenja){
                Knjiga k = map.get(z.getKnjiga().getIdKnjige());
                
                z.setKnjiga(k);
            }
        }
        
        tmZaduzenja model=new tmZaduzenja(zaduzenja,knjige);
        frmZaduzenja.getTblZaduzenja().setModel(model);
        dodajOsluskivace();
         if(zaduzenja.size()==0) JOptionPane.showMessageDialog(frmZaduzenja, "Za clana nije kreirano nijedno zaduzenje.");
        //dodavanje combo boxeva i date pikera
        TableColumn tcNazivKnjige=frmZaduzenja.getTblZaduzenja().getColumnModel().getColumn(0);
        JComboBox cbKnjige=new JComboBox(knjige.toArray());
        tcNazivKnjige.setCellEditor(new DefaultCellEditor(cbKnjige));
        
       TableColumn tcDatumOd = frmZaduzenja.getTblZaduzenja().getColumnModel().getColumn(1);
        tcDatumOd.setCellEditor(new JDateChooserCellEditor()); 
        
        TableColumn tcDatumDo = frmZaduzenja.getTblZaduzenja().getColumnModel().getColumn(2);
        tcDatumDo.setCellEditor(new JDateChooserCellEditor()); 
        
    }

    private void dodajOsluskivace() {
        frmZaduzenja.getBtnZaduzi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              tmZaduzenja model  = (tmZaduzenja) frmZaduzenja.getTblZaduzenja().getModel();
              model.setSignalZaZaduzi(true);
              model.setSignalZaRazduzi(false);
              Zaduzenje novo = new Zaduzenje();
              novo.setClan(clan);
             // novo.setStatus(Status.INSERT);
              model.addZaduzenje(novo);
              nova.add(novo);
            }
        });
        frmZaduzenja.getBtnRazduzi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              tmZaduzenja model  = (tmZaduzenja) frmZaduzenja.getTblZaduzenja().getModel();
              model.setSignalZaRazduzi(true);
              model.setSignalZaZaduzi(false);
              
            }
        });
        frmZaduzenja.getBtnSacuvaj().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sacuvajIzmene();
                    frmZaduzenja.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmZaduzenja, ex.getMessage());
                }
            }

            private void sacuvajIzmene() throws Exception {
             tmZaduzenja model  = (tmZaduzenja) frmZaduzenja.getTblZaduzenja().getModel();
             if(model.isSignalZaZaduzi()){
             /*List<Zaduzenje> nova = new ArrayList<>();
             //List<Zaduzenje> izBaze = Communication.getInstance().vratiZaduzenja(clan);
              //kada je lista iz baze manja od zaduzenja
              /*for(int i=izBaze.size();i<zaduzenja.size();i++){
                  nova.add(zaduzenja.get(i));
              }*/
              
              Communication.getInstance().dodajZaduzenje(nova);
              JOptionPane.showMessageDialog(frmZaduzenja, "Uspesno dodata zaduzenja.");
             }
             if(model.isSignalZaRazduzi()){
                 List<Zaduzenje> izBaze = Communication.getInstance().vratiZaduzenja(clan);
                 List<Zaduzenje> editovana  = new ArrayList<>();
               /*  for(int i=0;i<zaduzenja.size();i++){
                    if( zaduzenja.get(i).getStatus()==Status.UPDATE ){
                        //zaduzenja.get(i).getDatumRazduzenja() != null &&
                         editovana.add(zaduzenja.get(i));
                         System.out.println(editovana.get(i));
                    }
                     
                     
                 }*/
                
                /*for(int i=0;i<zaduzenja.size();i++){
                    if(zaduzenja.get(i).getStatus()==Status.UPDATE){
                        editovana.add(zaduzenja.get(i));
                        System.out.println(editovana.get(i));
                    }
                }*/
                
                
                 for (Zaduzenje zaduzenje : zaduzenja) {
                     if(zaduzenje.getStatus()!= null){
                     if(zaduzenje.getStatus().equals(Status.UPDATE)) editovana.add(zaduzenje);
                 }}
                Communication.getInstance().razduziKnjige(editovana);
                JOptionPane.showMessageDialog(frmZaduzenja, "Uspesno razduzene knjige.");
             }
              
            }  
            
        });
        
    }

    private void knjigeUMapu(List<Knjiga> knjige) {
       for (Knjiga knjiga : knjige) {
        map.put(knjiga.getIdKnjige(), knjiga);
       }
    }
    
}
