package ma.enset.Seance5.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Role {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, length = 20)
	private String roleName;
	@Column(name = "description") //au niveau mySQL il faut renommer
	private String desc;
	@ManyToMany(fetch = FetchType.LAZY)
	//la table association
	@JoinColumn(name = "Users_Roles")
	//ne pas ecrire la liste des utilisateurs dans la fonction to string pour ne pas tourner dans une boucle infinie
	@ToString.Exclude
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<User> users= new ArrayList<>();
	
}
