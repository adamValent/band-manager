package cz.muni.fi.pa165.modulecore.rest;

import cz.muni.fi.pa165.modulecore.ModuleCoreApplication;
import cz.muni.fi.pa165.modulecore.api.TourDto;
import cz.muni.fi.pa165.modulecore.facade.TourFacade;
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
@RequestMapping(path = "tours")
public class TourRestController {
    private final TourFacade tourFacade;

    @Autowired
    public TourRestController(TourFacade tourFacade) {
        this.tourFacade = tourFacade;
    }

    @Operation(
            security = @SecurityRequirement(name = ModuleCoreApplication.SECURITY_SCHEME_NAME),
            summary = "Find tour by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tour was found."),
            @ApiResponse(responseCode = "404", description = "Tour with given ID does not exist."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content())})
    @GetMapping(path = "{id}")
    public ResponseEntity<TourDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(tourFacade.findById(id));
    }

    @Operation(
            security = @SecurityRequirement(name = ModuleCoreApplication.SECURITY_SCHEME_NAME),
            summary = "Get all tours")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "All tours returned."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content())})
    @GetMapping
    public ResponseEntity<List<TourDto>> getAll() {
        return ResponseEntity.ok(tourFacade.getAll());
    }

    @Operation(
            security = @SecurityRequirement(name = ModuleCoreApplication.SECURITY_SCHEME_NAME),
            summary = "Create tour.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tour was created."),
            @ApiResponse(responseCode = "400", description = "Tour given to be created cannot be validated."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content())})
    @PostMapping
    public ResponseEntity<TourDto> createTour(@Valid @RequestBody TourDto tourDto) {
        return ResponseEntity.ok(tourFacade.createTour(tourDto));
    }

    @Operation(
            security = @SecurityRequirement(name = ModuleCoreApplication.SECURITY_SCHEME_NAME),
            summary = "Update tour by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tour was updated."),
            @ApiResponse(responseCode = "400", description = "Tour given to be updated cannot be validated."),
            @ApiResponse(responseCode = "404", description = "Tour with given ID does not exist."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content())})
    @PutMapping(path = "{id}")
    public ResponseEntity<TourDto> updateTour(@PathVariable("id") Long id, @Valid @RequestBody TourDto tourDto) {
        return ResponseEntity.ok(tourFacade.updateTour(id, tourDto));
    }

    @Operation(
            security = @SecurityRequirement(name = ModuleCoreApplication.SECURITY_SCHEME_NAME),
            summary = "Delete tour by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tour was deleted."),
            @ApiResponse(responseCode = "404", description = "Tour with given ID does not exist."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content())})
    @DeleteMapping(path = "{id}")
    public ResponseEntity<TourDto> deleteTour(@PathVariable("id") Long id) {
        tourFacade.deleteTour(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
