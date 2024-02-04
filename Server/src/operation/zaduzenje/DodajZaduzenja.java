/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.zaduzenje;

import domain.Zaduzenje;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Jelena
 */
public class DodajZaduzenja extends AbstractGenericOperation{

 
    @Override
    protected void preconditions(Object param) throws Exception {
        if(param==null){
            throw new Exception("zaduzenje ne moze biti prazno!");
        }
        Zaduzenje z = (Zaduzenje) param;
        if(z.getDatumZaduzenja().after(new Date())){
            throw new Exception("Greska pri unosu datuma!");
        }
        
        if(z.getKnjiga().getBrPrimeraka() <= 0 ){
            throw new Exception("Izabrana knjiga nije trenutno na stanju!");
        }else{
            int br = z.getKnjiga().getBrPrimeraka();
            z.getKnjiga().setBrPrimeraka(br--);
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Zaduzenje)param);
    }

  
}
