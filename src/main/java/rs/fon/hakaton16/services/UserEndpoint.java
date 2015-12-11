/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.hakaton16.services;

import com.sun.jersey.api.core.InjectParam;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rs.fon.hakaton16.domains.Appuser;
import rs.fon.hakaton16.emf.EM;
import rs.fon.hakaton16.emf.EMF;

/**
 *
 * @author stefan
 */
@Path("/users")
public class UserEndpoint {
    
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(@HeaderParam("Authorization") String authorization) {
        EntityManager em = EMF.createEntityManager();
        List<Appuser> users = em.createNamedQuery("Appuser.findAll", Appuser.class).getResultList();
        return Response.ok().entity(users).build();
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertUser(Appuser user) {
        EntityManager em = EMF.createEntityManager();
        EM.merge(em, user);
        return Response.ok().build();
    }
    
    @GET
    @Path("/{ime}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello(@PathParam("ime") String ime) {
        HashMap hm = new HashMap();
        hm.put("hello", ime);
        return Response.ok().entity(hm).build();
    }
    
//    @GET
//    @Path("/a")
//    @Produces(MediaType.TEXT_PLAIN)
//    public Response hdas(@PathParam("ime") String ime, Users user) {
//        return Response.ok().entity("asffsasaf").build();
//    }
//    
//    @POST
//    @Path("/login")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response login(@HeaderParam("authorization") String authorization) {
//        String[] userPass;
//        EntityManager em = EMF.createEntityManager();
//////        Token token = new Token();
////        try {
//////            userPass = token.check(authorization);
//////            userPass = token.decodeBasicAuth(authorization);
////        } catch (RuntimeException e) {
////            return Response.ok().entity("bzvz").build();
////        }
//        Users user = null;
//        try {
//            user = (Users) em
//                    .createNamedQuery("Users.findByUsername", Users.class)
//                    .setParameter("username", userPass[0])
//                    .getSingleResult();
//        } catch (NoResultException e) {
//            return Response.status(404).build();
//        }
//        
//        if (!user.getPassword().equals(userPass[1])) {
//            return Response.status(400).build();
//        }
//        
//        if (user.getToken() == null || user.getToken().equals("")) {
//            user.setToken(token.makeToken(user.getUsername(), user.getPassword()));
//            EM.merge(em, user);
//        }
//
//        return Response.ok().entity(user).build();
//    }
    
}
