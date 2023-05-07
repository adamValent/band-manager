package cz.muni.fi.pa165.modulecore.rest;

import cz.muni.fi.pa165.modulecore.CustomConfiguration;
import cz.muni.fi.pa165.modulecore.api.BandDto;
import cz.muni.fi.pa165.modulecore.facade.BandFacade;
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
@RequestMapping(path = "bands")
public class BandRestController {
    private final BandFacade bandFacade;

    @Autowired
    public BandRestController(BandFacade bandFacade) {
        this.bandFacade = bandFacade;
    }

    @Operation(
            security = @SecurityRequirement(name = CustomConfiguration.SECURITY_SCHEME_NAME),
            summary = "Find band by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Band was found."),
            @ApiResponse(responseCode = "404", description = "Band with given ID does not exist."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content()),
            @ApiResponse(responseCode = "403", description = "Forbidden - access token does not have scope test_1 or test_2.", content = @Content())})
    @GetMapping(path = "{id}")
    public ResponseEntity<BandDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bandFacade.findById(id));
    }

    @Operation(
            security = @SecurityRequirement(name = CustomConfiguration.SECURITY_SCHEME_NAME),
            summary = "Get all bands")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "All bands returned."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content()),
            @ApiResponse(responseCode = "403", description = "Forbidden - access token does not have scope test_1.", content = @Content())})
    @GetMapping
    public ResponseEntity<List<BandDto>> findAll() {
        return ResponseEntity.ok(bandFacade.findAll());
    }

    @Operation(
            security = @SecurityRequirement(name = CustomConfiguration.SECURITY_SCHEME_NAME),
            summary = "Create band.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Band was created."),
            @ApiResponse(responseCode = "400", description = "Band given to be created cannot be validated."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content()),
            @ApiResponse(responseCode = "403", description = "Forbidden - access token does not have scope test_1.", content = @Content())})
    @PostMapping
    public ResponseEntity<BandDto> createBand(@Valid @RequestBody BandDto bandDto) {
        return ResponseEntity.ok(bandFacade.createBand(bandDto));
    }

    @Operation(
            security = @SecurityRequirement(name = CustomConfiguration.SECURITY_SCHEME_NAME),
            summary = "Update band by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Band was updated."),
            @ApiResponse(responseCode = "400", description = "Band given to be updated cannot be validated."),
            @ApiResponse(responseCode = "404", description = "Band with given ID does not exist."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content()),
            @ApiResponse(responseCode = "403", description = "Forbidden - access token does not have scope test_1.", content = @Content())})
    @PutMapping(path = "{id}")
    public ResponseEntity<BandDto> updateBand(@PathVariable("id") Long id, @Valid @RequestBody BandDto bandDto) {
        return ResponseEntity.ok(bandFacade.updateBand(id, bandDto));
    }

    @Operation(
            security = @SecurityRequirement(name = CustomConfiguration.SECURITY_SCHEME_NAME),
            summary = "Delete band by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Band was deleted."),
            @ApiResponse(responseCode = "404", description = "Band with given ID does not exist."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content()),
            @ApiResponse(responseCode = "403", description = "Forbidden - access token does not have scope test_1.", content = @Content())})
    @DeleteMapping(path = "{id}")
    public ResponseEntity<BandDto> deleteBand(@PathVariable("id") Long id) {
        bandFacade.deleteBand(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
