package aar;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
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

   public PairsKpis() {}
   
   public PairsKpis(String name1, String name2, Integer id1, Integer id2) {
      this.name1 = name1;
      this.name2 = name2;
      this.id = id1+id2;
      this.id1 = id1;
      this.id2 = id2;
      
      //Genaramos el primer valor para la comparación
      comparation = 123456789;
      
   }

   public int getId() {
      return id;
   }

   @XmlElement
   public void setId(int id) {
      this.id = id;
   }
   public String getName() {
      return name1;
   }
   @XmlElement
   public void setName(String name1) {
      this.name1 = name1;
   }
   @XmlElement
   public void setName2(String name2) {
      this.name2 = name2;
   }
   @XmlElement
   public void setid1(int id1) {
	   this.id1 = id1;
   }
   @XmlElement
   public void setid2(int id2) {
	   this.id2 = id2;
   }
   @XmlElement
   public void setComparation(int comparation) {
	   this.comparation = comparation;
   }
		
		
}
