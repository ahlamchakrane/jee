package ma.enset.Seance5.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "users")
@Data @NoArgsConstructor @AllArgsConstructor
public class User {
	@Id
	@Column(length = 50)
	private String id;
	@Column(unique = true, length = 20)
	private String username;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	@ManyToMany(mappedBy ="users", fetch = FetchType.LAZY)
	private List<Role> roles= new ArrayList<>();
}
