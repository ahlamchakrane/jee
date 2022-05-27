package ma.enset.ProjetJEE.entities;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data  @AllArgsConstructor @NoArgsConstructor
public class AppUser {
	@Id 
	@Column(length=20)
	private String id;
	@Column(unique = true, length=20)
	private String username;
	private String password;
	private boolean active;
	@ManyToMany( fetch = FetchType.EAGER)
	private List<AppRole> appRoles = new ArrayList<>();

}
