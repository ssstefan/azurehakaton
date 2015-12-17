/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.hakaton16.services;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rs.fon.hakaton16.domains.Features;
import rs.fon.hakaton16.emf.EMF;

/**
 *
 * @author stefan
 */
@Path("/features")
public class FeatuesEndpoint {
    
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSites() {
        EntityManager em = EMF.createEntityManager();
        List<Features> sit = em.createNamedQuery("Features.findAll", Features.class).getResultList();
        em.close();
        return Response.ok().entity(sit).build();
    }
    
    @GET
    @Path("/names")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSitesN() {
        EntityManager em = EMF.createEntityManager();
        List<Features> sit = em.createNamedQuery("Features.findAll", Features.class).getResultList();
        List<String> s = new ArrayList<>();
        for (Features sit1 : sit) {
            s.add(sit1.getEn());
        }
        em.close();
        return Response.ok().entity(s).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFById(@PathParam("id") Integer id) {
        EntityManager em = EMF.createEntityManager();
        Features f = em.createNamedQuery("Features.findById", Features.class).setParameter("id", id).getSingleResult();
        em.close();
        return Response.ok().entity(f).build();
    }
    
}
