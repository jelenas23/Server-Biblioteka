/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jelena
 */
public class Sender {
    private Socket socket;
    ObjectOutputStream out;

    public Sender(Socket socket) {
        this.socket = socket;
    }
    public void send(Object object) throws Exception{
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(object);
            out.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Greska prilikom slanja objekta!\n" + ex.getMessage());
        }
        
    }
}
