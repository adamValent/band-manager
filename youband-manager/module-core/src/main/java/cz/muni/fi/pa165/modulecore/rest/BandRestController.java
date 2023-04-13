package cz.muni.fi.pa165.modulecore.rest;

import cz.muni.fi.pa165.modulecore.api.BandDto;
import cz.muni.fi.pa165.modulecore.exception.ResourceNotFoundException;
import cz.muni.fi.pa165.modulecore.facade.BandFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "bands")
public class BandRestController {
    private final BandFacade bandFacade;

    @Autowired
    public BandRestController(BandFacade bandFacade) {
        this.bandFacade = bandFacade;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<BandDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bandFacade.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<BandDto>> findAll() {
        return ResponseEntity.ok(bandFacade.findAll());
    }

    @PostMapping
    public ResponseEntity<BandDto> createBand(@Valid @RequestBody BandDto bandDto) {
        return ResponseEntity.ok(bandFacade.createBand(bandDto));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<BandDto> updateBand(@PathVariable("id") Long id, @Valid @RequestBody BandDto bandDto) {
        return ResponseEntity.ok(bandFacade.updateBand(id, bandDto));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<BandDto> deleteBand(@PathVariable("id") Long id) {
        bandFacade.deleteBand(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
