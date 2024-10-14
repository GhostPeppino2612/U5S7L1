package giuseppeacquaviva.U5S7L1.services;

import giuseppeacquaviva.U5S7L1.entities.Dipendente;
import giuseppeacquaviva.U5S7L1.entities.Viaggio;
import giuseppeacquaviva.U5S7L1.exceptions.NotFoundException;
import giuseppeacquaviva.U5S7L1.payloads.DipendenteDTO;
import giuseppeacquaviva.U5S7L1.payloads.ViaggioDTO;
import giuseppeacquaviva.U5S7L1.repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DipendenteService {
    @Autowired
    private DipendenteRepository dipendenteRepository;

    public Dipendente save(DipendenteDTO body) {
        Dipendente dipendente = new Dipendente();
        dipendente.setNome(body.nome());
        dipendente.setCognome(body.cognome());
        dipendente.setEmail(body.email());
        dipendente.setUsername(body.username());
        return dipendenteRepository.save(dipendente);
    }

    public Page<Dipendente> getDipendenti(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return dipendenteRepository.findAll(pageable);
    }

    public Dipendente findById(String id) {
        return dipendenteRepository.findById(id).orElseThrow(() -> new NotFoundException("Dipendente non trovato"));
    }

    public Dipendente findByEmail(String email) {
        return dipendenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("L'utente con email " + email + " non è stato trovato"));
    }

    public Dipendente findByIdAndUpdate(String id, DipendenteDTO body) {
        Dipendente found = this.findById(id);
        found.setUsername(body.username());
        found.setNome(body.nome());
        found.setCognome(body.cognome());
        return dipendenteRepository.save(found);
    }

    public void deleteDipendente(String id) {
        Dipendente dipendente = findById(id);
        dipendenteRepository.delete(dipendente);
    }
}
