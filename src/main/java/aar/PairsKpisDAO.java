package aar;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PairsKpisDAO {
	
	Logger log = Logger.getLogger(PairsKpisDAO.class.getName());

    final DatabaseService d = new DatabaseService();
	

    public int addPair(String name, String name2, Integer id1, Integer id2) {
        try {
            PairsKpis pair = new  PairsKpis(name, name2, id1, id2);
            //Se comprueba que no este en la base de datos antes de añadirla.
            if (pair.equals(d.readPair(id1, id2))){
            	if (pair.equals(d.readPair(id2, id1))) {
            		return 0;
            	}else {
            		d.insertPair(pair);
            	}
            }else {
            	d.insertPair(pair);
            }
            
        } catch (Exception ex) {	
           log.log(Level.SEVERE, null, ex);
        }		
        return 1;
    }
	
    public List<PairsKpis> getAllPairs() {
    	try {
    		return d.findAllPairs();
    	} catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
    	}
    	return null;
    }
   

    public KPI getPair(Integer id) {
        return d.read(id);
    }
    
    public PairsKpis getPair(Integer id1, Integer id2) {
    	return d.readPair(id1, id2);
    }
    
    public boolean deletePair(Integer id1, Integer id2) {
    	int id = id1+ id2;
    	return d.delete(id);
    }

}