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
public class Autor implements GenericEntity{
    private int idAutora;
    private String ime;
    private String prezime;

    public Autor() {
    }

    public Autor(int idAutora, String ime, String prezime) {
        this.idAutora = idAutora;
        this.ime = ime;
        this.prezime = prezime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public int getIdAutora() {
        return idAutora;
    }

    public void setIdAutora(int idAutora) {
        this.idAutora = idAutora;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    @Override
    public String toString() {
        return ime+" "+prezime;
    }

    @Override
    public String getNazivTabele() {
        return "autor";
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
        this.idAutora=id;
    }

    @Override
    public List<GenericEntity> dodajNovi(ResultSet rs) throws Exception {
        List<GenericEntity> autori = new ArrayList<>();
        while(rs.next()){
            Autor a = new Autor();
            a.setId(rs.getInt("idAutora"));
            a.setIme(rs.getString("ime"));
            a.setPrezime(rs.getString("prezime"));
            System.out.println(a);
            autori.add(a);
        }
        return autori;
    }

    @Override
    public GenericEntity dodajJednog(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getPrimarniKljuc() {
        return "idAutora="+idAutora;
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
        return "id="+idAutora;
                
    }

    @Override
    public String getAlijas() {
        return "a";
    }

    @Override
    public String getNaziviKolona() {
        return "a.ime,a.prezime";
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
        final Autor other = (Autor) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.idAutora, other.idAutora)) {
            return false;
        }
       
        return true;
    }
    
}
