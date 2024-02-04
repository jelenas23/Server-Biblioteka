/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.sun.java.accessibility.util.AWTEventMonitor;
import communication.Communication;
import cordinator.Coordinator;
import domain.Bibliotekar;
import form.frmLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jelena
 */
public class LoginController {
    private final frmLogin loginForma ;

    public LoginController(frmLogin loginForma) {
        this.loginForma = loginForma;
        addActionListener();
    }

    private void addActionListener() {
        loginForma.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    loginUser(actionEvent);
                } catch (Exception ex) {
                   JOptionPane.showMessageDialog(loginForma,"Sistem ne može da pronađe bibliotekara na osnovu unetih podataka!", "Greška pri logovanju!", JOptionPane.ERROR_MESSAGE);
                }
            }

            private void loginUser(ActionEvent actionEvent) throws Exception{
               
                    String korisnicko = loginForma.getTxtKorisnicko().getText().trim();
                    String lozinka = String.copyValueOf(loginForma.getTxtLozinka().getPassword());
                    System.out.println(korisnicko + " / "+lozinka);
                    proveriPodatke(korisnicko,lozinka);
                   
                    Bibliotekar b = communication.Communication.getInstance().login(korisnicko, lozinka);
                    JOptionPane.showMessageDialog(
                            loginForma,
                            "Uspešno ste se prijavili na sistem." ,
                            "Login", JOptionPane.INFORMATION_MESSAGE
                    );
                    loginForma.dispose();
                    Coordinator.getInstance().addParam(konstante.Konstante.trenutni_korisnik, b);
                    Coordinator.getInstance().otvoriMainFormu();
                
            }

           

            private void proveriPodatke(String korisnicko,String lozinka) throws Exception {
                
               List<Bibliotekar> bibliotekari=Communication.getInstance().vratiBibliotekare();
               boolean ulogovan=false;
               for(Bibliotekar b:bibliotekari){
                   if(b.getKorisnickoIme().equals(korisnicko) && b.getLozinka().equals(lozinka)){
                       ulogovan=true;
                       break;
                   }
               }
               if(!ulogovan) {
                   throw new Exception("");
               }
            }
                
            
        });
    }
    public void otvoriLoginFormu(){
        loginForma.setVisible(true);
        loginForma.setLocationRelativeTo(null);
        loginForma.setTitle("Log in");
    }
    }
    

