package cz.muni.fi.pa165.moduledataaccess.rest;

import cz.muni.fi.pa165.moduledataaccess.model.Band;
import cz.muni.fi.pa165.moduledataaccess.model.dto.BandDTO;
import cz.muni.fi.pa165.moduledataaccess.service.BandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("band")
public class BandController {
    private final BandService bandService;

    @Autowired
    public BandController(BandService bandService) {
        this.bandService = bandService;
    }

    @GetMapping("all")
    public ResponseEntity<List<Band>> getAll() {
        return ResponseEntity.ok(bandService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Band> getById(@PathVariable long id) {
        return ResponseEntity.of(bandService.getById(id));
    }

    @PostMapping
    public void create(@Valid @RequestBody BandDTO band) {
        bandService.create(new Band(
                0,
                band.name(),
                band.members(),
                band.activities(),
                band.albums()));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        bandService.delete(id);
    }

    @PutMapping
    public void update(@Valid @RequestBody Band band) {
        bandService.update(band);
    }
}
