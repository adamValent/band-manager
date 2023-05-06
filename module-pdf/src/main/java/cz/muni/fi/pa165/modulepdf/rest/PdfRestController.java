package cz.muni.fi.pa165.modulepdf.rest;

import cz.muni.fi.pa165.modulepdf.facade.PdfFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping(path = "pdf")
public class PdfRestController {

    private final PdfFacade pdfFacade;

    @Autowired
    public PdfRestController(PdfFacade pdfFacade) {
        this.pdfFacade = pdfFacade;
    }

    @Operation(summary = "Generate pdf file with all band members.")
    @ApiResponses
            ({
                    @ApiResponse(responseCode = "200", description = "Pdf returned."),
                    @ApiResponse(responseCode = "404", description = "Band with given ID does not exist.")
            })
    @GetMapping(path = "band/{id}/members")
    public ResponseEntity<Void> generatePdfBandAllMembers(@PathVariable(value = "id") Long idBand,
                                                          HttpServletResponse response) throws IOException {
        pdfFacade.generatePdfBandAllMembers(idBand, response);
        setResponseAttributesForPDf(response, idBand);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Generate pdf file with all band tours.")
    @ApiResponses
            ({
                    @ApiResponse(responseCode = "200", description = "Pdf returned."),
                    @ApiResponse(responseCode = "404", description = "Band with given ID does not exist.")
            })
    @GetMapping(path = "band/{id}/tours")
    public ResponseEntity<Void> generatePdfBandAllTours(@PathVariable(value = "id") Long idBand,
                                                        HttpServletResponse response) throws IOException {
        pdfFacade.generatePdfBandAllTours(idBand, response);
        setResponseAttributesForPDf(response, idBand);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Generate pdf file with all band albums.")
    @ApiResponses
            ({
                    @ApiResponse(responseCode = "200", description = "Pdf returned."),
                    @ApiResponse(responseCode = "404", description = "Band with given ID does not exist.")
            })
    @GetMapping(path = "band/{id}/albums")
    public ResponseEntity<Void> generatePdfBandAllAlbums(@PathVariable(value = "id") Long idBand,
                                                         HttpServletResponse response) throws IOException {
        pdfFacade.generatePdfBandAllAlbums(idBand, response);
        setResponseAttributesForPDf(response, idBand);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Generate pdf file with all band songs.")
    @ApiResponses
            ({
                    @ApiResponse(responseCode = "200", description = "Pdf returned."),
                    @ApiResponse(responseCode = "404", description = "Band with given ID does not exist.")
            })
    @GetMapping(path = "albums/{id}/songs")
    public ResponseEntity<Void> generatePdfAlbumAllSong(@PathVariable(value = "id") Long idAlbum,
                                                        HttpServletResponse response) throws IOException {
        pdfFacade.generatePdfAlbumAllSong(idAlbum, response);
        setResponseAttributesForPDf(response, idAlbum);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Generate pdf file with all tour dates.")
    @ApiResponses
            ({
                    @ApiResponse(responseCode = "200", description = "Pdf returned."),
                    @ApiResponse(responseCode = "404", description = "Tour with given ID does not exist.")
            })
    @GetMapping(path = "tours/{id}/tourDates")
    public ResponseEntity<Void> generatePdfTourAllTourDates(@PathVariable(value = "id") Long idTour,
                                                            HttpServletResponse response) throws IOException {
        pdfFacade.generatePdfTourAllTourDates(idTour, response);
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
