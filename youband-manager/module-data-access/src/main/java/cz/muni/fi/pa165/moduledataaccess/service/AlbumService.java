package cz.muni.fi.pa165.moduledataaccess.service;

import cz.muni.fi.pa165.moduledataaccess.model.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    List<Album> getAll();

    Optional<Album> getById(long id);

    void create(Album album);

    void delete(long id);

    void update(Album album);
}
