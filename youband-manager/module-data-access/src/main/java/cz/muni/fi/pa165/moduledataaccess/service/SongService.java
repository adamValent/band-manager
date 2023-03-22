package cz.muni.fi.pa165.moduledataaccess.service;

import cz.muni.fi.pa165.moduledataaccess.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {
    List<Song> getAll();

    Optional<Song> getById(long id);

    void create(Song song);

    void delete(long id);

    void update(Song song);
}
