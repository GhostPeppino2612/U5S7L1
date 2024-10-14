package giuseppeacquaviva.U5S7L1.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "viaggi")
@Getter
@Setter
@NoArgsConstructor
public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String destinazione;
    private LocalDate data;
    @Enumerated(EnumType.STRING)
    private Stato stato;

    public Viaggio(String destinazione, LocalDate data, Stato stato) {
        this.destinazione = destinazione;
        this.data = data;
        this.stato = stato;
    }
}
