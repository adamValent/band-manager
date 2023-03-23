package cz.muni.fi.pa165.modulealbums.rest;

import cz.muni.fi.pa165.modulealbums.api.SongDto;
import cz.muni.fi.pa165.modulealbums.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.modulealbums.facade.SongFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/songs")
public class SongRestController {
    private final SongFacade songFacade;

    @Autowired
    public SongRestController(SongFacade songFacade) {
        this.songFacade = songFacade;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<SongDto> findById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(songFacade.findById(id));
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<SongDto>> getAll() {
        return ResponseEntity.ok(songFacade.getAll());
    }

    @PostMapping
    public ResponseEntity<SongDto> createSong(@Valid @RequestBody SongDto songDto) {
        return ResponseEntity.ok(songFacade.createSong(songDto));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SongDto> updateSong(@PathVariable("id") Long id, @Valid @RequestBody SongDto songDto) {
        try {
            return ResponseEntity.ok(songFacade.updateSong(id, songDto));
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<SongDto> deleteSong(@PathVariable("id") Long id) {
        try {
            songFacade.deleteSong(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}

