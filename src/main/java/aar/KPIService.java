package aar;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/KPI")
public class KPIService {

  
   KPIDao kpiDao = new KPIDao();
   PairsKpisDAO PairsDao = new PairsKpisDAO();
   
   Logger log = Logger.getLogger(KPIService.class.getName());

   
   @GET
   @Path("/kpis")
   @Produces(MediaType.APPLICATION_JSON)
   public List<KPI> getKpis() {	   
	   return kpiDao.getAllKpis();
   }
   
   @GET
   @Path("/pairs")
   @Produces(MediaType.APPLICATION_JSON)
   public List<PairsKpis> getPairs() {	   
	   return PairsDao.getAllPairs();
   }
   
   @GET
   @Path("/pairs/current")
   @Produces(MediaType.APPLICATION_JSON)
   public List<KPI> getKpis2() {	   
	   return kpiDao.getAllKpis();
   }
   
   @GET
   @Path("/pairs/max")
   @Produces(MediaType.APPLICATION_JSON)
   public List<KPI> getKpis3() {	   
	   return kpiDao.getAllKpis();
   }
   
   @POST
   @Path("/kpis")
   @Consumes("application/x-www-form-urlencoded")
   public Response addKpi(@FormParam("name") String name) {
	   try {
		   kpiDao.addKpi(name);
		   log.log(Level.INFO, "Inserted kpi "+name);
      
		   return Response.status(200)
				   .entity("addKpi -> name: " + name )
				   .build();
      } catch(Exception e) {
    	  return Response.status(400).build();
      }
   }

   @POST
   @Path("/pairs")
   @Consumes("application/x-www-form-urlencoded")
   public Response addPair(@FormParam("KPI1") String name, @FormParam("KPI2") String name2) {
	   try {
		   PairsDao.addPair(name, name2);
		   log.log(Level.INFO, "Inserted user "+name);
      
		   return Response.status(200)
				   .entity("addPair -> name: " + name + ", name2: " + name2)
				   .build();
      } catch(Exception e) {
    	  return Response.status(400).build();
      }
   }

   @DELETE
   @Path("/kpis")
   @Consumes("application/x-www-form-urlencoded")
   public Response removeKpi(@FormParam("id") Integer id) {
      try {
    	  boolean deletedOk = kpiDao.deleteKpi(id);
      
    	  if(deletedOk == true) 
    		  log.log(Level.INFO, "deleted KPI "+id+" correctly ");
      
    	  return Response.status(200)
    			  .entity("Deleted KPI with id: " + id + " correctly ")
    			  .build();
      } catch(Exception e) {
    	  return Response.status(400).build();
      }
   }
   
   @DELETE
   @Path("/pairs")
   @Consumes("application/x-www-form-urlencoded")
   public Response removeUser(@FormParam("id") Integer id,@FormParam("id2") Integer id2 ) {
      try {
    	  boolean deletedOk = PairsDao.deletePair(id, id2);
      
    	  if(deletedOk == true) 
    		  log.log(Level.INFO, "deleted pair "+id+"/"+id2+" correctly ");
      
    	  return Response.status(200)
    			  .entity("deleted pair "+id+"/"+id2+" correctly ")
    			  .build();
      } catch(Exception e) {
    	  return Response.status(400).build();
      }
   }
     
}