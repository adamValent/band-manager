package cz.muni.fi.pa165.modulecore.rest;

import cz.muni.fi.pa165.modulecore.api.BandDto;
import cz.muni.fi.pa165.modulecore.exception.ResourceNotFoundException;
import cz.muni.fi.pa165.modulecore.facade.BandFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Find band by ID.")
    @ApiResponses
            ({
                    @ApiResponse(responseCode = "200", description = "Band was found."),
                    @ApiResponse(responseCode = "404", description = "Band with given ID does not exist.")
            })
    @GetMapping(path = "{id}")
    public ResponseEntity<BandDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bandFacade.findById(id));
    }

    @Operation(summary = "Get all bands")
    @ApiResponses
            ({
                    @ApiResponse(responseCode = "200", description = "All bands returned."),
            })
    @GetMapping
    public ResponseEntity<List<BandDto>> findAll() {
        return ResponseEntity.ok(bandFacade.findAll());
    }

    @Operation(summary = "Create band.")
    @ApiResponses
            ({
                    @ApiResponse(responseCode = "200", description = "Band was created."),
                    @ApiResponse(responseCode = "400", description = "Band given to be created cannot be validated."),
            })
    @PostMapping
    public ResponseEntity<BandDto> createBand(@Valid @RequestBody BandDto bandDto) {
        return ResponseEntity.ok(bandFacade.createBand(bandDto));
    }

    @Operation(summary = "Update band by ID.")
    @ApiResponses
            ({
                    @ApiResponse(responseCode = "200", description = "Band was updated."),
                    @ApiResponse(responseCode = "400", description = "Band given to be updated cannot be validated."),
                    @ApiResponse(responseCode = "404", description = "Band with given ID does not exist.")
            })
    @PutMapping(path = "{id}")
    public ResponseEntity<BandDto> updateBand(@PathVariable("id") Long id, @Valid @RequestBody BandDto bandDto) {
        return ResponseEntity.ok(bandFacade.updateBand(id, bandDto));
    }

    @Operation(summary = "Delete band by ID.")
    @ApiResponses
            ({
                    @ApiResponse(responseCode = "200", description = "Band was deleted."),
                    @ApiResponse(responseCode = "404", description = "Band with given ID does not exist.")
            })
    @DeleteMapping(path = "{id}")
    public ResponseEntity<BandDto> deleteBand(@PathVariable("id") Long id) {
        bandFacade.deleteBand(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
