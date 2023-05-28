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

    public void generatePdfBandAllMembers(Long idBand, HttpServletResponse response, String token) throws IOException {
        pdfService.generatePdfBandAllMembers(idBand, response, token);
    }

    public void generatePdfBandAllTours(Long idBand, HttpServletResponse response, String token) throws IOException {
        pdfService.generatePdfBandAllTours(idBand, response, token);
    }

    public void generatePdfTourAllTourDates(Long idTour, HttpServletResponse response, String token) throws IOException {
        pdfService.generatePdfToursAllTourDates(idTour, response, token);
    }

    public void generatePdfBandAllAlbums(Long idBand, HttpServletResponse response, String token) throws IOException {
        pdfService.generatePdfBandAllAlbums(idBand, response, token);
    }

    public void generatePdfAlbumAllSong(Long idAlbum, HttpServletResponse response, String token) throws IOException {
        pdfService.generatePdfAlbumAllSong(idAlbum, response, token);
    }


}
