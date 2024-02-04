/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.clan;

import domain.Clan;
import operation.AbstractGenericOperation;

/**
 *
 * @author Jelena
 */
public class IzmeniClana extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
         if (param == null || !(param instanceof Clan)) {
            throw new Exception("Greska pri cuvanju podataka o clanu!");
        }
       
        Clan clan = (Clan) param;
        if(clan.getAdresa().isEmpty()){
             throw new Exception("Morate uneti adresu člana!");
        }
         if(clan.getEmail().isEmpty()){
             throw new Exception("Morate uneti email člana!");
        }
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
             repository.edit((Clan)param);    
    }
    
    
}
