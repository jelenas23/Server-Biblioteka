/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.clan;

import domain.Clan;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Jelena
 */
public class PretraziClana extends AbstractGenericOperation{

   List<Clan> clanovi;
    
    @Override
    protected void preconditions(Object param) throws Exception {

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
         clanovi= repository.getPoUslovu(param);
    }

    public List<Clan> getClanovi() {
        return clanovi;
    }
    
}
