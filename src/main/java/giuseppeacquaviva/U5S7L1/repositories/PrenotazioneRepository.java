package giuseppeacquaviva.U5S7L1.repositories;

import giuseppeacquaviva.U5S7L1.entities.Dipendente;
import giuseppeacquaviva.U5S7L1.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {

    boolean existsByDipendenteAndDataRichiesta(Dipendente dipendente, LocalDate dataRichiesta);

    @Query("SELECT p FROM Prenotazione p WHERE p.dipendente.id = :dipendenteId AND p.dataRichiesta = :data")
    Optional<Prenotazione> findPrenotazioneByDipendenteAndData(UUID id, LocalDate data);
}
