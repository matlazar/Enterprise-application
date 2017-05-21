/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.matlazar.ejb.sb;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import org.foi.nwtis.matlazar.rest.klijenti.GMKlijent;
import org.foi.nwtis.matlazar.rest.klijenti.OWMKlijent;
import org.foi.nwtis.matlazar.rest.klijenti.Lokacija;
import org.foi.nwtis.matlazar.web.podaci.MeteoPrognoza;

/**
 *
 * @author Matija Lazar
 */
@Stateless
@LocalBean
public class MeteoIoTKlijent {
    private String apiKey = null;

    public void postaviKorisnickePodatke(String apiKey) {
        this.apiKey = apiKey;
    }

    public Lokacija dajLokaciju(String adresa) {
        GMKlijent gmk = new GMKlijent();
        Lokacija lok = gmk.getGeoLocation(adresa);
        return lok;
    }

    public MeteoPrognoza[] dajMeteoPrognoze(int id, String adresa) {
        OWMKlijent owmk = new OWMKlijent(apiKey);
        Lokacija lok = dajLokaciju(adresa);
        MeteoPrognoza[] mps = owmk.getWeatherForecast(id, lok.getLatitude(), lok.getLongitude());
        return mps;
    }
}
