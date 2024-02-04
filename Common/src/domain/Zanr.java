/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Jelena
 */
public class Zanr implements GenericEntity{
    private int idZandra;
    private String nazivZanra;

    public Zanr() {
    }

    public Zanr(int idZandra, String nazivZanra) {
        this.idZandra = idZandra;
        this.nazivZanra = nazivZanra;
    }

    public String getNazivZanra() {
        return nazivZanra;
    }

    public void setNazivZanra(String nazivZanra) {
        this.nazivZanra = nazivZanra;
    }

    public int getIdZandra() {
        return idZandra;
    }

    public void setIdZandra(int idZandra) {
        this.idZandra = idZandra;
    }

    @Override
    public String toString() {
        return nazivZanra;
    }

    @Override
    public String getNazivTabele() {
        return "zanr";
    }

    @Override
    public String getKoloneZaDodavanje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vrednostiZaDodavanje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setId(int id) {
        this.idZandra=id;
    }

    @Override
    public List<GenericEntity> dodajNovi(ResultSet rs) throws Exception {
        List<GenericEntity> zanrovi = new ArrayList<>();
        while(rs.next()){
            Zanr z = new Zanr();
            z.setIdZandra(rs.getInt("idZanra"));
            z.setNazivZanra(rs.getString("nazivZanra"));
           
            zanrovi.add(z);
        }
        return zanrovi;
    }

    @Override
    public GenericEntity dodajJednog(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getPrimarniKljuc() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getKriterijumZaBrisanje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getVrednostiZaEdit() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getKriterijumZaJednog() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getAlijas() {
        return "z";
    }

    @Override
    public String getNaziviKolona() {
        return "z.nazivZanra";
    }

    @Override
    public String vrednostZaJoin() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Zanr other = (Zanr) obj;
        if (!Objects.equals(this.nazivZanra, other.nazivZanra)) {
            return false;
        }
        if (!Objects.equals(this.idZandra, other.idZandra)) {
            return false;
        }
       
        return true;
    }
    
}
