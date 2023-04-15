package cz.muni.fi.pa165.modulecore.rest;

import cz.muni.fi.pa165.modulecore.api.SongDto;
import cz.muni.fi.pa165.modulecore.facade.SongFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("songs")
public class SongRestController {
    private final SongFacade songFacade;

    @Autowired
    public SongRestController(SongFacade songFacade) {
        this.songFacade = songFacade;
    }

    @Operation(summary = "Find song by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Song was found."),
            @ApiResponse(responseCode = "404", description = "Song with given ID does not exist.")
    })
    @GetMapping(path = "{id}")
    public ResponseEntity<SongDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(songFacade.findById(id));
    }

    @Operation(summary = "Create song.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Song was created."),
            @ApiResponse(responseCode = "400", description = "Song given to be created cannot be validated."),
    })
    @PostMapping
    public ResponseEntity<SongDto> create(@Valid @RequestBody SongDto songDto) {
        return ResponseEntity.ok(songFacade.create(songDto));
    }

    @Operation(summary = "Update song by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Song was updated."),
            @ApiResponse(responseCode = "400", description = "Song given to be updated cannot be validated."),
            @ApiResponse(responseCode = "404", description = "Song with given ID does not exist.")
    })
    @PutMapping(path = "{id}")
    public ResponseEntity<SongDto> update(@PathVariable("id") Long id, @Valid @RequestBody SongDto songDto) {
        return ResponseEntity.ok(songFacade.update(id, songDto));
    }

    @Operation(summary = "Delete song by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Song was deleted."),
            @ApiResponse(responseCode = "404", description = "Song with given ID does not exist.")
    })
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        songFacade.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
