/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import communication.StatusOdgovora;
import controller.Controller;
import domain.Autor;
import domain.Bibliotekar;
import domain.Clan;
import domain.Knjiga;
import domain.Zaduzenje;
import domain.Zanr;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.Server;

/**
 *
 * @author Jelena
 */
public class ClientHandler extends Thread{
    Socket socket;
    Sender sender;
    Receiver receiver;
    private Bibliotekar b;
    Server server;
    public ClientHandler(Socket socket) {
        this.socket = socket;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }
 
    @Override
    public void run() {
        while(true){
            try {
                Request request = (Request) receiver.receive();
                Response response = new Response();
                try {
                    switch(request.getOperacija()){
                        case VRATI_SVE_BIBLIOTEKARE:
                            Bibliotekar bbb= (Bibliotekar) request.getArgument();
                            response.setOdgovor(Controller.getInstance().vratiBibliotekare(bbb));
                            sender.send(response);
                            break;
                        case LOGOVANJE:
                            Bibliotekar bibliotekar = (Bibliotekar) request.getArgument();
                            b=(Bibliotekar)Controller.getInstance().login(bibliotekar);
                            response.setOdgovor(b);
                            boolean aktivan = Controller.getInstance().vecJeUlogovan(b);
                            System.out.println(aktivan);
                            if (aktivan) {
                            response.setException(new Exception("Korisnik je već ulogovan."));
                            } else {
                            server.osvezi();
                                response.setStatus(StatusOdgovora.Ok);
                                Controller.getInstance().getAktivniKorisnici().add(this);
                                sender.send(response);
                            } 
                            break;
                        case VRATI_AUTORE:
                            response.setOdgovor(Controller.getInstance().vratiAutore());
                            sender.send(response);
                            break;
                        case VRATI_ZANROVE:
                            response.setOdgovor(Controller.getInstance().vratiZanrove());
                            sender.send(response);
                            break;
                        case DODAJ_CLANA:
                            Clan c = (Clan) request.getArgument();
                            Object odgovor = Controller.getInstance().dodajClana(c);
                            if(!(odgovor instanceof Clan)) response.setException((Exception)odgovor);
                            else response.setOdgovor(odgovor);
                            sender.send(response);
                            break;
                        case VRATI_SVE_CLANOVE:
                            response.setOdgovor(Controller.getInstance().vratiClanove());
                            sender.send(response);
                            break;
                        case PRETRAZI_CLANA:
                           response.setOdgovor(Controller.getInstance().vratiClanovePoUslovu((Clan)request.getArgument()));
                           sender.send(response);
                           break;
                        case DODAJ_KNJIGU:
                            Knjiga k = (Knjiga) request.getArgument();
                            Object odg = Controller.getInstance().dodajKnjigu(k);
                            if(!(odg instanceof Knjiga)) response.setException((Exception)odg);
                            else response.setOdgovor(odg);
                            sender.send(response);
                            break;
                        case PRETRAZI_KNJIGU:
                           response.setOdgovor(Controller.getInstance().vratiKnjigePoUslovu((Knjiga)request.getArgument()));
                           sender.send(response);
                           break;
                        case VRATI_SVE_KNJIGE:
                            response.setOdgovor(Controller.getInstance().vratiKnjige());
                            sender.send(response);
                            break;
                        case OBRISI_CLANA:
                           response.setOdgovor(Controller.getInstance().obrisiClana((Clan)request.getArgument()));
                           sender.send(response);
                           break;
                        case IZMENI_CLANA:
                            response.setOdgovor(Controller.getInstance().izmeniClana((Clan)request.getArgument()));
                          /* Object odgIzmenaClana = Controller.getInstance().izmeniClana((Clan)request.getArgument());
                           if(!(odgIzmenaClana instanceof Exception)) {
                               response.setStatus((StatusOdgovora) odgIzmenaClana);
                           }else response.setException((Exception) odgIzmenaClana);*/
                           sender.send(response);
                           break;
                        case iZMENI_KNJIGU:
                           response.setOdgovor(Controller.getInstance().izmeniKnjigu((Knjiga)request.getArgument()));
                           sender.send(response);
                           break;
                        case OBRISI_KNJIGU:
                           response.setOdgovor(Controller.getInstance().obrisiKnjigu((Knjiga)request.getArgument()));
                           sender.send(response);
                           break;
                        case VRATI_ZADUZENJA:
                           response.setOdgovor(Controller.getInstance().vratiZaduzenja((Clan)request.getArgument()));
                           sender.send(response);
                           break;
                        case DODAJ_ZADUZENJA:
                            List<Zaduzenje> zaduzenja =  (List<Zaduzenje>) request.getArgument();
                            Object odg3 = Controller.getInstance().dodajZaduzenja(zaduzenja);
                            if(!(odg3 instanceof Exception)) response.setOdgovor(odg3);
                            else response.setException((Exception)odg3);
                            sender.send(response);
                            break;
                        case RAZDUZI_KNJIGE:
                            List<Zaduzenje> razduzenja =  (List<Zaduzenje>) request.getArgument();
                            Object odg4 = Controller.getInstance().RazduziKnjige(razduzenja);
                            if(!(odg4 instanceof Exception)) response.setOdgovor(odg4);
                            else response.setException((Exception)odg4);
                            sender.send(response);
                            break;
                       
                            
                    }
                    
                } catch (Exception ex) {
                    response.setException(ex);
                    
                    sender.send(response);
                }
                
            } catch (Exception ex) {
                    System.out.println(ex);
                    System.out.println("Doslo do greske pri obradi klijentskih zahteva!");
                }
            
        }
    }

     public void stopHandler() throws IOException
    {
        if(this.socket != null) {
            if(!this.socket.isClosed()) {
                this.socket.close();
            }
        }
    }
     

    public Object getKlijent() {
        return socket;
    }

    public Bibliotekar getBibliotekar() {
        return b;
    }

    public void setBibliotekar(Bibliotekar bibliotekar) {
        this.b = bibliotekar;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }
}
