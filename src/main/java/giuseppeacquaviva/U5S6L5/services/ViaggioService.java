package giuseppeacquaviva.U5S6L5.services;

import giuseppeacquaviva.U5S6L5.entities.Stato;
import giuseppeacquaviva.U5S6L5.entities.Viaggio;
import giuseppeacquaviva.U5S6L5.exceptions.NotFoundException;
import giuseppeacquaviva.U5S6L5.payloads.ViaggioDTO;
import giuseppeacquaviva.U5S6L5.repositories.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ViaggioService {

    @Autowired
    private ViaggioRepository viaggioRepository;

    public Viaggio save(ViaggioDTO body) {
        Viaggio viaggio = new Viaggio();
        viaggio.setDestinazione(body.destinazione());
        viaggio.setData(body.data());
        viaggio.setStato(body.stato() != null ? body.stato() : Stato.IN_PROGRAMMA);
        return viaggioRepository.save(viaggio);
    }

    public Page<Viaggio> getViaggi(int page, int size, String sort) {
        return viaggioRepository.findAll(PageRequest.of(page, size, Sort.by(sort)));
    }

    public Viaggio findById(UUID id) {
        return viaggioRepository.findById(id).orElseThrow(() -> new NotFoundException("Risorsa non trovata"));
    }

    public Viaggio findByIdAndUpdate(UUID id, ViaggioDTO body) {
        Viaggio found = this.findById(id);
        found.setStato(body.stato());
        found.setData(body.data());
        found.setDestinazione(body.destinazione());
        return viaggioRepository.save(found);
    }

    public void deleteViaggio(UUID id) {
        Viaggio viaggio = findById(id);
        viaggioRepository.delete(viaggio);
    }
}
