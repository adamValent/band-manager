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
    public void generatePdfBandAllMembers(@PathVariable(value = "id") Long idBand,
                                          HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_.pdf";
        response.setHeader(headerKey, headerValue);

        pdfFacade.test(response);
    }

    @GetMapping(path = "band/{id}/tours")
    public ResponseEntity<Void> generatePdfBandAllTours(@PathVariable(value = "id") Long idBand) {
        pdfFacade.generatePdfBandAllTours(idBand);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "band/{id}/albums")
    public ResponseEntity<Void> generatePdfBandAllAlbums(@PathVariable(value = "id") Long idBand) {
        pdfFacade.generatePdfBandAllAlbums(idBand);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "albums/{id}/songs")
    public ResponseEntity<Void> generatePdfAlbumAllSong(@PathVariable(value = "id") Long idAlbum) {
        pdfFacade.generatePdfAlbumAllSong(idAlbum);
        return ResponseEntity.ok().build();
    }



}
