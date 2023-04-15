package cz.muni.fi.pa165.moduleemail.service;

import cz.muni.fi.pa165.moduleemail.exceptions.ResourceNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BandService {

    private final HashMap<Long, List<String>> bandMembersEmailsMock = new HashMap<>();
    private final HashMap<Long, String> bandManagerEmailMock = new HashMap<>();

    @PostConstruct
    private void initMock() {
        bandMembersEmailsMock.put(1L, new ArrayList<>());
        bandMembersEmailsMock.get(1L).add("youband.manager@gmail.com");

        bandManagerEmailMock.put(1L, "youband.manager@gmail.com");
    }

    public List<String> getMembersEmailFromBandByiD(Long idBand) {
        if (bandMembersEmailsMock.containsKey(idBand)) {
            return bandMembersEmailsMock.get(idBand);
        }
        throw new ResourceNotFoundException();
    }

    public String getManagerEmailFromBandByiD(Long idBand) {
        if (bandManagerEmailMock.containsKey(idBand)) {
            return bandManagerEmailMock.get(idBand);
        }
        throw new ResourceNotFoundException();
    }



}
