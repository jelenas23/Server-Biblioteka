/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form.model;

import domain.Clan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jelena
 */
public class tmNadjeniClanovi extends AbstractTableModel{

    public final List<Clan> clanovi;
    private final String[] naziviKolona= new String[] {"ID clana", "Ime","Prezime","Adresa","Email"};;
    private Class[] klase=new Class[]{Object.class,Object.class,Object.class, Object.class, Object.class};
  
    
    public tmNadjeniClanovi(List<Clan> clanovi) {
        this.clanovi = clanovi;
    }

    @Override
    public int getRowCount() {
     return clanovi.size();
    }

    @Override
    public int getColumnCount() {
       return naziviKolona.length;
    }

      @Override
    public Object getValueAt(int row, int column) {
        Clan c = clanovi.get(row);
        switch (column) {
            case 0:
                return c.getIdClana();
            case 1:
                return c.getIme();
            case 2:
                return c.getPrezime();
            case 3:
                return c.getAdresa();
            case 4:
                return c.getEmail();
                
            default:
                return "\n";
        }
    }
    @Override
    public String getColumnName(int column){
        return naziviKolona[column];
    }
    @Override
    public Class<?> getColumnClass(int column){
        return klase[column];
    }
    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }

    public List<Clan> getClanovi() {
        return clanovi;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
       Clan c = clanovi.get(rowIndex);
        switch (columnIndex) {
            case 0:
                 c.setIdClana((int) aValue);
                 break;
            case 1:
                c.setIme((String) aValue);
                break;
            case 2:
                c.setPrezime((String) aValue);
                break;
            case 3:
               c.setAdresa((String) aValue);
               break;
             case 4:
               c.setEmail((String) aValue);
               break;
             
        }
    }
 public Clan getClanAt(int row) {
        return clanovi.get(row);
    }
 
}
