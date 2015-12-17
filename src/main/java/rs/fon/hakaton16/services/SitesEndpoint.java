/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.hakaton16.services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rs.fon.hakaton16.domains.Sites;
import rs.fon.hakaton16.emf.EMF;

/**
 *
 * @author stefan
 */
@Path("/sites")
public class SitesEndpoint {
    
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSites() {
        EntityManager em = EMF.createEntityManager();
        List<Sites> sit = em.createNamedQuery("Sites.findAll", Sites.class).getResultList();
        em.close();
        return Response.ok().entity(sit).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSitesById(@PathParam("id") String id) {
        EntityManager em = EMF.createEntityManager();
        Sites o = em.createNamedQuery("Sites.findByIdVarchar", Sites.class).setParameter("idVarchar", id).getSingleResult();
        em.close();
        return Response.ok().entity(o).build();
    }
    
}
