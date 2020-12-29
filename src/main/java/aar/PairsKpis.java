package aar;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class PairsKpis implements Serializable {
   
   private static final long serialVersionUID = 1L;
   
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   private int id;

   @Column(nullable = false)
   private String name;
   
   @Column(nullable = false)
   private String name2;

   @Column(nullable = false)
   private int comparacion;

   public PairsKpis() {}
   
   public PairsKpis(String name, String name2) {
      this.name = name;
      
   }

   public int getId() {
      return id;
   }

   @XmlElement
   public void setId(int id) {
      this.id = id;
   }
   public String getName() {
      return name;
   }
   @XmlElement
   public void setName(String name) {
      this.name = name;
   }
		
}
