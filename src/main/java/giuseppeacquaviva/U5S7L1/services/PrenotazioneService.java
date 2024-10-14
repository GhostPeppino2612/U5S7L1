package giuseppeacquaviva.U5S7L1.services;

import giuseppeacquaviva.U5S7L1.entities.Dipendente;
import giuseppeacquaviva.U5S7L1.entities.Prenotazione;
import giuseppeacquaviva.U5S7L1.entities.Viaggio;
import giuseppeacquaviva.U5S7L1.exceptions.NotFoundException;
import giuseppeacquaviva.U5S7L1.payloads.PrenotazioneDTO;
import giuseppeacquaviva.U5S7L1.payloads.ViaggioDTO;
import giuseppeacquaviva.U5S7L1.repositories.DipendenteRepository;
import giuseppeacquaviva.U5S7L1.repositories.PrenotazioneRepository;
import giuseppeacquaviva.U5S7L1.repositories.ViaggioRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private ViaggioRepository viaggioRepository;

    public Page<Prenotazione> getPrenotazioni(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return prenotazioneRepository.findAll(pageable);
    }

    public Prenotazione findById(UUID id) {
        return prenotazioneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Prenotazione non trovata"));
    }
    public Prenotazione save(PrenotazioneDTO body) throws BadRequestException {
        Dipendente dipendente = dipendenteRepository.findById(body.dipendenteId())
                .orElseThrow(() -> new NotFoundException("Dipendente non trovato"));
        Viaggio viaggio = viaggioRepository.findById(body.viaggioId())
                .orElseThrow(() -> new NotFoundException("Viaggio non trovato"));

        if (prenotazioneRepository.existsByDipendenteAndDataRichiesta(dipendente, body.dataRichiesta())) {
            throw new BadRequestException("Il dipendente ha già una prenotazione per questa data.");
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);
        prenotazione.setDataRichiesta(body.dataRichiesta());
        prenotazione.setPreferenze(body.preferenze());
        return prenotazioneRepository.save(prenotazione);
    }

    public Prenotazione findByIdAndUpdate(UUID id, PrenotazioneDTO body) {
        Prenotazione found = this.findById(id);

        // Recupera il Viaggio e il Dipendente dai rispettivi repository
        Viaggio viaggio = viaggioRepository.findById(body.viaggioId())
                .orElseThrow(() -> new NotFoundException("Viaggio non trovato con id: " + body.viaggioId()));

        Dipendente dipendente = dipendenteRepository.findById(body.dipendenteId())
                .orElseThrow(() -> new NotFoundException("Dipendente non trovato con id: " + body.dipendenteId()));

        // Imposta i campi dell'oggetto Prenotazione
        found.setPreferenze(body.preferenze());
        found.setDataRichiesta(body.dataRichiesta());
        found.setViaggio(viaggio);
        found.setDipendente(dipendente);

        return prenotazioneRepository.save(found);
    }


    public void deletePrenotazione(UUID id) {
        Prenotazione prenotazione = findById(id);
        prenotazioneRepository.delete(prenotazione);
    }
}
