package giuseppeacquaviva.U5S6L5.controllers;

import giuseppeacquaviva.U5S6L5.entities.Dipendente;
import giuseppeacquaviva.U5S6L5.exceptions.BadRequestException;
import giuseppeacquaviva.U5S6L5.payloads.DipendenteDTO;
import giuseppeacquaviva.U5S6L5.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    @GetMapping("")
    public Page<Dipendente> getDipendenti(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "20") int size,
                                          @RequestParam(defaultValue = "username") String sortBy) {
        return dipendenteService.getDipendenti(page, size, sortBy);
    }

    @GetMapping("/{dipendenteId}")
    public Dipendente findById(@PathVariable String dipendenteId) {
        return dipendenteService.findById(dipendenteId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public DipendenteDTO saveDipendente(@RequestBody @Validated DipendenteDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        Dipendente savedDipendente = dipendenteService.save(body);
        return new DipendenteDTO(savedDipendente.getUsername(), savedDipendente.getNome(),
                savedDipendente.getCognome(), savedDipendente.getEmail());
    }

    @PutMapping("/{dipendenteId}")
    public Dipendente findByIdAndUpdate(@PathVariable String dipendenteId, @RequestBody DipendenteDTO body) {
        return dipendenteService.findByIdAndUpdate(dipendenteId, body);
    }

    @DeleteMapping("/{dipendenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDipendente(@PathVariable String dipendenteId) {
        dipendenteService.deleteDipendente(dipendenteId);
    }
}
