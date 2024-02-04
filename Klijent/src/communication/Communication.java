/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import domain.*;
import communication.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jelena
 */
public class Communication {
    private static Communication instance;
    Socket socket;
    Sender sender;
    Receiver receiver;
    private Communication()throws Exception{
        socket = new Socket("127.0.0.1", 9000);
        sender=new Sender(socket);
        receiver = new Receiver(socket);
        
    }
    public static Communication getInstance() throws Exception{
        if(instance==null){
            instance=new Communication();
        }
        return instance;
    }
    
    public Bibliotekar login(String korisnicko,String lozinka) throws Exception{
        Bibliotekar b = new Bibliotekar();
        b.setKorisnickoIme(korisnicko);
        b.setLozinka(lozinka);
        Request request = new Request(Operation.LOGOVANJE,b);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if(response.getException()==null){
            return (Bibliotekar) response.getOdgovor();
        }else{
            throw response.getException();
        }
        
    }

    public List<Autor> vratiAutore() throws Exception {
            List<Autor> autori=new ArrayList<>();
            Request request = new Request(Operation.VRATI_AUTORE,autori);
            sender.send(request);
            Response response = (Response) receiver.receive();
            return (List<Autor>)response.getOdgovor();
    }

    public List<Zanr> vratiZanrove() throws Exception {
        List<Zanr> zanrovi=new ArrayList<>();
        Request request = new Request(Operation.VRATI_ZANROVE,zanrovi);
        sender.send(request);
        Response response = (Response) receiver.receive();
        return (List<Zanr>)response.getOdgovor();
    }

    public void dodajClana(Clan clan) throws Exception {
         Request request = new Request(Operation.DODAJ_CLANA,clan);
         sender.send(request);
         Response response = (Response) receiver.receive();
          if(response.getException()!=null){
              throw new Exception(response.getException().getMessage());
            }
    }
 
     public List<Bibliotekar> vratiBibliotekare() throws Exception {
       
      List<Bibliotekar> bibliotekari=new ArrayList<>();
      Request request=new Request(Operation.VRATI_SVE_BIBLIOTEKARE,new Bibliotekar());
      sender.send(request);
      Response response=(Response)receiver.receive();
      if(response.getException()==null){
                return (List<Bibliotekar>) response.getOdgovor();
            }else{
               throw response.getException();
            }
    }

    public List<Clan> vratiClanove() throws Exception{
     List<Clan> clanovi=new ArrayList<>();
     Request request=new Request(Operation.VRATI_SVE_CLANOVE, clanovi);
     sender.send(request);
     Response response=(Response)receiver.receive();
     return (List<Clan>)response.getOdgovor();
    }

    public List<Clan> vratiClanovePoUslovu(Clan clan) throws Exception {
       
       Request request=new Request(Operation.PRETRAZI_CLANA, clan);
       sender.send(request);
       Response response=(Response)receiver.receive(); 
       return (List<Clan>)response.getOdgovor();
    }

    public void dodajKnjigu(Knjiga novaKnjiga) throws Exception {
        Request request = new Request(Operation.DODAJ_KNJIGU,novaKnjiga);
         sender.send(request);
         Response response = (Response) receiver.receive();
          if(response.getException()!=null){
              throw new Exception(response.getException().getMessage());
            }
    }

    public List<Knjiga> vratiKnjige() throws Exception {
     List<Knjiga> knjige=new ArrayList<>();
     Request request=new Request(Operation.VRATI_SVE_KNJIGE, knjige);
     sender.send(request);
     Response response=(Response)receiver.receive();
     return (List<Knjiga>)response.getOdgovor();
    }

    public List<Knjiga> vratiKnjigePoUslovu(Knjiga k) throws Exception {
     
     Request request=new Request(Operation.PRETRAZI_KNJIGU, k);
     sender.send(request);
     Response response=(Response)receiver.receive();
     return (List<Knjiga>)response.getOdgovor();
    }

    public void obrisiClana(Clan clan) throws Exception {
        Request request=new Request(Operation.OBRISI_CLANA, clan);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(!response.getOdgovor().equals(StatusOdgovora.Ok.toString()) || response.getException()!=null ) 
         throw response.getException();
    }

    public void izmeniClana(Clan clan) throws Exception {
       Request request=new Request(Operation.IZMENI_CLANA, clan);
       sender.send(request);
       Response response=(Response)receiver.receive();
        if(/*!(response.getOdgovor().equals(StatusOdgovora.Ok)) || */response.getException()!=null ) 
        throw response.getException();
    }

    public void izmeniKnjigu(Knjiga knjiga) throws Exception {
        Request request=new Request(Operation.iZMENI_KNJIGU, knjiga);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(!response.getOdgovor().equals(StatusOdgovora.Ok.toString()) || response.getException()!=null ) 
         throw response.getException();
    }

    public void obrisiKnjigu(Knjiga knjiga) throws Exception {
        Request request=new Request(Operation.OBRISI_KNJIGU, knjiga);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(!response.getOdgovor().equals(StatusOdgovora.Ok.toString()) || response.getException()!=null ) 
         throw response.getException();
    }

    public List<Zaduzenje> vratiZaduzenja(Clan clan) throws Exception {
        Request request=new Request(Operation.VRATI_ZADUZENJA, clan);
        sender.send(request);
        Response response=(Response)receiver.receive();
        return (List<Zaduzenje>) response.getOdgovor();
    }

    public void dodajZaduzenje(List<Zaduzenje> nova) throws Exception {
        Request request = new Request(Operation.DODAJ_ZADUZENJA,nova);
         sender.send(request);
         Response response = (Response) receiver.receive();
          if(response.getException()!=null){
              throw new Exception(response.getException());
            }
    }

    public void razduziKnjige(List<Zaduzenje> editovana) throws Exception {
        Request request = new Request(Operation.RAZDUZI_KNJIGE,editovana);
         sender.send(request);
         Response response = (Response) receiver.receive();
          if(response.getException()!=null){
              throw new Exception(response.getException());
            }
    }

   
}
