package giuseppeacquaviva.U5S7L1.controllers;

import giuseppeacquaviva.U5S7L1.entities.Prenotazione;
import giuseppeacquaviva.U5S7L1.exceptions.BadRequestException;
import giuseppeacquaviva.U5S7L1.payloads.PrenotazioneDTO;
import giuseppeacquaviva.U5S7L1.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping("")
    public Page<Prenotazione> getPrenotazioni(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "20") int size,
                                              @RequestParam(defaultValue = "dataRichiesta") String sortBy) {
        return prenotazioneService.getPrenotazioni(page, size, sortBy);
    }

    @GetMapping("/{prenotazioneId}")
    public Prenotazione findById(@PathVariable UUID prenotazioneId) {
        return prenotazioneService.findById(prenotazioneId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public PrenotazioneDTO savePrenotazione(@RequestBody @Validated PrenotazioneDTO body, BindingResult validation) throws org.apache.coyote.BadRequestException {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        Prenotazione savedPrenotazione = prenotazioneService.save(body);
        return new PrenotazioneDTO(savedPrenotazione.getDataRichiesta(), savedPrenotazione.getPreferenze(),
                savedPrenotazione.getDipendente().getUsername(), savedPrenotazione.getViaggio().getId());
    }

    @DeleteMapping("/{prenotazioneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrenotazione(@PathVariable UUID prenotazioneId) {
        prenotazioneService.deletePrenotazione(prenotazioneId);
    }
}
