package cz.muni.fi.pa165.modulecore.rest;

import cz.muni.fi.pa165.modulecore.api.AlbumDto;
import cz.muni.fi.pa165.modulecore.exception.ResourceNotFoundException;
import cz.muni.fi.pa165.modulecore.facade.AlbumFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "albums")
public class AlbumRestController {
    private final AlbumFacade albumFacade;

    @Autowired
    public AlbumRestController(AlbumFacade albumFacade) {
        this.albumFacade = albumFacade;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<AlbumDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(albumFacade.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AlbumDto>> getAll() {
        return ResponseEntity.ok(albumFacade.getAll());
    }

    @PostMapping
    public ResponseEntity<AlbumDto> createAlbum(@Valid @RequestBody AlbumDto albumDto) {
        return ResponseEntity.ok(albumFacade.createAlbum(albumDto));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<AlbumDto> updateAlbum(@PathVariable("id") Long id, @Valid @RequestBody AlbumDto albumDto) {
        return ResponseEntity.ok(albumFacade.updateAlbum(id, albumDto));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<AlbumDto> deleteAlbum(@PathVariable("id") Long id) {
        albumFacade.deleteAlbum(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
