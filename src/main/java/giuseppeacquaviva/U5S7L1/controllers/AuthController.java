package giuseppeacquaviva.U5S7L1.controllers;

import giuseppeacquaviva.U5S7L1.entities.Dipendente;
import giuseppeacquaviva.U5S7L1.exceptions.BadRequestException;
import giuseppeacquaviva.U5S7L1.payloads.DIpendenteLoginDTO;
import giuseppeacquaviva.U5S7L1.payloads.DipendenteDTO;
import giuseppeacquaviva.U5S7L1.payloads.UserDTO;
import giuseppeacquaviva.U5S7L1.services.AuthService;
import giuseppeacquaviva.U5S7L1.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public DIpendenteLoginDTO login(@RequestBody DipendenteDTO body) {
        return new DIpendenteLoginDTO(authService.authUserAndGenerateToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public DipendenteDTO saveDipendente(@RequestBody @Validated DipendenteDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        Dipendente savedDipendente = dipendenteService.save(body);
        return new DipendenteDTO(savedDipendente.getUsername(), savedDipendente.getNome(),
                savedDipendente.getCognome(), savedDipendente.getEmail());
    }
}
