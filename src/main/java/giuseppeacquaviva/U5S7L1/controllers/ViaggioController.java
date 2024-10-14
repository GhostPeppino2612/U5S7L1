package giuseppeacquaviva.U5S7L1.controllers;

import giuseppeacquaviva.U5S7L1.entities.Viaggio;
import giuseppeacquaviva.U5S7L1.exceptions.BadRequestException;
import giuseppeacquaviva.U5S7L1.payloads.ViaggioDTO;
import giuseppeacquaviva.U5S7L1.services.ViaggioService;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;

    @GetMapping("")
    public Page<Viaggio> getViaggi(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "20") int size,
                                   @RequestParam(defaultValue = "destinazione") String sortBy) {
        return viaggioService.getViaggi(page, size, sortBy);
    }

    @GetMapping("/{viaggioId}")
    public Viaggio findById(@PathVariable UUID viaggioId) {
        return viaggioService.findById(viaggioId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ViaggioDTO saveViaggio(@RequestBody @Validated ViaggioDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        Viaggio savedViaggio = viaggioService.save(body);
        return new ViaggioDTO(savedViaggio.getDestinazione(), savedViaggio.getData(), savedViaggio.getStato());
    }

    @PutMapping("/{viaggioId}")
    public Viaggio updateStatoViaggio(@PathVariable UUID viaggioId, @RequestBody ViaggioDTO body) {
        return viaggioService.findByIdAndUpdate(viaggioId, body);
    }

    @DeleteMapping("/{viaggioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteViaggio(@PathVariable UUID viaggioId) {
        viaggioService.deleteViaggio(viaggioId);
    }
}
