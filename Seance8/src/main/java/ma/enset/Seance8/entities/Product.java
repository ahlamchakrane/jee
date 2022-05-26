package ma.enset.Seance8.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Product {
	@Id @Column(length=50)
	private String id;
	private String name;
	private double price;
	private double quantity;
	@ManyToOne
	private Category category;
}
