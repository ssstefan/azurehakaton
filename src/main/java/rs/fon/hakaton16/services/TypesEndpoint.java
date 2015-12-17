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
import rs.fon.hakaton16.domains.Types;
import rs.fon.hakaton16.emf.EMF;

/**
 *
 * @author stefan
 */
@Path("/types")
public class TypesEndpoint {
 
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTypes() {
        EntityManager em = EMF.createEntityManager();
        List<Types> types = em.createNamedQuery("Types.findAll", Types.class).getResultList();
        em.close();
        return Response.ok().entity(types).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTypesById(@PathParam("id") Integer id) {
        EntityManager em = EMF.createEntityManager();
        Types o = em.createNamedQuery("Types.findById", Types.class).setParameter("id", id).getSingleResult();
        em.close();
        return Response.ok().entity(o).build();
    }
    
}
