package cz.muni.fi.pa165.modulepdf.rest;

import cz.muni.fi.pa165.modulepdf.CustomConfiguration;
import cz.muni.fi.pa165.modulepdf.facade.PdfFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = "pdf")
public class PdfRestController {
    private final PdfFacade pdfFacade;

    @Autowired
    public PdfRestController(PdfFacade pdfFacade) {
        this.pdfFacade = pdfFacade;
    }

    @Operation(
            security = @SecurityRequirement(name = CustomConfiguration.SECURITY_SCHEME_NAME),
            summary = "Generate pdf file with all band members.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pdf returned."),
            @ApiResponse(responseCode = "404", description = "Band with given ID does not exist."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content()),
            @ApiResponse(responseCode = "403", description = "Forbidden - access token does not have scope test_1.", content = @Content())})
    @GetMapping(path = "band/{id}/members")
    public ResponseEntity<Void> generatePdfBandAllMembers(@PathVariable(value = "id") Long idBand,
                                                          HttpServletResponse response,
                                                          @RequestHeader("Authorization") @Schema(hidden = true) String token) throws IOException {
        pdfFacade.generatePdfBandAllMembers(idBand, response, token);
        setResponseAttributesForPDf(response, idBand);
        return ResponseEntity.ok().build();
    }

    @Operation(
            security = @SecurityRequirement(name = CustomConfiguration.SECURITY_SCHEME_NAME),
            summary = "Generate pdf file with all band tours.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pdf returned."),
            @ApiResponse(responseCode = "404", description = "Band with given ID does not exist."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content()),
            @ApiResponse(responseCode = "403", description = "Forbidden - access token does not have scope test_1.", content = @Content())})
    @GetMapping(path = "band/{id}/tours")
    public ResponseEntity<Void> generatePdfBandAllTours(@PathVariable(value = "id") Long idBand,
                                                        HttpServletResponse response,
                                                        @RequestHeader("Authorization") @Schema(hidden = true) String token) throws IOException {
        pdfFacade.generatePdfBandAllTours(idBand, response, token);
        setResponseAttributesForPDf(response, idBand);
        return ResponseEntity.ok().build();
    }

    @Operation(
            security = @SecurityRequirement(name = CustomConfiguration.SECURITY_SCHEME_NAME),
            summary = "Generate pdf file with all band albums.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pdf returned."),
            @ApiResponse(responseCode = "404", description = "Band with given ID does not exist."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content()),
            @ApiResponse(responseCode = "403", description = "Forbidden - access token does not have scope test_1.", content = @Content())})
    @GetMapping(path = "band/{id}/albums")
    public ResponseEntity<Void> generatePdfBandAllAlbums(@PathVariable(value = "id") Long idBand,
                                                         HttpServletResponse response,
                                                         @RequestHeader("Authorization") @Schema(hidden = true) String token) throws IOException {
        pdfFacade.generatePdfBandAllAlbums(idBand, response, token);
        setResponseAttributesForPDf(response, idBand);
        return ResponseEntity.ok().build();
    }

    @Operation(
            security = @SecurityRequirement(name = CustomConfiguration.SECURITY_SCHEME_NAME),
            summary = "Generate pdf file with all band songs.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pdf returned."),
            @ApiResponse(responseCode = "404", description = "Band with given ID does not exist."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content()),
            @ApiResponse(responseCode = "403", description = "Forbidden - access token does not have scope test_1.", content = @Content())})
    @GetMapping(path = "albums/{id}/songs")
    public ResponseEntity<Void> generatePdfAlbumAllSong(@PathVariable(value = "id") Long idAlbum,
                                                        HttpServletResponse response,
                                                        @RequestHeader("Authorization") @Schema(hidden = true) String token) throws IOException {
        pdfFacade.generatePdfAlbumAllSong(idAlbum, response, token);
        setResponseAttributesForPDf(response, idAlbum);
        return ResponseEntity.ok().build();
    }

    @Operation(
            security = @SecurityRequirement(name = CustomConfiguration.SECURITY_SCHEME_NAME),
            summary = "Generate pdf file with all tour dates.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pdf returned."),
            @ApiResponse(responseCode = "404", description = "Tour with given ID does not exist."),
            @ApiResponse(responseCode = "401", description = "Unauthorized - access token not provided or valid", content = @Content()),
            @ApiResponse(responseCode = "403", description = "Forbidden - access token does not have scope test_1.", content = @Content())})
    @GetMapping(path = "tours/{id}/tourDates")
    public ResponseEntity<Void> generatePdfTourAllTourDates(@PathVariable(value = "id") Long idTour,
                                                            HttpServletResponse response,
                                                            @RequestHeader("Authorization") @Schema(hidden = true) String token) throws IOException {
        pdfFacade.generatePdfTourAllTourDates(idTour, response, token);
        setResponseAttributesForPDf(response, idTour);
        return ResponseEntity.ok().build();
    }

    private void setResponseAttributesForPDf(HttpServletResponse response,
                                             Long id) {
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=all_songs_of_" + id + ".pdf";
        response.setHeader(headerKey, headerValue);
    }

}
