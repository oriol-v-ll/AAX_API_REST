package aar;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KPIDao {

	Logger log = Logger.getLogger(KPIDao.class.getName());

	final DatabaseService d = new DatabaseService();

	public int addKpi(String name) {
		try {
			KPI kpi = new KPI(name);
			d.insert(kpi);
		} catch (Exception ex) {
			log.log(Level.SEVERE, null, ex);
		}
		return 1;
	}

	public List<KPI> getAllKpis() {
		try {
			return d.findAll();
		} catch (Exception ex) {
			log.log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public KPI getkpi(Integer id) {
		return d.read(id);
	}

	public boolean deleteKpi(Integer id) {
		return d.delete(id);
	}

}