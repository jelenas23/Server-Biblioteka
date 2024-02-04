/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.zaduzenje;

import domain.Status;
import domain.Zaduzenje;
import java.util.Date;
import operation.AbstractGenericOperation;

/**
 *
 * @author Jelena
 */
public class RazduziKnjige extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
         if(param==null){
            throw new Exception("zaduzenje ne moze biti prazno!");
        }
        Zaduzenje z = (Zaduzenje) param;
        
        if(z.getStatus().equals(Status.UPDATE) && z.getDatumRazduzenja()!=null ){
           if(z.getDatumRazduzenja().after(new Date())||z.getDatumRazduzenja().before(z.getDatumZaduzenja()) || z.getDatumZaduzenja().after(z.getDatumRazduzenja())){
            throw new Exception("Greska pri unosu datuma!");
        }}
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.edit(param);
    }
    
}
