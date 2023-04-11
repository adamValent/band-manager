package cz.muni.fi.pa165.modulepdf.rest;

import cz.muni.fi.pa165.modulepdf.facade.PdfFacade;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping(path = "api/pdf")
public class EmailRestController {

    private final PdfFacade pdfFacade;

    @Autowired
    public EmailRestController(PdfFacade pdfFacade) {
        this.pdfFacade = pdfFacade;
    }

    @GetMapping(path = "band/{id}/members")
    public ResponseEntity<Void> generatePdfBandAllMembers(@PathVariable(value = "id") Long idBand,
                                                          HttpServletResponse response) throws IOException {
        setResponseAttributesForPDf(response, idBand);

        pdfFacade.generatePdfBandAllMembers(idBand, response);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "band/{id}/tours")
    public ResponseEntity<Void> generatePdfBandAllTours(@PathVariable(value = "id") Long idBand,
                                                        HttpServletResponse response) throws IOException {
        setResponseAttributesForPDf(response, idBand);

        pdfFacade.generatePdfBandAllTours(idBand, response);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "band/{id}/albums")
    public ResponseEntity<Void> generatePdfBandAllAlbums(@PathVariable(value = "id") Long idBand,
                                                         HttpServletResponse response) throws IOException {
        setResponseAttributesForPDf(response, idBand);

        pdfFacade.generatePdfBandAllAlbums(idBand, response);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "albums/{id}/songs")
    public ResponseEntity<Void> generatePdfAlbumAllSong(@PathVariable(value = "id") Long idAlbum,
                                                        HttpServletResponse response) throws IOException {
        setResponseAttributesForPDf(response, idAlbum);

        pdfFacade.generatePdfAlbumAllSong(idAlbum, response);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "tours/{id}/tourDates")
    public ResponseEntity<Void> generatePdfTourAllTourDates(@PathVariable(value = "id") Long idTour,
                                                            HttpServletResponse response) throws IOException {
       setResponseAttributesForPDf(response, idTour);

        pdfFacade.generatePdfTourAllTourDates(idTour, response);
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
