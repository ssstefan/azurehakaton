/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.hakaton16.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.EntityManager;
import rs.fon.hakaton16.domains.Buildings;

/**
 *
 * @author stefan
 */
public class Domina {

//Student restaurant,Reading room,TV room,Disco,Gymnasium,Sports courts,Laundries,Cafe,Copier,Store,Info kiosk,Small hall,Big hall,Infirmary,Post office,Gym,Hairdresser,Student club,Drawing office,Fitness center,Rehabilitation center,Restaurant,Shared bathrooms,Video surveillance,Music room,Room for parents,Bookstore,Library,Student organizations,Chapel,Museum
    private int[] features;
    private BigDecimal lokacijax;
    private BigDecimal lokacijay;
    private double udaljenostOdFaksa;
    private double jacinaDoma = 0;
    private Integer id;
    private String ime;

    public Domina(String featuresWeb, BigDecimal x, BigDecimal y, String ime, Integer id) {
        this.id = id;
        this.ime = ime;
        this.lokacijax = x;
        this.lokacijay = y;
        int[] fWeb = obradifWeb(featuresWeb);

        this.features = new int[27];

        for (int i = 0; i < 27; i++) {
            this.features[i] = 0;
        }

        for (int f : fWeb) {
            this.features[f] = 1;
        }
    }

    private int[] obradifWeb(String featuresWeb) {
        String[] fws = featuresWeb.split(",");
        int[] nizFeaturea = new int[fws.length];
        int i = 0;
        for (String fw : fws) {
            nizFeaturea[i++] = Integer.parseInt(fw);
        }
        return nizFeaturea;
    }

    public int[] getFeatures() {
        return features;
    }

    public void setFeatures(int[] features) {
        this.features = features;
    }

    public BigDecimal getLokacijax() {
        return lokacijax;
    }

    public void setLokacijax(BigDecimal lokacijax) {
        this.lokacijax = lokacijax;
    }

    public BigDecimal getLokacijay() {
        return lokacijay;
    }

    public void setLokacijay(BigDecimal lokacijay) {
        this.lokacijay = lokacijay;
    }

    public static List<Buildings> dajRangirane(EntityManager em, List<Domina> domine, int[] nizRangFeatura, Buildings faks) {
        double minUdaljenost = 99;
        for (Domina d : domine) {
            int jacinaDoma = 0;
            for (int i = 2; i < nizRangFeatura.length; i++) {
                jacinaDoma += d.features[i - 1] * (nizRangFeatura[i] - 1);
            }
            double razlikaX = d.getLokacijax().doubleValue() - faks.getLongitude().doubleValue();
            double razlikaY = d.getLokacijay().doubleValue() - faks.getLatitude().doubleValue();
            d.setUdaljenostOdFaksa(Math.sqrt(Math.pow(razlikaX, 2) + Math.pow(razlikaY, 2)));
            if (d.getUdaljenostOdFaksa() < minUdaljenost) {
                minUdaljenost = d.getUdaljenostOdFaksa();
            }
            d.setJacinaDoma(jacinaDoma);
        }

        for (Domina d : domine) {
            d.setUdaljenostOdFaksa(minUdaljenost / d.getUdaljenostOdFaksa());
            d.setJacinaDoma(d.getJacinaDoma() + d.getUdaljenostOdFaksa() * (nizRangFeatura[1] - 1));
        }

        Collections.sort(domine, new Comparator<Domina>() {
            @Override
            public int compare(Domina o1, Domina o2) {
                return (int)(o1.getJacinaDoma()*100 - o2.getJacinaDoma()*100);
            }
        });
        List<Buildings> najboljiDomovi = new ArrayList<>();
        for (int i = domine.size()-1; i > domine.size() - 4 ; i--) {
            Buildings dom = em.createNamedQuery("Buildings.findById", Buildings.class).setParameter("id", domine.get(i).getId()).getSingleResult();
            najboljiDomovi.add(dom);
        }
        return najboljiDomovi;
    }

    public double getJacinaDoma() {
        return jacinaDoma;
    }

    public void setJacinaDoma(double jacinaDoma) {
        this.jacinaDoma = jacinaDoma;
    }

    public double getUdaljenostOdFaksa() {
        return udaljenostOdFaksa;
    }

    public void setUdaljenostOdFaksa(double udaljenostOdFaksa) {
        this.udaljenostOdFaksa = udaljenostOdFaksa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

}
