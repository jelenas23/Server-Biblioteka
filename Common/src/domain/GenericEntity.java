/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Jelena
 */
public interface GenericEntity extends Serializable{
    String getNazivTabele();
    String getKoloneZaDodavanje();
    String vrednostiZaDodavanje();
    void setId(int id);

    public List<GenericEntity> dodajNovi(ResultSet rs)throws Exception;
    
    public GenericEntity dodajJednog(ResultSet rs) throws Exception;
    
    public String getPrimarniKljuc();
   
    public String getKriterijumZaBrisanje();

    public String getVrednostiZaEdit();

    public String getKriterijumZaJednog();

    public String getAlijas();
    
    public String getNaziviKolona();
    
    public String vrednostZaJoin();
}
