package resep1.deluxes.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import resep1.deluxes.domain.message;

public interface messageRepo extends JpaRepository<message, Long> {
}
