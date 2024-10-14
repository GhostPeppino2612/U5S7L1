package giuseppeacquaviva.U5S7L1.services;

import giuseppeacquaviva.U5S7L1.entities.Dipendente;
import giuseppeacquaviva.U5S7L1.exceptions.UnauthorizedException;
import giuseppeacquaviva.U5S7L1.payloads.DipendenteDTO;
import giuseppeacquaviva.U5S7L1.payloads.UserDTO;
import giuseppeacquaviva.U5S7L1.security.JWTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private JWTools jwTools;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authUserAndGenerateToken(DipendenteDTO body) {
        Dipendente dipendente = dipendenteService.findById(body.username());

        if(passwordEncoder.matches(body.username(), dipendente.getUsername())) {
            return jwTools.createToken(dipendente);
        } else {
            throw new UnauthorizedException("Credenziali non corrette");
        }
    }
}
