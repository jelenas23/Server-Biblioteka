/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.Serializable;

/**
 *
 * @author Jelena
 */
public class Request implements Serializable{
    private Operation operacija;
    private Object argument;

    public Request() {
    }

    public Request(Operation operacija, Object argument) {
        this.operacija = operacija;
        this.argument = argument;
    }

    public Operation getOperacija() {
        return operacija;
    }

    public void setOperacija(Operation operacija) {
        this.operacija = operacija;
    }

    public Object getArgument() {
        return argument;
    }

    public void setArgument(Object argument) {
        this.argument = argument;
    }
    
}
