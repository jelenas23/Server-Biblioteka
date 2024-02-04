/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.clan;

import domain.Clan;
import java.util.ArrayList;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Jelena
 */
public class DodajClana extends AbstractGenericOperation{
    Clan zaUnos;

    public DodajClana(Clan zaUnos) {
        this.zaUnos = zaUnos;
    }
  
    @Override
    protected void preconditions(Object param) throws Exception {
         if (zaUnos == null || !(zaUnos instanceof Clan)) {
            throw new Exception("Greska pri cuvanju podataka o clanu!");
        }
         List<Clan> clanovi = new ArrayList<>();
        try{
         clanovi= repository.getAll(new Clan());
            System.out.println(clanovi.get(0));
        }catch (Exception ex){
            ex.getMessage();
        }finally{
        if(clanovi.size()!=0){
            for(Clan c:clanovi){
                if( c.getEmail().toLowerCase().equals(zaUnos.getEmail().toLowerCase()))
                   throw new Exception("Vec je registrovan clan "+c);
                }
            }
        }
        }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Clan)param);
    }

    public Clan getZaUnos() {
        return zaUnos;
    }
    
}

