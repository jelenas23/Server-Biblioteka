/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import domain.GenericEntity;
import domain.Zaduzenje;
import java.util.List;

/**
 *
 * @author Jelena
 */
public interface Repository<T> {
 
    void add(T param) throws Exception;
    void edit(T param) throws Exception;
    void delete(T param)throws Exception;
    List <T> getAll(T param)throws Exception;
    T getOne(T param) throws Exception;
    List<T> getPoUslovu(T param)throws Exception;
    List<T> getAllJoin(T param) throws Exception;
    List<T> getJoinPoUslovu(T param) throws Exception;
   
 
}
