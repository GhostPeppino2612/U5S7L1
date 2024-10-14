package giuseppeacquaviva.U5S7L1.payloads;

import giuseppeacquaviva.U5S7L1.entities.Stato;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ViaggioDTO(
    @NotNull(message = "La destinazione non può essere nulla.")
    @Size(min = 3, max = 50, message = "La destinazione deve essere tra 3 e 50 caratteri.")
    String destinazione,
    @NotNull(message = "La data del viaggio non può essere nulla.")
    LocalDate data,
    @NotNull(message = "Lo stato del viaggio non può essere nullo.")
    Stato stato
) {
}
