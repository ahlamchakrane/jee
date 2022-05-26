package ma.enset.Seance8.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Category {
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String name;
	@OneToMany(mappedBy = "category")
	//@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) solution de bricolage
	private List<Product> products= new ArrayList<>();
}
