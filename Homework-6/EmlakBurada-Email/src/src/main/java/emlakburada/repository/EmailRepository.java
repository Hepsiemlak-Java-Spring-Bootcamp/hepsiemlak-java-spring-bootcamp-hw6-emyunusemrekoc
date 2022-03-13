package src.main.java.emlakburada.repository;

import emlakburada.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email,Integer> {


}
