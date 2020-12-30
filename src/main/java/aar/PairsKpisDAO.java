package aar;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PairsKpisDAO {
	
	Logger log = Logger.getLogger(PairsKpisDAO.class.getName());

    final DatabaseService d = new DatabaseService();
	

    public int addPair(String name, String name2) {
        try {
            PairsKpis pair = new  PairsKpis(name, name2);
            d.insertPair(pair);
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
    public boolean deletePair(Integer id, Integer id2) {
    	return d.delete(id);
    }

}