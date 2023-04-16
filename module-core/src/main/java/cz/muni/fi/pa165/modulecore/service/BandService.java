package cz.muni.fi.pa165.modulecore.service;

import cz.muni.fi.pa165.modulecore.data.model.Band;
import cz.muni.fi.pa165.modulecore.data.repository.BandRepository;
import cz.muni.fi.pa165.modulecore.exception.ResourceNotFoundException;
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
        return bandRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public List<Band> findAll() {
        return (List<Band>) bandRepository.findAll();
    }

    public Band createBand(Band band) {
        return bandRepository.save(band);
    }

    public Band updateBand(Long id, Band band) {
        band.setId(id);
        return bandRepository.save(band);
    }

    public void deleteBand(Long id) {
        bandRepository.deleteById(id);
    }
}
