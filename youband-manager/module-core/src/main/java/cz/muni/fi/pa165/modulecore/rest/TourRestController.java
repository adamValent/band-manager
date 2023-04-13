package cz.muni.fi.pa165.modulecore.rest;

import cz.muni.fi.pa165.modulecore.api.TourDto;
import cz.muni.fi.pa165.modulecore.exception.ResourceNotFoundException;
import cz.muni.fi.pa165.modulecore.facade.TourFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "tours")
public class TourRestController {
    private final TourFacade tourFacade;

    @Autowired
    public TourRestController(TourFacade tourFacade) {
        this.tourFacade = tourFacade;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<TourDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(tourFacade.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TourDto>> getAll() {
        return ResponseEntity.ok(tourFacade.getAll());
    }

    @PostMapping
    public ResponseEntity<TourDto> createTour(@Valid @RequestBody TourDto tourDto) {
        return ResponseEntity.ok(tourFacade.createTour(tourDto));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<TourDto> updateTour(@PathVariable("id") Long id, @Valid @RequestBody TourDto tourDto) {
        return ResponseEntity.ok(tourFacade.updateTour(id, tourDto));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<TourDto> deleteTour(@PathVariable("id") Long id) {
        tourFacade.deleteTour(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
