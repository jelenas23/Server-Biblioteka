/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.knjiga;

import domain.Knjiga;
import java.util.ArrayList;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Jelena
 */
public class DodajKnjigu extends AbstractGenericOperation{

   Knjiga zaUnos;

    public DodajKnjigu(Knjiga zaUnos) {
        this.zaUnos = zaUnos;
    }
  
    @Override
    protected void preconditions(Object param) throws Exception {
         if (zaUnos == null || !(zaUnos instanceof Knjiga)) {
            throw new Exception("Greska pri cuvanju podataka o knjizi!");
        }
         List<Knjiga> knjige = new ArrayList<>();
        try{
         knjige= repository.getAllJoin(new Knjiga());
            System.out.println(knjige.get(0));
        }catch (Exception ex){
            ex.getMessage();
        }finally{
        if(knjige.size()!=0){
            for(Knjiga k:knjige){
                if(zaUnos.getNazivKnjige().equals(k.getNazivKnjige()) &&
                   zaUnos.getAutor().getIdAutora() == k.getAutor().getIdAutora() &&
                        zaUnos.getGodinaIzdanja() == k.getGodinaIzdanja())
                   throw new Exception("Uneta knjiga vec postoji u bazi ! "+k);
                }
            }
        }
        }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Knjiga)param);
    }

    public Knjiga getZaUnos() {
        return zaUnos;
    }
    
    
}
