/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form.model;

import domain.Autor;
import domain.Clan;
import domain.Knjiga;
import domain.Zanr;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jelena
 */
public class tmNadjeneKnjige extends AbstractTableModel{
    public final List<Knjiga> knjige;
    private final String[] naziviKolona= new String[] {"Naziv knjige", "Autor","Zanr","Broj primeraka","Godina izdanja","Polica"};;
    private Class[] klase=new Class[]{Object.class,Object.class,Object.class, Object.class, Object.class, Object.class};
  
    
    public tmNadjeneKnjige(List<Knjiga> knjige) {
       this.knjige=knjige;
    }

    @Override
    public int getRowCount() {
     return knjige.size();
    }

    @Override
    public int getColumnCount() {
       return naziviKolona.length;
    }

      @Override
    public Object getValueAt(int row, int column) {
        Knjiga k = knjige.get(row);
        switch (column) {
            case 0:
                return k.getNazivKnjige();
            case 1:
                return k.getAutor().toString();
            case 2:
                return k.getZanr().toString();
            case 3:
                return k.getBrPrimeraka();
            case 4:
                return k.getGodinaIzdanja();
            case 5:
                return k.getPolica();  
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

    public List<Knjiga> getClanovi() {
        return knjige;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
       Knjiga k = knjige.get(rowIndex);
        switch (columnIndex) {
            case 0:
                 k.setNazivKnjige((String) aValue);
                break;
            case 1:
                 k.setAutor((Autor) aValue);
                break;
            case 2:
                k.setZanr((Zanr) aValue);
               break;
            case 3:
              k.setBrPrimeraka((int)aValue);
              break;
             case 4:
               k.setGodinaIzdanja((int)aValue);
               break;
            case 5:
               k.setPolica((String)aValue);
               break;
             
        }
    }
 public Knjiga getKnjigaAt(int row) {
        return knjige.get(row);
    }
 
}
