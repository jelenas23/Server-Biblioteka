/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.zaduzenje;


import domain.Clan;
import domain.Zaduzenje;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Jelena
 */
public class UcitajListuZaduzenja extends AbstractGenericOperation{
    private List<Zaduzenje> zaduzenja;
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Zaduzenje zaduzenje = new Zaduzenje();
        zaduzenje.setClan((Clan) param);
        zaduzenja = repository.getPoUslovu(zaduzenje);
    }
    public List<Zaduzenje> vratiSvaZaduzenja(){
        return zaduzenja;
    }
}
