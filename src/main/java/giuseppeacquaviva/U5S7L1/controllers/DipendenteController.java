package giuseppeacquaviva.U5S7L1.controllers;

import giuseppeacquaviva.U5S7L1.entities.Dipendente;
import giuseppeacquaviva.U5S7L1.exceptions.BadRequestException;
import giuseppeacquaviva.U5S7L1.payloads.DipendenteDTO;
import giuseppeacquaviva.U5S7L1.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    @GetMapping("")
    public Page<Dipendente> getDipendenti(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "20") int size,
                                          @RequestParam(defaultValue = "username") String sortBy) {
        return dipendenteService.getDipendenti(page, size, sortBy);
    }

    @GetMapping("/{dipendenteId}")
    public Dipendente findById(@PathVariable String dipendenteId) {
        return dipendenteService.findById(dipendenteId);
    }


    @PutMapping("/{dipendenteId}")
    public Dipendente findByIdAndUpdate(@PathVariable String dipendenteId, @RequestBody DipendenteDTO body) {
        return dipendenteService.findByIdAndUpdate(dipendenteId, body);
    }

    @DeleteMapping("/{dipendenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDipendente(@PathVariable String dipendenteId) {
        dipendenteService.deleteDipendente(dipendenteId);
    }
}
