package ma.enset.ProjetJEE.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@DiscriminatorValue("SA") 
@Data @AllArgsConstructor @NoArgsConstructor
public class SavingAccount extends BankAccount {
	private double interestRate; //taux d'interet

}
