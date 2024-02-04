/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jelena
 */
public class Clan implements GenericEntity{
    private int idClana;
    private String ime;
    private String prezime;
    private String adresa;
    private String email;
    public Clan() {
    }

    public Clan(int idClana, String ime, String prezime, String adresa,String email) {
        this.idClana = idClana;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.email=email;
    }

    public Clan(String ime, String prezime, String adresa,String email) {
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.email=email;
    }

    public int getIdClana() {
        return idClana;
    }

    public void setIdClana(int idClana) {
        this.idClana = idClana;
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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    @Override
    public String toString() {
        return ime + " " + prezime + ",adresa " + adresa+",email "+email;
    }

    @Override
    public String getNazivTabele() {
        return "clan";
    }

    @Override
    public String getKoloneZaDodavanje() {
        //nisam sigurna da je potrebno staviti i id jer je autoinkrement
        return "ime,prezime,adresa,email";
    }

    @Override
    public String vrednostiZaDodavanje() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(ime).append("',")
          .append("'").append(prezime).append("','")
          .append(adresa).append("'").append(",'").append(email).append("'");
        return sb.toString();
    }

    @Override
    public void setId(int id) {
        this.idClana=id;
    }

    @Override
    public List<GenericEntity> dodajNovi(ResultSet rs) throws Exception{
        List<GenericEntity> clanovi = new ArrayList<>();
        while(rs.next()){
            Clan c = new Clan();
            c.setId(rs.getInt("idClana"));
            c.setIme(rs.getString("ime"));
            c.setPrezime(rs.getString("prezime"));
            c.setAdresa(rs.getString("adresa"));
            c.setEmail(rs.getString("email"));
            clanovi.add(c);
        }
        return clanovi;
    }

    @Override
    public String getKriterijumZaBrisanje() {
        return "idClana="+idClana;
    }

    @Override
    public String getPrimarniKljuc() {
        return "idClana="+idClana;
    }

    @Override
    public String getVrednostiZaEdit() {
        return "adresa="+"'"+adresa+"'"+",email='"+email+"'";
    }

    @Override
    public String getKriterijumZaJednog() {
        return "ime = '" + ime + "' and prezime='" + prezime + "'";
    }

    @Override
    public GenericEntity dodajJednog(ResultSet rs) throws Exception {
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
        final Clan other = (Clan) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.idClana, other.idClana)) {
            return false;
        }
        if (!Objects.equals(this.adresa, other.adresa)) {
            return false;
        }
         if (!Objects.equals(this.email, other.email)) {
            return false;
        }
      
        return true;
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
    

