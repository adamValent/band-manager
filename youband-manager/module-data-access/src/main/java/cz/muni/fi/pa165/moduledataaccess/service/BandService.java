package cz.muni.fi.pa165.moduledataaccess.service;

import cz.muni.fi.pa165.moduledataaccess.model.Band;

import java.util.List;
import java.util.Optional;

public interface BandService {
    List<Band> getAll();

    Optional<Band> getById(long id);

    void create(Band band);

    void delete(long id);

    void update(Band band);
}
