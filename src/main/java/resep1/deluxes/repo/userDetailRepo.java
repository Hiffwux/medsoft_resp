package resep1.deluxes.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import resep1.deluxes.domain.user;

public interface userDetailRepo extends JpaRepository<user, String> {
}
