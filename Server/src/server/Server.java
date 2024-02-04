/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jelena
 */
package server;

import dbRepository.DbConnectionFactory;
import domain.Bibliotekar;
import form.frmMainServer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import threads.ClientHandler;

public class Server extends Thread{
    private List<ClientHandler> klijenti;
    frmMainServer serverskaForma;
    ServerSocket serverSocket;
    public Server(frmMainServer aThis) {
        serverskaForma = aThis;
        klijenti = new ArrayList<>();
    }

    @Override
    public void run() {
         try {
            serverSocket = new ServerSocket(9000);
            while(serverSocket != null && !serverSocket.isClosed()){
                System.out.println("Ceka se uspostavljanje konekcije...");
                Socket socket = serverSocket.accept();
                System.out.println("Klijent se povezao.");
                handleClient(socket);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    
    
   
 

    private void handleClient(Socket socket) {
        ClientHandler clientHandler = new ClientHandler(socket);
        clientHandler.setServer(this);
        klijenti.add(clientHandler);
        clientHandler.start();
    }

    public void stopServer() throws SQLException, IOException{
        for (ClientHandler c: klijenti) {
                c.stopHandler();
        }
            
        if(serverSocket != null && !serverSocket.isClosed()) {
            serverSocket.close();
            serverSocket = null;
        }
        DbConnectionFactory.getInstance().disconnect();
      
    }

    public List<Bibliotekar> getKlijenti() {
     
        List<Bibliotekar> lista = new ArrayList<>();
        for(ClientHandler ch : klijenti) {
            if(ch.getKlijent()!= null) {
                lista.add(ch.getBibliotekar());
            }
        }
        return lista;
    }

    public void setKlijenti(List<ClientHandler> klijenti) {
        this.klijenti = klijenti;
    }

    public void osvezi() {
        serverskaForma.osveziListu();
    }
    
}
