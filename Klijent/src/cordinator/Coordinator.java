/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cordinator;

import controller.InformacijeClanController;
import controller.InformacijeKnjigaController;
import controller.KreiranjeClanaController;
import controller.KreiranjeKnjigeController;
import controller.LoginController;
import controller.MainController;

import controller.PretrazivanjeClanovaController;
import controller.PretrazivanjeKnjigeController;
import controller.ZaduzenjaClanController;
import domain.Clan;
import domain.Knjiga;
import form.frmClan;
import form.frmKnjiga;
import form.frmKreiranjeClana;
import form.frmKreiranjeKnjige;
import form.frmLogin;
import form.frmMain;
import form.frmPTT;
import form.frmPretrazivanjeClana;
import form.frmPretrazivanjeKnjige;
import form.frmZaduzenja;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author Jelena
 */
public class Coordinator {
    private static Coordinator instance;
    private final MainController mainController;  
    private final Map<String, Object> params;
     private Coordinator() {
        mainController = new MainController(new frmMain());
        params = new HashMap<>();
    }

    public static Coordinator getInstance() {
        if (instance == null) {
            instance = new Coordinator();
        }
        return instance;
    }

    public void otvoriMainFormu() {
        //da li ostaviti main formu da bude otvorena ili je zatvarati?
        mainController.otvoriFormu();
    }
    public void otvoriLoginFormu() {
        LoginController loginC = new LoginController(new frmLogin());
        loginC.otvoriLoginFormu();
       
    }
   
      public void addParam(String name, Object key) {
        params.put(name, key);
    }

    public Object getParam(String name) {
        return params.get(name);
    }

    
    public void otvoriKreirajClanaFormu(){
        KreiranjeClanaController kck = new KreiranjeClanaController(new frmKreiranjeClana(mainController.getFrmMain(), true));
        kck.otvoriFormu();
        //productController.openForm(FormMode.FORM_ADD); - da li je ovo uopste potrebno?
    }

    public void otvoriPretrazivanjeClanovaFormu() throws Exception{
        PretrazivanjeClanovaController pck = new PretrazivanjeClanovaController(new frmPretrazivanjeClana(mainController.getFrmMain(), true));
        pck.otvoriFormu();
    }

    public void otvoriKreirajKnjiguFormu() throws Exception {
        KreiranjeKnjigeController kkc = new KreiranjeKnjigeController(new frmKreiranjeKnjige(mainController.getFrmMain(), true));
        kkc.otvoriFormu();
    }

    public void otvoriPretrazivanjeKnjigeFormu() throws Exception {
        PretrazivanjeKnjigeController pkc = new PretrazivanjeKnjigeController(new frmPretrazivanjeKnjige(mainController.getFrmMain(), true));
        pkc.otvoriFormu();
    }

    public void otvoriFrmClan(Clan clan) {
       InformacijeClanController icc = new InformacijeClanController(new frmClan(mainController.getFrmMain(),true),clan);
       icc.otvoriFormu();
       
    }
       public MainController getMainContoller() {
        return mainController;
    }

    public void otvoriFrmKnjiga(Knjiga knjiga) {
         InformacijeKnjigaController ikc = new InformacijeKnjigaController(new frmKnjiga(mainController.getFrmMain(),true),knjiga);
         ikc.otvoriFormu();
    }

    public void otvoriFrmZaduzenja(Clan clan) throws Exception {
        ZaduzenjaClanController zck = new ZaduzenjaClanController(new frmZaduzenja(mainController.getFrmMain(),true),clan);
        zck.otvoriFormu();
    }

   
    
}
