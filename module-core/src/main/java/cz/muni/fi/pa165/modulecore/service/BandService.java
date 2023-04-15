package cz.muni.fi.pa165.modulecore.service;

import cz.muni.fi.pa165.modulecore.data.model.Band;
import cz.muni.fi.pa165.modulecore.data.repository.BandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BandService {
    private final BandRepository bandRepository;

    @Autowired
    public BandService(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    public Band findById(Long id) {
        return bandRepository.findById(id);
    }

    public List<Band> findAll() {
        return bandRepository.findAll();
    }

    public Band createBand(Band band) {
        return bandRepository.createBand(band);
    }

    public Band updateBand(Long id, Band band) {
        return bandRepository.updateBand(id, band);
    }

    public void deleteBand(Long id) {
        bandRepository.deleteBand(id);
    }
}
