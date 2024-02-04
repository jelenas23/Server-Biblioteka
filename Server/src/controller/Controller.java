/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.StatusOdgovora;
import dbRepository.impl.DbRepositoryGeneric;
import domain.*;
import java.util.ArrayList;
import java.util.List;
import operation.AbstractGenericOperation;
import operation.autor.UcitajListuAutora;
import operation.bibliotekar.LogovanjeSO;
import operation.clan.DodajClana;
import operation.clan.IzmeniClana;
import operation.clan.ObrisiClana;
import operation.clan.PretraziClana;
import operation.clan.UcitajListuClanova;
import operation.knjiga.DodajKnjigu;
import operation.knjiga.IzmeniKnjigu;
import operation.knjiga.ObrisiKnjigu;
import operation.knjiga.PretraziKnjigu;
import operation.knjiga.UcitajListuKnjiga;
import operation.zaduzenje.DodajZaduzenja;
import operation.zaduzenje.RazduziKnjige;
import operation.zaduzenje.UcitajListuZaduzenja;

import operation.zanr.UcitajListuZanrova;

import repository.Repository;
import threads.ClientHandler;

/**
 *
 * @author Jelena
 */
public class Controller {
    private final Repository repositoryGeneric;
    private static Controller controller;
    private ArrayList<ClientHandler> aktivniKorisnici;
    public Controller() {
        repositoryGeneric = new DbRepositoryGeneric();
        aktivniKorisnici=new ArrayList<>();
    }
    
    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }
    
    public Object login(Object obj) throws Exception{
        LogovanjeSO login=new LogovanjeSO();
        System.out.println(obj.toString());
        login.execute(obj);
        System.out.println(obj.toString());
        System.out.println(login.getBibliotekar().toString());
        return login.getBibliotekar();
          
    }

     public List<Bibliotekar> vratiBibliotekare(Bibliotekar b) throws Exception {
        List<Bibliotekar> bibliotekari = repositoryGeneric.getAll(b);
        return bibliotekari;
    } 
    public List<Autor> vratiAutore() throws Exception {
        UcitajListuAutora ula = new UcitajListuAutora();
        ula.execute(new Autor());
        return ula.getAutori();
    }

    public List<Zanr> vratiZanrove() throws Exception {
        UcitajListuZanrova ulz = new UcitajListuZanrova();
        ulz.execute(new Zanr());
        return ulz.getZanrovi();
    }

    public Object dodajClana(Clan c) throws Exception {
        DodajClana dc = new DodajClana(c);
        
        try {
            dc.execute(c);
            return dc.getZaUnos();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex;
        }
    }

    public boolean vecJeUlogovan(Bibliotekar b) {
         for (ClientHandler klijent : aktivniKorisnici) {
            if (klijent.getBibliotekar().equals(b)) {
                return true;
            }
        }
        return false;
    }

   public ArrayList<ClientHandler> getAktivniKorisnici() {
        return aktivniKorisnici;
    }

     public void setAktivniKorisnici(ArrayList<ClientHandler> aktivniKorisnici) {
        this.aktivniKorisnici = aktivniKorisnici;
    }
     public List<Clan> vratiClanove() throws Exception{
        
        UcitajListuClanova ulc=new UcitajListuClanova();
        ulc.execute(new Clan());
        return ulc.vratiSveClanove();
    }
     
     public List<Clan> vratiClanovePoUslovu(Clan clan) throws Exception{
        PretraziClana pc=new PretraziClana();
        pc.execute(clan);
        return pc.getClanovi();
    }

    public Object dodajKnjigu(Knjiga k) {
         DodajKnjigu dk = new DodajKnjigu(k);
        
        try {
            dk.execute(k);
            return dk.getZaUnos();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex;
        }
    }

    public List<Knjiga> vratiKnjigePoUslovu(Knjiga knjiga) throws Exception {
        PretraziKnjigu pk=new PretraziKnjigu();
        pk.execute(knjiga);
        return pk.getKnjige();
    }

    public List<Knjiga> vratiKnjige() throws Exception {
        UcitajListuKnjiga ulk=new UcitajListuKnjiga();
        ulk.execute(new Knjiga());
        return ulk.vratiSveKnjige();
    }
    public Object obrisiClana(Clan clan)throws Exception{
        ObrisiClana oc=new ObrisiClana();
        oc.execute(clan);
        return StatusOdgovora.Ok.toString();
    }
     public Object izmeniClana(Clan clan) throws Exception {
        IzmeniClana ic=new IzmeniClana();
        ic.execute(clan);
        return StatusOdgovora.Ok;
    }

    public Object izmeniKnjigu(Knjiga knjiga) throws Exception {
        IzmeniKnjigu ik=new IzmeniKnjigu();
        ik.execute(knjiga);
        return StatusOdgovora.Ok.toString();
    }

    public Object obrisiKnjigu(Knjiga knjiga) throws Exception {
        ObrisiKnjigu  ok=new ObrisiKnjigu();
        ok.execute(knjiga);
        return StatusOdgovora.Ok.toString();
    }

    public Object vratiZaduzenja(Clan clan) throws Exception {
        UcitajListuZaduzenja ulz=new UcitajListuZaduzenja();
        ulz.execute(clan);
        return ulz.vratiSvaZaduzenja();
    }

    public Object dodajZaduzenja(List<Zaduzenje> zaduzenja) {
         DodajZaduzenja dz = new DodajZaduzenja();
        
        try {
            for(Zaduzenje z:zaduzenja){
                dz.execute(z);
            }
            
            return StatusOdgovora.Ok;
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex;
        }
    }

    public Object RazduziKnjige(List<Zaduzenje> razduzenja) {
        RazduziKnjige rk = new RazduziKnjige();
         try {
            for(Zaduzenje z:razduzenja){
                System.out.println(z.getBrojZaduzenja());
                rk.execute(z);
            }
            
            return StatusOdgovora.Ok;
        } catch (Exception ex) {
            //ex.printStackTrace();
            return ex;
        }
    }

   
}
