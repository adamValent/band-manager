package cz.muni.fi.pa165.moduleband.data.repository;

import cz.muni.fi.pa165.moduleband.data.enums.Style;
import cz.muni.fi.pa165.moduleband.data.model.Band;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import cz.muni.fi.pa165.moduleband.exceptions.*;

@Repository
public class BandRepository {

    private List<Band> bandsMockData = new CopyOnWriteArrayList<>();

    @PostConstruct
    private void init() {

        Band band1 = new Band(
                1L,
                "Band1",
                Style.COUNTRY,
                new Byte[0],
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                1L
        );

        Band band2 = new Band(
                2L,
                "Band2",
                Style.COUNTRY,
                new Byte[0],
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                1L
        );

        Band band3 = new Band(
                3L,
                "Band3",
                Style.COUNTRY,
                new Byte[0],
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                1L
        );

        Band band4 = new Band(
                4L,
                "Band4",
                Style.COUNTRY,
                new Byte[0],
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                1L
        );

        bandsMockData.add(band1);
        bandsMockData.add(band2);
        bandsMockData.add(band3);
        bandsMockData.add(band4);
    }

    public Band findById(Long id) {
        return bandsMockData.stream()
                .filter(band -> band.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Band was not found."));
    }

    public List<Band> findAll() {
        return bandsMockData;
    }

    public Band createBand(Band band) {
        band.setId(bandsMockData.get(bandsMockData.size() - 1).getId() + 1);
        bandsMockData.add(band);
        return band;
    }

    public Band updateBand(Long id, Band bandUpdated) {

        Band bandOld = findById(id);

        bandOld.setMembersIds(bandUpdated.getMembersIds());
        bandOld.setName(bandUpdated.getName());
        bandOld.setStyle(bandUpdated.getStyle());
        bandOld.setImage(bandUpdated.getImage());
        bandOld.setAlbumsIds(bandUpdated.getAlbumsIds());
        bandOld.setActivitiesIds(bandUpdated.getActivitiesIds());
        bandOld.setManagerId(bandUpdated.getManagerId());

        return bandOld;
    }

    public void deleteBand(Long id) {
        Band bandToDelete = findById(id);
        bandsMockData.remove(bandToDelete);
    }


}
