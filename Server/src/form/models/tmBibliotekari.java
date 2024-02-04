/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.models;

import domain.Bibliotekar;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author milaz
 */
public class tmBibliotekari extends AbstractTableModel {

    String[] naziviKolona = {"Ime i prezime", "Korisničko ime"};
    private List<Bibliotekar> bibliotekari;

    public tmBibliotekari() {
        bibliotekari = new ArrayList<>();
    }

    public tmBibliotekari(List<Bibliotekar> bibliotekari) {
        this.bibliotekari= bibliotekari;
    }

    @Override
    public int getRowCount() {
        return bibliotekari.size();
    }

    @Override
    public int getColumnCount() {
        return naziviKolona.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Bibliotekar b = bibliotekari.get(row);
        switch (column) {
            case 0:
                return b.getIme() + " " + b.getPrezime();
            case 1:
                return b.getKorisnickoIme();
           
            default:
                return "\n";
        }
    }

    @Override
    public String getColumnName(int i) {
        return naziviKolona[i];
    }

    public List<Bibliotekar> getBibliotekari() {
        return bibliotekari;
    }

    public void setBibliotekari(ArrayList<Bibliotekar> bibliotekari) {
        this.bibliotekari = bibliotekari;
    }

    public void dodaj(Bibliotekar b) {
        bibliotekari.add(b);
        fireTableDataChanged();
    }

    public void obrisi(Bibliotekar b) {
         bibliotekari.remove(b);
        fireTableDataChanged();
    }

    public void odjaviSe(Bibliotekar b) {
        bibliotekari.remove(b);
        fireTableDataChanged();
    }

    public void obrisiSve() {
        this.bibliotekari = new ArrayList<>();
    }

}
