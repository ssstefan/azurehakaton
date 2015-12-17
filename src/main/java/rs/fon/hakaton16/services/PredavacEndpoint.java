/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.hakaton16.services;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rs.fon.hakaton16.domains.Kurs;
import rs.fon.hakaton16.domains.OdslusaniKursevi;
import rs.fon.hakaton16.domains.Predavac;
import rs.fon.hakaton16.emf.EMF;
import rs.fon.hakaton16.exception.HakatonException;

/**
 *
 * @author stefan
 */
@Path("/predavaci")
public class PredavacEndpoint {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPredavaci(@QueryParam("rankGT") int rankGT) {
        EntityManager em = EMF.createEntityManager();
        List<Predavac> predavaci = em.createNamedQuery("Predavac.findAll", Predavac.class).getResultList();
        List<Predavac> pl = new ArrayList<>();
        for (Predavac p : predavaci) {
            if (p.getRank() > rankGT) {
                pl.add(p);
            }
        }
        em.close();
        return Response.ok().entity(pl).build();
    }
    
    @GET
    @Path("/top")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopTen() {
        EntityManager em = EMF.createEntityManager();
        List<Predavac> predavaci = em.createNamedQuery("Predavac.findAll", Predavac.class).setMaxResults(10).getResultList();
        em.close();
        return Response.ok().entity(predavaci).build();
    }

    @POST
    @Path("/slusaj_kurs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOdslusaniKursevi(OdslusaniKursevi ok) {
        EntityManager em = EMF.createEntityManager();

        rs.fon.hakaton16.emf.EM.persist(em, ok);
        em.close();
        return Response.ok().build();
    }

    @GET
    @Path("/odslusani_kursevi/{idpredavac}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOdslusaniKursevi(@PathParam("idpredavac") int idpredavac) {
        EntityManager em = EMF.createEntityManager();

        String kveri = "Select k from OdslusaniKursevi k where k.idpredavac=" + idpredavac;
        List<OdslusaniKursevi> kursevi = em.createQuery(kveri).getResultList();

        em.close();
        return Response.ok().entity(kursevi).build();
    }
    
    @GET
    @Path("/{ime}/{sifra}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@PathParam("ime") String ime, @PathParam("sifra") String sifra) {
        EntityManager em = EMF.createEntityManager();
        Predavac predavac = null;
        try {
            predavac = em.createNamedQuery("Predavac.findByIme", Predavac.class).setParameter("ime", ime).getSingleResult();
        } catch (Exception e) {
            throw new HakatonException("Student sa zadatim imenom ne postoji.");
        }
        
        if (!predavac.getSifra().equals(sifra)) {
            throw new HakatonException("Uneta sifra nije tacna.");
        }
        
        em.close();
        return Response.ok().entity(predavac).build();
    }

//    @GET
//    @Path("/{idkursa}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getOdslusaniKursevi(@PathParam("idkursa") int idkursa) {
//        EntityManager em = EMF.createEntityManager();
//        List<OdslusaniKursevi> odsl = em.createNamedQuery("OdslusaniKursevi.findByPredavac", OdslusaniKursevi.class).setParameter("idkursa", idkursa).getResultList();
//        em.close();
//        return Response.ok().entity(odsl).build();
//    }
}
