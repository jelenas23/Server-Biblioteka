/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.zanr;

import domain.Zanr;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Jelena
 */
public class UcitajListuZanrova extends AbstractGenericOperation{

    private List<Zanr> zanrovi;
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        zanrovi=repository.getAll(param);
    }

    public List<Zanr> getZanrovi(){
        return zanrovi;
    }    
    
}
