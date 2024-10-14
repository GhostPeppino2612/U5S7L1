package giuseppeacquaviva.U5S7L1.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DipendenteDTO(
        @NotNull(message = "Lo username non può essere nullo.")
        @Size(min = 3, max = 20, message = "Lo username deve essere tra 3 e 20 caratteri.")
        String username,
        @NotNull(message = "Il nome non può essere nullo.")
        @Size(min = 2, max = 30, message = "Il nome deve essere tra 2 e 30 caratteri.")
        String nome,
        @NotNull(message = "Il cognome non può essere nullo.")
        @Size(min = 2, max = 30, message = "Il cognome deve essere tra 2 e 30 caratteri.")
        String cognome,

        @NotNull(message = "L'email non può essere nulla.")
        @Email(message = "L'email deve essere valida.")
        String email
) {
}
