package ag.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ag.demo.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

}
