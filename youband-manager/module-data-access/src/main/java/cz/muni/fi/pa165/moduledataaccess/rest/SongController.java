package cz.muni.fi.pa165.moduledataaccess.rest;

import cz.muni.fi.pa165.moduledataaccess.model.Song;
import cz.muni.fi.pa165.moduledataaccess.model.dto.SongDTO;
import cz.muni.fi.pa165.moduledataaccess.service.SongService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("song")
public class SongController {
    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("all")
    public ResponseEntity<List<Song>> getAll() {
        return ResponseEntity.ok(songService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Song> getById(@PathVariable long id) {
        return ResponseEntity.of(songService.getById(id));
    }

    @PostMapping
    public void create(@Valid @RequestBody SongDTO song) {
        songService.create(new Song(0, song.name(), song.duration()));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        songService.delete(id);
    }

    @PutMapping
    public void update(@Valid @RequestBody Song song) {
        songService.update(song);
    }
}
