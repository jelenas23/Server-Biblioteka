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
public enum Operation implements Serializable{
    LOGOVANJE,
    VRATI_SVE_BIBLIOTEKARE,
    VRATI_SVE_KNJIGE,
    DODAJ_KNJIGU,
    PRETRAZI_KNJIGU,
    iZMENI_KNJIGU,
    OBRISI_KNJIGU,
    VRATI_SVE_CLANOVE,
    DODAJ_CLANA,
    PRETRAZI_CLANA,
    OBRISI_CLANA,
    IZMENI_CLANA,
    ZADUZI_KNJIGE,
    RAZDUZI_KNJIGE,
    VRATI_AUTORE,
    VRATI_ZANROVE,
    VRATI_ZADUZENJA, DODAJ_ZADUZENJA,
    DODAJ_PTT,
    OBRISI_PTT, VRATI_PTT;
    
}
