package cz.muni.fi.pa165.modulepdf.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import cz.muni.fi.pa165.modulepdf.api.PdfType;
import cz.muni.fi.pa165.modulepdf.data.model.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.util.List;


@Service
public class PdfService {

    private final CoreService coreService;

    @Autowired
    public PdfService(CoreService coreService) {
        this.coreService = coreService;
    }

    public void generatePdfBandAllMembers(Long idBand, HttpServletResponse response) throws IOException {
        export(idBand, response, PdfType.ALL_MEMBERS_OF_BAND);
    }

    public void generatePdfBandAllTours(Long idBand, HttpServletResponse response) throws IOException {
        export(idBand, response, PdfType.ALL_MEMBERS_OF_BAND);
    }

    public void generatePdfToursAllTourDates(Long idTour, HttpServletResponse response) throws IOException {
        export(idTour, response, PdfType.ALL_TOUR_DATES_OF_TOUR);
    }

    public void generatePdfBandAllAlbums(Long idBand, HttpServletResponse response) throws IOException {
        export(idBand, response, PdfType.ALL_MEMBERS_OF_BAND);
    }

    public void generatePdfAlbumAllSong(Long idAlbum, HttpServletResponse response) throws IOException {
        export(idAlbum, response, PdfType.ALL_MEMBERS_OF_BAND);
    }

    public void export(Long id,
                       HttpServletResponse response,
                       PdfType pdfType) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.black);

        PdfPTable table = null;

        switch (pdfType) {
            case ALL_MEMBERS_OF_BAND-> {
                Paragraph p = new Paragraph("Band Members", font);
                p.setAlignment(Paragraph.ALIGN_CENTER);

                document.add(p);

                table = new PdfPTable(5);
                table.setWidthPercentage(100f);
                table.setWidths(new float[]{1.5f, 3.5f, 2.0f, 2.0f, 3f});
                table.setSpacingBefore(10);

                tableHeaderAllBandMembers(table);
                tableDataAllBandMembers(table, id);
            }
            case ALL_TOURS_OF_BAND -> {
                Paragraph p = new Paragraph("Tours", font);
                p.setAlignment(Paragraph.ALIGN_CENTER);

                document.add(p);

                table = new PdfPTable(2);
                table.setWidthPercentage(100f);
                table.setWidths(new float[]{3f, 3f});
                table.setSpacingBefore(10);

                tableHeaderAllBandTours(table);
                tableDataAllBandTours(table, id);
            }
            case ALL_ALBUMS_OF_BAND -> {
                Paragraph p = new Paragraph("Albums", font);
                p.setAlignment(Paragraph.ALIGN_CENTER);

                document.add(p);

                table = new PdfPTable(4);
                table.setWidthPercentage(100f);
                table.setWidths(new float[]{1.5f, 3.5f, 3.0f, 3.0f});
                table.setSpacingBefore(10);

                tableHeaderAllBandAlbums(table);
                tableDataAllBandAlbums(table, id);
            }
            case ALL_SONG_OF_ALBUM-> {
                Paragraph p = new Paragraph("Songs", font);
                p.setAlignment(Paragraph.ALIGN_CENTER);

                document.add(p);

                table = new PdfPTable(3);
                table.setWidthPercentage(100f);
                table.setWidths(new float[]{1.5f, 3.5f, 3.0f});
                table.setSpacingBefore(10);

                tableHeaderAllAlbumSongs(table);
                tableDataAllAlbumSongs(table, id);
            }
            case ALL_TOUR_DATES_OF_TOUR -> {
                Paragraph p = new Paragraph("Tour dates", font);
                p.setAlignment(Paragraph.ALIGN_CENTER);

                document.add(p);

                table = new PdfPTable(3);
                table.setWidthPercentage(100f);
                table.setWidths(new float[]{3.5f, 3.5f, 3.0f});
                table.setSpacingBefore(10);

                tableHeaderAllTourDates(table);
                tableDataAllTourDates(table, id);
            }
        }

        document.add(table);

        document.close();
    }

    private PdfPCell createCell() {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.pink);
        cell.setPadding(5);
        return cell;
    }

    private Font createFont() {
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
        return font;
    }


    private void tableHeaderAllBandMembers(PdfPTable table) {
        PdfPCell cell = createCell();
        Font font = createFont();

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("User type", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("First name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Second name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Email", font));
        table.addCell(cell);
    }

    private void tableDataAllBandMembers(PdfPTable table,
                                         Long idBand) {
        List<User> users = coreService.getBandMembers(idBand);

        for (User user : users) {
            table.addCell(String.valueOf(user.getId()));
            table.addCell(user.getUserType().toString());
            table.addCell(user.getFirstName());
            table.addCell(user.getLastName());
            table.addCell(user.getEmail());
        }
    }

    private void tableHeaderAllBandAlbums(PdfPTable table) {
        PdfPCell cell = createCell();
        Font font = createFont();

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Release date", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Genre", font));
        table.addCell(cell);
    }

    private void tableDataAllBandAlbums(PdfPTable table,
                                        Long idBand) {
        List<Album> albums = coreService.getBandAlbums(idBand);

        for (Album album : albums) {
            table.addCell(album.getId().toString());
            table.addCell(album.getName());
            table.addCell(album.getReleaseDate().toString());
            table.addCell(album.getGenre().toString());
        }


    }

    private void tableHeaderAllBandTours(PdfPTable table) {
        PdfPCell cell = createCell();
        Font font = createFont();

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);
    }

    private void tableDataAllBandTours(PdfPTable table,
                                       Long idBand) {
        List<Tour> tours = coreService.getBandTours(idBand);

        for (Tour tour : tours) {
            table.addCell(tour.getId().toString());
            table.addCell(tour.getName());
        }
    }

    private void tableHeaderAllTourDates(PdfPTable table) {
        PdfPCell cell = createCell();
        Font font = createFont();

        cell.setPhrase(new Phrase("City", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Venue", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);
    }

    private void tableDataAllTourDates(PdfPTable table,
                                       Long idTour) {
        List<TourDate> tourDates = coreService.getTourDatesOfTour(idTour);

        for (TourDate tourDate : tourDates) {
            table.addCell(tourDate.getCity());
            table.addCell(tourDate.getVenue());
            table.addCell(tourDate.getDate().toString());
        }
    }

    private void tableHeaderAllAlbumSongs(PdfPTable table) {
        PdfPCell cell = createCell();
        Font font = createFont();

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Title", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Duration", font));
        table.addCell(cell);
    }

    private void tableDataAllAlbumSongs(PdfPTable table,
                                        Long idAlbum) {
        List<Song> songs = coreService.getAlbumSongs(idAlbum);

        for (Song song : songs) {
            table.addCell(song.getId().toString());
            table.addCell(song.getTitle());
            table.addCell(song.getDuration().toString());
        }


    }

}
