/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.hakaton16.services;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rs.fon.hakaton16.domains.Kurs;
import rs.fon.hakaton16.emf.EMF;
import rs.fon.hakaton16.exception.HakatonException;

/**
 *
 * @author stefan
 */
@Path("/kursevi")
public class KurseviEndpoint {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKursevi(@QueryParam("ime") String ime, @QueryParam("zamena") String zamena, @QueryParam("kapacitet") String kapacitet) {
        EntityManager em = EMF.createEntityManager();
        StringBuilder kveri = new StringBuilder("Select k from Kurs k");
        String operator = " WHERE ";
        if (ime != null) {
            kveri.append(operator).append("k.ime LIKE '%").append(ime).append("%'");
            operator = " AND ";
        }
        if (kapacitet != null) {
            kveri.append(operator).append("k.ime=").append(kapacitet);
            operator = " AND ";
        }
        
        if (zamena != null) {
            kveri.append(operator).append("k.zamena LIKE '%").append(zamena).append("%'");
        }
        List<Kurs> kursevi = em.createQuery(kveri.toString()).getResultList();

        em.close();
        return Response.ok().entity(kursevi).build();
    }

    @GET
    @Path("/prijava/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response prijaviSe(@PathParam("id") Integer id) {
        EntityManager em = EMF.createEntityManager();
        Kurs k = em.createNamedQuery("Kurs.findById", Kurs.class).setParameter("id", id).getSingleResult();
        if (k.getKapacitet() >= k.getPopunjeno() + 1) {
            k.setPopunjeno(k.getPopunjeno() + 1);
            rs.fon.hakaton16.emf.EM.merge(em, k);
        } else {
            throw new HakatonException("Sva mesta su popunjena, pokusajte sa drugim kursom.");
        }
        em.close();
        return Response.ok().entity(k).build();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response napraviKurs(Kurs kurs) {
        EntityManager em = EMF.createEntityManager();
        kurs.setDatumKursa(new Date(System.currentTimeMillis()));
        rs.fon.hakaton16.emf.EM.persist(em, kurs);
        return Response.ok().build();
    }

}
