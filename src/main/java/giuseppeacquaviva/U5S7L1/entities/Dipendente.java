package giuseppeacquaviva.U5S7L1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "dipendenti")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dipendente {
    @Id
    private String username;
    private String nome;
    private String cognome;
    private String email;

}
