package cz.muni.fi.pa165.modulecore.service;

import cz.muni.fi.pa165.modulecore.data.model.Band;
import cz.muni.fi.pa165.modulecore.data.model.User;
import cz.muni.fi.pa165.modulecore.data.repository.BandRepository;
import cz.muni.fi.pa165.modulecore.data.repository.UserRepository;
import cz.muni.fi.pa165.modulecore.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BandService {
    private final BandRepository bandRepository;
    private final UserRepository userRepository;

    @Autowired
    public BandService(BandRepository bandRepository, UserRepository userRepository) {
        this.bandRepository = bandRepository;
        this.userRepository = userRepository;
    }

    public Band findById(Long id) {
        return bandRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public List<Band> findAll() {
        return (List<Band>) bandRepository.findAll();
    }

    @Transactional
    public Band createBand(Band band) {
        if (band.getId() != null) {
            band.getManager().setManagerOfBand(band);
        }
        Band createdBand = bandRepository.save(band);
        band.getManager().setManagerOfBand(createdBand);
        userRepository.save(band.getManager());
        if (band.getMembers() != null) {
            for (User bandMember: band.getMembers()) {
                bandMember.setMemberOfBand(createdBand);
                userRepository.save(bandMember);
            }
        }
        return createdBand;
    }

    @Transactional
    public Band updateBand(Long id, Band band) {
        band.setId(id);
        return createBand(band);
    }

    public void deleteBand(Long id) {
        bandRepository.deleteById(id);
    }
}
