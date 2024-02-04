/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jelena
 */
public class Bibliotekar implements GenericEntity{
    private int id;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String lozinka;

    public Bibliotekar() {
    }

    public Bibliotekar(int id, String ime, String prezime, String korisnickoIme, String lozinka) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    @Override
    public String toString() {
        return "Bibliotekar{" + "ime=" + ime + ", prezime=" + prezime + ", korisnickoIme=" + korisnickoIme + '}';
    }

    @Override
    public String getNazivTabele() {
        return "bibliotekar";
    }

    @Override
    public String getKoloneZaDodavanje() {
        
       return null;
    }

    @Override
    public String vrednostiZaDodavanje() {
        return null;
    }

    @Override
    public List<GenericEntity> dodajNovi(ResultSet rs) throws Exception {
        List<GenericEntity> bibliotekari = new ArrayList<>();
        while(rs.next()){
            Bibliotekar b = new Bibliotekar();
            b.setId(rs.getInt("id"));
            b.setIme(rs.getString("ime"));
            b.setPrezime(rs.getString("prezime"));
            b.setKorisnickoIme(rs.getString("korisnickoIme"));
            b.setLozinka(rs.getString("lozinka"));
            System.out.println(b);
            bibliotekari.add(b);
        }
        return bibliotekari;
    }

    @Override
    public String getPrimarniKljuc() {
        return "id="+id;
    }
    

    @Override
    public String getKriterijumZaBrisanje() {
        return "id="+id;
    }

    @Override
    public String getVrednostiZaEdit() {
        return null;
    }

    @Override
    public String getKriterijumZaJednog() {
        return "korisnickoIme = '" + korisnickoIme + "' and lozinka='" + lozinka + "'";
        
    }

    @Override
    public GenericEntity dodajJednog(ResultSet rs) throws Exception {
       
        if(rs.next()){
            id = rs.getInt("id");
            ime = rs.getString("ime");
            prezime = rs.getString("prezime");
            korisnickoIme= rs.getString("korisnickoIme");
            lozinka = rs.getString("lozinka");
            return this;
            
        }
        throw new Exception("Bibliotekar ne postoji u bazi!");
    }

    @Override
    public String getAlijas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getNaziviKolona() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vrednostZaJoin() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
