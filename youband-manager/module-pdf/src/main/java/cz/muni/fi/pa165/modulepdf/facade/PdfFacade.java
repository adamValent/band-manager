package cz.muni.fi.pa165.modulepdf.facade;


import cz.muni.fi.pa165.modulepdf.service.PdfService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PdfFacade {

    private final PdfService pdfService;

    @Autowired
    public PdfFacade(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    public void test(HttpServletResponse response) throws IOException {
        pdfService.export(response);
    }

    public void generatePdfBandAllMembers(Long idBand) {
        pdfService.generatePdfBandAllMembers(idBand);
    }

    public void generatePdfBandAllTours(Long idBand) {
        pdfService.generatePdfBandAllTours(idBand);
    }

    public void generatePdfBandAllAlbums(Long idBand) {
        pdfService.generatePdfBandAllAlbums(idBand);
    }

    public void generatePdfAlbumAllSong(Long idAlbum) {
        pdfService.generatePdfAlbumAllSong(idAlbum);
    }
}
