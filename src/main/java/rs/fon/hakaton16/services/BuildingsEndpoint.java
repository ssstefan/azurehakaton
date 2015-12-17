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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import rs.fon.hakaton16.domains.Buildings;
import rs.fon.hakaton16.domains.Features;
import rs.fon.hakaton16.emf.EMF;

/**
 *
 * @author stefan
 */
@Path("/buildings")
public class BuildingsEndpoint {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBuild() {
        EntityManager em = EMF.createEntityManager();
        List<Buildings> buildings = em.createNamedQuery("Buildings.findAll", Buildings.class).getResultList();
        em.close();
        return Response.ok().entity(buildings).build();
    }

    @GET
    @Path("/{id}/features")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBuildAllFeatures(@PathParam("id") Integer id) {
        EntityManager em = EMF.createEntityManager();
        Buildings building = em.createNamedQuery("Buildings.findById", Buildings.class).setParameter("id", id).getSingleResult();
        String[] features = building.getFeatures().split(",");
        List<Features> features_l = new ArrayList<>();
        for (String feature : features) {
            int f = Integer.parseInt(feature);
            Features feat = em.createNamedQuery("Features.findById", Features.class).setParameter("id", f).getSingleResult();
            features_l.add(feat);
        }
        em.close();
        return Response.ok().entity(features_l).build();
    }

    @GET
    @Path("/locations")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBuildLocations() {
        EntityManager em = EMF.createEntityManager();
        List<Buildings> buildings = em.createNamedQuery("Buildings.findAll", Buildings.class).getResultList();
        List<HashMap> lhm = new ArrayList<>();
        for (Buildings building : buildings) {
            boolean preskoci = false;
            for (HashMap h : lhm) {
                if (((String) h.get("name")).contains(building.getBuildingName().substring(0, building.getBuildingName().length() - 5))) {
                    preskoci = true;
                    break;
                }
            }
            if (preskoci) {
                continue;
            }
            HashMap hm2 = new HashMap();
            hm2.put("id", building.getId());
            hm2.put("name", building.getBuildingName());
            hm2.put("longitude", building.getLongitude());
            hm2.put("latitude", building.getLatitude());
            lhm.add(hm2);
        }

        for (HashMap h : lhm) {
            if (((String) h.get("name")).contains(" -")) {
                h.put("name", ((String)h.get("name")).substring(0, ((String)h.get("name")).indexOf(" -")));
            }
        }
        return Response.ok().entity(lhm).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBuildById(@PathParam("id") Integer id) {
        EntityManager em = EMF.createEntityManager();
        Buildings buildings = em.createNamedQuery("Buildings.findById", Buildings.class).setParameter("id", id).getSingleResult();
        em.close();
        return Response.ok().entity(buildings).build();
    }

    @GET
    @Path("/najbolji_domovi/{rangovi}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBestDom(@PathParam("rangovi") String rangFeatures) {
        EntityManager em = EMF.createEntityManager();
        String[] rang_s = rangFeatures.split(",");
        int[] nizRangFeaturea = new int[rang_s.length];
        int i = 0;
        for (String fw : rang_s) {
            nizRangFeaturea[i++] = Integer.parseInt(fw);
        }
        Integer idFaksa = nizRangFeaturea[0];
        List<Buildings> domovi = em.createNamedQuery("Buildings.findDomovi", Buildings.class).getResultList();
        List<Domina> domine = new ArrayList<>();
        for (Buildings d : domovi) {
            Domina dom = new Domina(d.getFeatures(), d.getLongitude(), d.getLatitude(), d.getBuildingName(), d.getId());
            domine.add(dom);
        }
        Buildings faks = em.createNamedQuery("Buildings.findById", Buildings.class).setParameter("id", idFaksa).getSingleResult();
        return Response.ok().entity(Domina.dajRangirane(em, domine, nizRangFeaturea, faks)).build();
    }

    @GET
    @Path("/domovi")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDomovi() {
        EntityManager em = EMF.createEntityManager();
        List<Buildings> buildings = em.createNamedQuery("Buildings.findDomovi", Buildings.class).getResultList();
        em.close();
        return Response.ok().entity(buildings).build();
    }

    @GET
    @Path("/citaonice")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCitaonice() {
        EntityManager em = EMF.createEntityManager();
        List<Buildings> buildings = em.createNamedQuery("Buildings.findAll", Buildings.class).getResultList();
        List<Buildings> citaonice = new ArrayList<>();
        for (Buildings building : buildings) {
            if (building.getFeatures() == null) {
                continue;
            }
            String feature = building.getFeatures();
            if (feature.contains("2,")) {
                citaonice.add(building);
            }
        }
        em.close();
        return Response.ok().entity(citaonice).build();
    }
    
    @GET
    @Path("/menze")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMenze() {
        EntityManager em = EMF.createEntityManager();
        List<Buildings> buildings = em.createNamedQuery("Buildings.findAll", Buildings.class).getResultList();
        List<Buildings> menza = new ArrayList<>();
        for (Buildings building : buildings) {
            if (building.getFeatures() == null) {
                continue;
            }
            String feature = building.getFeatures();
            if (feature.contains("1,")) {
                menza.add(building);
            }
        }

        em.close();
        return Response.ok().entity(menza).build();
    }
    
    @GET
    @Path("/rekreacije")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRekreacije() {
        EntityManager em = EMF.createEntityManager();
        List<Buildings> buildings = em.createNamedQuery("Buildings.findAll", Buildings.class).getResultList();
        List<Buildings> rekreacije = new ArrayList<>();
        for (Buildings building : buildings) {
            if (building.getFeatures() == null) {
                continue;
            }
            String feature = building.getFeatures();
            if (feature.contains("5,")) {
                rekreacije.add(building);
                continue;
            }
            if (feature.contains("6,")) {
                rekreacije.add(building);
            }
        }
        em.close();
        return Response.ok().entity(rekreacije).build();
    }
    

    @GET
    @Path("/faks_features")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFacFeat() {
        EntityManager em = EMF.createEntityManager();
        List<Buildings> build = em.createNamedQuery("Buildings.findByIdGThen", Buildings.class).setParameter("id", 38).getResultList();
        List<HashMap> lhm = new ArrayList<>();
        for (Buildings b : build) {
            HashMap hm2 = new HashMap();
            hm2.put("id", b.getId());
            hm2.put("name", b.getBuildingName());
            lhm.add(hm2);
        }
        List<Features> feat = em.createNamedQuery("Features.findByIdLThen", Features.class).setParameter("id", 27).getResultList();

        List<Object> obj = new ArrayList<>();
        obj.add(lhm);
        obj.add(feat);
        return Response.ok().entity(obj).build();
    }
}
