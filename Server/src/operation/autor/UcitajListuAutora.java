/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.autor;

import domain.Autor;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Jelena
 */
public class UcitajListuAutora extends AbstractGenericOperation{

    private List<Autor> autori;
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        autori=repository.getAll(param);
    }

    public List<Autor> getAutori(){
        return autori;
    }    
}
