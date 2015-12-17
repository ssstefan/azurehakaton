/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.hakaton16.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rs.fon.hakaton16.domains.Citaonica;
import rs.fon.hakaton16.domains.Student;
import rs.fon.hakaton16.emf.EMF;
import rs.fon.hakaton16.emf.EM;
import rs.fon.hakaton16.exception.HakatonException;

/**
 *
 * @author stefan
 */
@Path("/citaonice")
public class CitaonicaEndpoint {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sveCitaonice() {
        EntityManager em = EMF.createEntityManager();
        List<Citaonica> buildings = em.createNamedQuery("Citaonica.findAll", Citaonica.class).getResultList();
        em.close();
        return Response.ok().entity(buildings).build();
    }
//    http://192.168.1.114:8084/Hakaton16/rest/citaonice/dodaj/?ime=value&sifra=value&status=value&predmet=value&poruka=value&citaonica_id=value

    @GET
    @Path("/dodaj")
    @Produces(MediaType.APPLICATION_JSON)
    public Response dodajStudentaUCitaonicu(@QueryParam("ime") String ime, @QueryParam("sifra") String sifra, @QueryParam("status") String status, @QueryParam("predmet") String predmet, @QueryParam("poruka") String poruke, @QueryParam("citaonica_id") Integer citaonica_id) {
        EntityManager em = EMF.createEntityManager();
        List<Student> studenti = em.createNamedQuery("Student.findByIme", Student.class).setParameter("ime", ime).getResultList();
        Student student = new Student();
        student.setIme(ime);
        student.setPoruka(poruke);
        student.setPredmet(predmet);
        student.setSifra(sifra);
        Citaonica citaonica = em.createNamedQuery("Citaonica.findById", Citaonica.class).setParameter("id", citaonica_id).getSingleResult();
        student.setCitaonicaid(citaonica);

        if(studenti == null || studenti.size() == 0) {
            EM.merge(em, student);
            if (citaonica.getKapacitet() < citaonica.getPopunjenost()+1) {
                throw new HakatonException("Nema vise mesta u citaonici, proverite u okolini");
            }
            citaonica.setPopunjenost(citaonica.getPopunjenost() + 1);
            EM.merge(em, citaonica);
        }
        Citaonica citaona = em.createNamedQuery("Citaonica.findById", Citaonica.class).setParameter("id", citaonica_id).getSingleResult();
        List<Student> studenti124 = em.createNamedQuery("Student.findByCitaonica", Student.class).setParameter("citaonicaid", citaona).getResultList();
        for (Student s : studenti124) {
            s.setCitaonicaid(null);
        }
        List<Object> obj = new ArrayList<>();
        obj.add(studenti124);
        HashMap hm = new HashMap();
        Student s = em.createNamedQuery("Student.findByIme",  Student.class).setParameter("ime", student.getIme()).getSingleResult();
        hm.put("mojid", s.getId());
        obj.add(hm);
        em.close();
        return Response.ok().entity(obj).build();
    }

    @GET
    @Path("/izbaci")
    @Produces(MediaType.APPLICATION_JSON)
    public Response izadjiIzCitaonice(@QueryParam("ime") String ime, @QueryParam("citaonica_id") Integer citaonica_id) {
        EntityManager em = EMF.createEntityManager();
        List<Student> studentiZaIzbacivanje = em.createNamedQuery("Student.findByIme", Student.class).setParameter("ime", ime).getResultList();
        for (Student student : studentiZaIzbacivanje) {
            EM.remove(em, student);
        }

        Citaonica citaonica = em.createNamedQuery("Citaonica.findById", Citaonica.class).setParameter("id", citaonica_id).getSingleResult();
        if (citaonica.getPopunjenost() <= 0) {
            throw new HakatonException("Citaonica je prazna");
        }
        citaonica.setPopunjenost(citaonica.getPopunjenost() - 1);
        EM.merge(em, citaonica);
        em.close();
        return Response.ok().build();
    }

    @GET
    @Path("/{citaonica_id}/studenti")
    @Produces(MediaType.APPLICATION_JSON)
    public Response dajStudenteUCitaonici(@PathParam("citaonica_id") int citaonica_id) {
        EntityManager em = EMF.createEntityManager();
        Citaonica citaona = em.createNamedQuery("Citaonica.findById", Citaonica.class).setParameter("id", citaonica_id).getSingleResult();
        List<Student> studenti = em.createNamedQuery("Student.findByCitaonica", Student.class).setParameter("citaonicaid", citaona).getResultList();
        return Response.ok().entity(studenti).build();
    }
}
