/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Jelena
 */
public class Knjiga implements GenericEntity{
    private int idKnjige;
    private String nazivKnjige;
    private int brPrimeraka;
    private Autor autor;
    private Zanr zanr;
    private String polica;
    private int godinaIzdanja;

    public Knjiga() {
    }

    public Knjiga(int idKnjige, String nazivKnjige, int brPrimeraka, Autor autor, Zanr zanr, String polica, int godinaIzdanja) {
        this.idKnjige = idKnjige;
        this.nazivKnjige = nazivKnjige;
        this.brPrimeraka = brPrimeraka;
        this.autor = autor;
        this.zanr = zanr;
        this.polica = polica;
        this.godinaIzdanja = godinaIzdanja;
    }

    public Knjiga(String nazivKnjige, int brPrimeraka, Autor autor, Zanr zanr, String polica, int godinaIzdanja) {
        this.nazivKnjige = nazivKnjige;
        this.brPrimeraka = brPrimeraka;
        this.autor = autor;
        this.zanr = zanr;
        this.polica = polica;
        this.godinaIzdanja = godinaIzdanja;
    }

    
    public int getIdKnjige() {
        return idKnjige;
    }

    public void setIdKnjige(int idKnjige) {
        this.idKnjige = idKnjige;
    }

    public String getNazivKnjige() {
        return nazivKnjige;
    }

    public void setNazivKnjige(String nazivKnjige) {
        this.nazivKnjige = nazivKnjige;
    }

    public int getBrPrimeraka() {
        return brPrimeraka;
    }

    public void setBrPrimeraka(int brPrimeraka) {
        this.brPrimeraka = brPrimeraka;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Zanr getZanr() {
        return zanr;
    }

    public void setZanr(Zanr zanr) {
        this.zanr = zanr;
    }

    public String getPolica() {
        return polica;
    }

    public void setPolica(String polica) {
        this.polica = polica;
    }

    public int getGodinaIzdanja() {
        return godinaIzdanja;
    }

    public void setGodinaIzdanja(int godinaIzdanja) {
        this.godinaIzdanja = godinaIzdanja;
    }

    @Override
    public String toString() {
        return  nazivKnjige +"," + autor ;
    }

    @Override
    public String getNazivTabele() {
        return "knjiga";
    }

    @Override
    public String getKoloneZaDodavanje() {
        return "nazivKnjige,brPrimeraka,idAutora,idZanra,polica,godinaIzdanja";
    }

    @Override
    public String vrednostiZaDodavanje() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(nazivKnjige).append("',")
          .append(brPrimeraka).append(",")
          .append(autor.getIdAutora()).append(",").append(zanr.getIdZandra())
          .append(",'").append(polica).append("',")
          .append(godinaIzdanja);
        return sb.toString();
    }

    @Override
    public void setId(int id) {
        this.idKnjige=id;
    }

    @Override
    public List<GenericEntity> dodajNovi(ResultSet rs) throws Exception {
        List<GenericEntity> knjige = new ArrayList<>();
        while(rs.next()){
            Knjiga k = new Knjiga();
            k.setId(rs.getInt("idKnjige"));
            k.setNazivKnjige(rs.getString("nazivKnjige"));
            Autor a = new Autor();
            Zanr z = new Zanr();
            a.setId(rs.getInt("idAutora"));
            z.setId(rs.getInt("idZanra"));
            ResultSetMetaData rsmd = rs.getMetaData();
            if(rsmd.getColumnCount()>10){
            if(rsmd.getColumnName(5).equals("ime")){
                a.setIme(rs.getString("ime"));
            }
             if(rsmd.getColumnName(6).equals("prezime")){
               a.setPrezime(rs.getString("prezime"));
            }
            if(rsmd.getColumnName(9).equals("nazivZanra")){
               z.setNazivZanra(rs.getString("nazivZanra"));
            }
            }
            
            k.setAutor(a);
            k.setZanr(z);
            k.setBrPrimeraka(rs.getInt("brPrimeraka"));
            k.setPolica(rs.getString("polica"));
            k.setGodinaIzdanja(rs.getInt("godinaIzdanja"));
            //System.out.println(k);
            knjige.add(k);
        }
        return knjige;
    }

    @Override
    public GenericEntity dodajJednog(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getPrimarniKljuc() {
        return "idKnjige="+idKnjige;
    }

    @Override
    public String getKriterijumZaBrisanje() {
         return "idKnjige="+idKnjige;
    }

    @Override
    public String getVrednostiZaEdit() {
        return "brPrimeraka="+brPrimeraka+",polica='"+polica+"'";
    }

    @Override
    public String getKriterijumZaJednog() {
        return "nazivKnjige LIKE '"+nazivKnjige+"%'";
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
        final Knjiga other = (Knjiga) obj;
        if (!Objects.equals(this.nazivKnjige, other.nazivKnjige)) {
            return false;
        }
        if (!Objects.equals(this.autor, other.autor)) {
            return false;
        }
        if (!Objects.equals(this.idKnjige, other.idKnjige)) {
            return false;
        }
        if (!Objects.equals(this.brPrimeraka, other.brPrimeraka)) {
            return false;
        }
       if (!Objects.equals(this.zanr, other.zanr)) {
            return false;
        }
        if (!Objects.equals(this.polica, other.polica)) {
            return false;
        }
        if (!Objects.equals(this.godinaIzdanja, other.godinaIzdanja)) {
            return false;
        }
        return true;
    }

    @Override
    public String getAlijas() {
        return "k";
    }

    @Override
    public String getNaziviKolona() {
        return "k.idKnjige,k.nazivKnjige,k.idAutora,k.idZanra,k.polica,k.godinaIzdanja";
    }

    @Override
    public String vrednostZaJoin() {
        return "k.";
    }

    @Override
    public int hashCode() {
        int hash = 7;
       
        return hash;
    }
    

}
