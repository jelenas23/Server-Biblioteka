/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Jelena
 */
public class Zaduzenje implements GenericEntity{
    private int brojZaduzenja;
    private Date datumZaduzenja;
    private Date datumRazduzenja;
    private Knjiga knjiga;
    private Clan clan;
    private Status status;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    public Zaduzenje() {
    }
    
    public Zaduzenje(int brojZaduzenja, Date datumZaduzenja, Date datumRazduzenja, Clan clan,Knjiga knjiga) {
        this.brojZaduzenja = brojZaduzenja;
        this.datumZaduzenja = datumZaduzenja;
        this.datumRazduzenja=datumRazduzenja;
        this.knjiga = knjiga;
        this.clan = clan;
    }
 

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }

    public int getBrojZaduzenja() {
        return brojZaduzenja;
    }

    public void setBrojZaduzenja(int brojZaduzenja) {
        this.brojZaduzenja = brojZaduzenja;
    }

    public Date getDatumZaduzenja() {
        return datumZaduzenja;
    }

    public void setDatumZaduzenja(Date datumZaduzenja) {
        this.datumZaduzenja = datumZaduzenja;
    }

    public Date getDatumRazduzenja() {
        return datumRazduzenja;
    }

    public void setDatumRazduzenja(Date datumRazduzenja) {
        this.datumRazduzenja = datumRazduzenja;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

   

    @Override
    public String toString() {
        return "Zaduzenje{" + "brojZaduzenja=" + brojZaduzenja + ", datumZaduzenja=" + datumZaduzenja + ", datumRazduzenja=" + datumRazduzenja + ", knjiga="+knjiga  + ", clan=" + clan + '}';
    }

    @Override
    public String getNazivTabele() {
        return "zaduzenje";
    }

    @Override
    public String getKoloneZaDodavanje() {
        return "datumZaduzenja,datumRazduzenja,idClana,idKnjige";
    }

    @Override
    public String vrednostiZaDodavanje() {
        if(datumRazduzenja!=null){
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(df.format(datumZaduzenja)).append("','").append(df.format(datumRazduzenja))
          .append("',")
          .append(clan.getIdClana()).append(",").append(knjiga.getIdKnjige());
        System.out.println(sb.toString());  
        return sb.toString();
        }else{
            StringBuilder sb = new StringBuilder();
        sb.append("'").append(df.format(datumZaduzenja)).append("',").append("NULL")
          .append(",")
          .append(clan.getIdClana()).append(",").append(knjiga.getIdKnjige());
        System.out.println(sb.toString());  
        return sb.toString();
        }
        
    }

    @Override
    public void setId(int id) {
        this.brojZaduzenja=id;
    }

    @Override
    public List<GenericEntity> dodajNovi(ResultSet rs) throws Exception {
         List<GenericEntity> zaduzenja = new ArrayList<>();
        while(rs.next()){
            Zaduzenje z = new Zaduzenje();
            z.setId(rs.getInt("brojZaduzenja"));
            z.setDatumZaduzenja(rs.getDate("datumZaduzenja"));
            z.setDatumRazduzenja(rs.getDate("datumRazduzenja"));
            clan.setId(rs.getInt("idClana"));
            Knjiga knjiga = new Knjiga();
            knjiga.setIdKnjige(rs.getInt("idKnjige"));
            z.setClan(clan);
            z.setKnjiga(knjiga);
            zaduzenja.add(z);
        }
        return zaduzenja;
    }

    @Override
    public GenericEntity dodajJednog(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getPrimarniKljuc() {
        return "brojZaduzenja="+brojZaduzenja;
    }

    @Override
    public String getKriterijumZaBrisanje() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getVrednostiZaEdit() {
        return "datumRazduzenja='"+df.format(datumRazduzenja)+"'";
    }

    @Override
    public String getKriterijumZaJednog() {
        System.out.println(clan.getIdClana() + "id clana");
        return "idClana="+clan.getIdClana();
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
        final Zaduzenje other = (Zaduzenje) obj;
        if (!Objects.equals(this.brojZaduzenja, other.brojZaduzenja)) {
            return false;
        }
        if (!Objects.equals(this.knjiga, other.knjiga)) {
            return false;
        }
        if (!Objects.equals(this.datumRazduzenja, other.datumRazduzenja)) {
            return false;
        }
        if (!Objects.equals(this.datumZaduzenja, other.datumZaduzenja)) {
            return false;
        }
        if (!Objects.equals(this.clan, other.clan)) {
            return false;
        }
        
        return true;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
}
