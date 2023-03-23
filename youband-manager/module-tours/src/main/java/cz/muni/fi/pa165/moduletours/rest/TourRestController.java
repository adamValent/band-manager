package cz.muni.fi.pa165.moduletours.rest;

import cz.muni.fi.pa165.moduletours.api.TourDto;
import cz.muni.fi.pa165.moduletours.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.moduletours.facade.TourFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/tours")
public class TourRestController {
    private final TourFacade tourFacade;

    @Autowired
    public TourRestController(TourFacade tourFacade) {
        this.tourFacade = tourFacade;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TourDto> findById(@PathVariable("id") Long id) {
        try{
            return ResponseEntity.ok(tourFacade.findById(id));
        }catch (ResourceNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<TourDto>> getAll(){
        return ResponseEntity.ok(tourFacade.getAll());
    }

    @PostMapping
    public ResponseEntity<TourDto> createTour(@Valid @RequestBody TourDto tourDto){
        return ResponseEntity.ok(tourFacade.createTour(tourDto));
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<TourDto> updateTour(@PathVariable("id") Long id, @Valid @RequestBody TourDto tourDto){
        try{
            return ResponseEntity.ok(tourFacade.updateTour(id, tourDto));
        } catch (ResourceNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<TourDto> deleteTour(@PathVariable("id") Long id){
        try{
            tourFacade.deleteTour(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
