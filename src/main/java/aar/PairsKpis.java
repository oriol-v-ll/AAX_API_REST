package aar;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class PairsKpis implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(nullable = false)
	private int id1;
	@Column(nullable = false)
	private int id2;

	@Column(nullable = false)
	private String name1;

	@Column(nullable = false)
	private String name2;

	@Column(nullable = false)
	private int comparation;

	@Column(nullable = false)
	static private int max;

	static {
		max = 0;

	}

	public PairsKpis() {
	}

	public PairsKpis(String name1, String name2, Integer id1, Integer id2) {
		this.name1 = name1;
		this.name2 = name2;
		this.id = id1 + id2;
		this.id1 = id1;
		this.id2 = id2;
		generateComparation();

	}

	/**
	 * Función que genera un valor aleatorio desde la API proporcionada
	 * 
	 */
	public void generateComparation() {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://www.randomnumberapi.com/api/v1.0/random?min=10&max=99&count=1");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

		String response = invocationBuilder.get(String.class);
		String comparationString = response.substring(1, 3);
		int comparationFinal = Integer.parseInt(comparationString);
		this.comparation = comparationFinal;

		if (comparationFinal > max) {
			setMax(comparationFinal);
		}

	}

	public int getId() {
		return id;
	}

	static public void setMax(int newMax) {
		max = newMax;
	}

	@XmlElement
	public void setId(int id) {
		this.id = id;
	}

	static public int getMax() {
		return max;
	}

	public String getName1() {
		return name1;
	}

	@XmlElement
	public void setName(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public int getid1() {
		return id1;
	}

	public int getid2() {
		return id2;
	}

	public int getComparation() {
		return comparation;
	}

}
