package cz.muni.fi.pa165.moduledataaccess.rest;

import cz.muni.fi.pa165.moduledataaccess.model.Album;
import cz.muni.fi.pa165.moduledataaccess.model.Song;
import cz.muni.fi.pa165.moduledataaccess.model.dto.AlbumDTO;
import cz.muni.fi.pa165.moduledataaccess.service.AlbumService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("album")
public class AlbumController {
    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("all")
    public ResponseEntity<List<Album>> getAll() {
        return ResponseEntity.ok(albumService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Album> getById(@PathVariable long id) {
        return ResponseEntity.of(albumService.getById(id));
    }

    @PostMapping
    public void create(@Valid @RequestBody AlbumDTO album) {
        albumService.create(new Album(0, album.name(), album.songs().stream()
                .map(songDTO -> new Song(0, songDTO.name(), songDTO.duration()))
                .toList()));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        albumService.delete(id);
    }

    @PutMapping
    public void update(@Valid @RequestBody Album album) {
        albumService.update(album);
    }
}
