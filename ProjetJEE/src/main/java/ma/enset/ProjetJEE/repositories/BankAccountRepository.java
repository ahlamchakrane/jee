package ma.enset.ProjetJEE.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.enset.ProjetJEE.entities.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount,String>{
	@Query("select c from BankAccount c where c.id like :kw")
	List<BankAccount> searchBankAccounts(@Param("kw") String keyword);
}
