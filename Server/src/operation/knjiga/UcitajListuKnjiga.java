/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.knjiga;


import domain.Knjiga;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Jelena
 */
public class UcitajListuKnjiga extends AbstractGenericOperation{

  private List<Knjiga> knjige;

    @Override
    protected void preconditions(Object param) throws Exception {
        //nema preduslova
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        knjige = repository.getAllJoin(param);
    }
    
    public List<Knjiga> vratiSveKnjige(){
        return knjige;
    }

   
}
