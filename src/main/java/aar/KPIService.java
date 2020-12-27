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
   
   Logger log = Logger.getLogger(KPIService.class.getName());

   
   @GET
   @Path("/kpis")
   @Produces(MediaType.APPLICATION_XML)
   public List<KPI> getKpis() {	   
	   return kpiDao.getAllKpis();
   }
   
   @GET
   @Path("/pairs")
   @Produces(MediaType.APPLICATION_XML)
   public List<KPI> getKpis2() {	   
	   return kpiDao.getAllKpis();
   }

  /* 
  	
  
   @POST
   @Path("/")
   @Consumes("application/x-www-form-urlencoded")
   public Response addUser(@FormParam("name") String name, @FormParam("profession") String profession) {
	   try {
		   kpiDao.addUser(name, profession);
		   log.log(Level.INFO, "Inserted user "+name);
      
		   return Response.status(200)
				   .entity("addUser -> name: " + name + ", profession: " + profession)
				   .build();
      } catch(Exception e) {
    	  return Response.status(400).build();
      }
   }
   */
   @DELETE
   @Path("/")
   @Consumes("application/x-www-form-urlencoded")
   public Response removeUser(@FormParam("id") Integer id) {
      try {
    	  boolean deletedOk = kpiDao.deleteUser(id);
      
    	  if(deletedOk == true) 
    		  log.log(Level.INFO, "deleted user "+id+" correctly ");
      
    	  return Response.status(200)
    			  .entity("Deleted user with id: " + id + " correctly ")
    			  .build();
      } catch(Exception e) {
    	  return Response.status(400).build();
      }
   }
     
}