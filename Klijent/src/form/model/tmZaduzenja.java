/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form.model;

import domain.Knjiga;
import domain.Status;
import domain.Zaduzenje;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jelena
 */
public class tmZaduzenja extends AbstractTableModel{
    public final List<Zaduzenje> zaduzenja;
    public final List<Knjiga> knjige;
    private final String[] naziviKolona= new String[] {"Naziv knjige", "Datum od","Datum do"};;
    private Class[] klase=new Class[]{Object.class,Object.class,Object.class};
    private boolean signalZaZaduzi = false;
    private boolean signalZaRazduzi = false;
    private DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
    public tmZaduzenja(List<Zaduzenje> zaduzenja,List<Knjiga> knjige) {
        this.zaduzenja = zaduzenja;
        this.knjige=knjige;
    }
    
    

    @Override
    public int getRowCount() {
        return zaduzenja.size();
    }

    @Override
    public int getColumnCount() {
        return naziviKolona.length;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
         Zaduzenje zaduzenje = zaduzenja.get(rowIndex);
        switch (columnIndex) {
            case 0:
                zaduzenje.setKnjiga((Knjiga) aValue);
                break;
            case 1:
               zaduzenje.setDatumZaduzenja((Date) aValue);
               zaduzenje.setStatus(Status.INSERT);
                break;
            case 2: 
               zaduzenje.setDatumRazduzenja((Date)  aValue);
               zaduzenje.setStatus(Status.UPDATE);
               System.out.println("ispisi " + zaduzenje);
                break;
           
                
        }
    }

    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Zaduzenje z = zaduzenja.get(rowIndex);
        
         switch (columnIndex) {
            case 0:
                if(z.getKnjiga()==null)return null;return z.getKnjiga().getNazivKnjige();
            case 1:
                if(z.getDatumZaduzenja()==null) return null;return df.format(z.getDatumZaduzenja());
            case 2:
                if(z.getDatumRazduzenja()==null) return null;return df.format(z.getDatumRazduzenja());
                
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
        
        //editable je samo poslednji dodati red
        if((column==2 && signalZaZaduzi) && row==zaduzenja.size()-1)return false;
        else if(((column==1 || column==0) && signalZaRazduzi))return false;
        return true;
    }
    public List<Zaduzenje> getZaduzenja() {
        return zaduzenja;
    }

 public Zaduzenje getZaduzenjeAt(int row) {
        return zaduzenja.get(row);
    }

 
     public void addZaduzenje(Zaduzenje novo){
        zaduzenja.add(novo);
        
        fireTableDataChanged();
    }

    public boolean isSignalZaZaduzi() {
        return signalZaZaduzi;
    }

    public void setSignalZaZaduzi(boolean signalZaZaduzi) {
        this.signalZaZaduzi = signalZaZaduzi;
    }

    public boolean isSignalZaRazduzi() {
        return signalZaRazduzi;
    }

    public void setSignalZaRazduzi(boolean signalZaRazduzi) {
        this.signalZaRazduzi = signalZaRazduzi;
    }


    
 
}
