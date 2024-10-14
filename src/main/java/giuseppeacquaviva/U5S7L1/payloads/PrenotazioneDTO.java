package giuseppeacquaviva.U5S7L1.payloads;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record PrenotazioneDTO(
        @NotNull(message = "La data della richiesta non può essere nulla.")
        LocalDate dataRichiesta,
        String preferenze,
        @NotNull(message = "L'ID del dipendente non può essere nullo.")
        String dipendenteId,
        @NotNull(message = "L'ID del viaggio non può essere nullo.")
        UUID viaggioId
) {
}
