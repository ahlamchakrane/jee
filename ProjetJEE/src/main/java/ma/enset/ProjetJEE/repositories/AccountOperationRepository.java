package ma.enset.ProjetJEE.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ma.enset.ProjetJEE.entities.AccountOperation;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long>{
	List<AccountOperation> findByBankAccountId(Long accountId);
	Page<AccountOperation> findByBankAccountId(Long accountId, Pageable pageable);
	@Query("select a from AccountOperation a where a.description like :kw")
	List<AccountOperation> searchAccountOperations(@Param("kw") String keyword);
}
