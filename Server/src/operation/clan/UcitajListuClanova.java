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
public class UcitajListuClanova extends AbstractGenericOperation{
    private List<Clan> clanovi;

    @Override
    protected void preconditions(Object param) throws Exception {
        //nema preduslova
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        clanovi = repository.getAll(param);
    }
    
    public List<Clan> vratiSveClanove(){
        return clanovi;
    }
}
