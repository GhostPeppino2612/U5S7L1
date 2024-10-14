package giuseppeacquaviva.U5S7L1.repositories;

import giuseppeacquaviva.U5S7L1.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, String> {
     public Optional<Dipendente> findByEmail(String email);
}
