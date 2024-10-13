package giuseppeacquaviva.U5S6L5.repositories;

import giuseppeacquaviva.U5S6L5.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, String> {

}
