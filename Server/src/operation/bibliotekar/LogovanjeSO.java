/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.bibliotekar;

import domain.Bibliotekar;
import operation.AbstractGenericOperation;

/**
 *
 * @author Jelena
 */
public class LogovanjeSO extends AbstractGenericOperation{
   
    
    private Bibliotekar bibliotekar;

    @Override
    protected void preconditions(Object param) throws Exception {
        if(!(param instanceof Bibliotekar)){
           throw new Exception("Greška,bibliotekar ne postoji u bazi.");
        }else{
            System.out.println("Nadjen bibliotekar.");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
         bibliotekar=(Bibliotekar) repository.getOne(param);
    }

    public Bibliotekar getBibliotekar() {
        return bibliotekar;
    }

    public void setBibliotekar(Bibliotekar bibliotekar) {
        this.bibliotekar = bibliotekar;
    }
    
}
