package cz.muni.fi.pa165.modulecore.rest;

import cz.muni.fi.pa165.modulecore.ModuleCoreApplication;
import cz.muni.fi.pa165.modulecore.api.AlbumDto;
import cz.muni.fi.pa165.modulecore.facade.AlbumFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @Operation(
            security = @SecurityRequirement(name = ModuleCoreApplication.SECURITY_SCHEME_NAME),
            summary = "Find album by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Album was found."),
            @ApiResponse(responseCode = "404", description = "Album with given ID does not exist."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content())})
    @GetMapping(path = "{id}")
    public ResponseEntity<AlbumDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(albumFacade.findById(id));
    }

    @Operation(
            security = @SecurityRequirement(name = ModuleCoreApplication.SECURITY_SCHEME_NAME),
            summary = "Get all albums")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "All albums returned."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content())})
    @GetMapping
    public ResponseEntity<List<AlbumDto>> getAll() {
        return ResponseEntity.ok(albumFacade.getAll());
    }

    @Operation(
            security = @SecurityRequirement(name = ModuleCoreApplication.SECURITY_SCHEME_NAME),
            summary = "Create album.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Album was created."),
            @ApiResponse(responseCode = "400", description = "Album given to be created cannot be validated."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content())})
    @PostMapping
    public ResponseEntity<AlbumDto> createAlbum(@Valid @RequestBody AlbumDto albumDto) {
        return ResponseEntity.ok(albumFacade.createAlbum(albumDto));
    }

    @Operation(
            security = @SecurityRequirement(name = ModuleCoreApplication.SECURITY_SCHEME_NAME),
            summary = "Update album by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Album was updated."),
            @ApiResponse(responseCode = "400", description = "Album given to be updated cannot be validated."),
            @ApiResponse(responseCode = "404", description = "Album with given ID does not exist."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content())})
    @PutMapping(path = "{id}")
    public ResponseEntity<AlbumDto> updateAlbum(@PathVariable("id") Long id, @Valid @RequestBody AlbumDto albumDto) {
        return ResponseEntity.ok(albumFacade.updateAlbum(id, albumDto));
    }

    @Operation(
            security = @SecurityRequirement(name = ModuleCoreApplication.SECURITY_SCHEME_NAME),
            summary = "Delete album by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Album was deleted."),
            @ApiResponse(responseCode = "404", description = "Album with given ID does not exist."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content())})
    @DeleteMapping(path = "{id}")
    public ResponseEntity<AlbumDto> deleteAlbum(@PathVariable("id") Long id) {
        albumFacade.deleteAlbum(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            security = @SecurityRequirement(name = ModuleCoreApplication.SECURITY_SCHEME_NAME),
            summary = "Find all albums by band ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Found albums returned."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content())})
    @GetMapping(path = "allByBand/{id}")
    public ResponseEntity<List<AlbumDto>> findAllByAlbumId(@PathVariable("id") Long bandId) {
        return ResponseEntity.ok(albumFacade.findAllByBandId(bandId));
    }
}
