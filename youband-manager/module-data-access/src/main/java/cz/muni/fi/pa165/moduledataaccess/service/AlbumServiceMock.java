package cz.muni.fi.pa165.moduledataaccess.service;

import cz.muni.fi.pa165.moduledataaccess.model.Album;
import cz.muni.fi.pa165.moduledataaccess.model.Song;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AlbumServiceMock implements AlbumService {
    private final Song song = new Song(1, "song 1", 2123);
    private final Map<Long, Album> data = new HashMap<>(Map.ofEntries(
            new AbstractMap.SimpleEntry<>(1L, new Album(1, "Al Al", List.of(song))),
            new AbstractMap.SimpleEntry<>(2L, new Album(2, "AI reckoning", List.of(song))),
            new AbstractMap.SimpleEntry<>(3L, new Album(3, "That one", List.of(song)))));

    @Override
    public List<Album> getAll() {
        return data.values().stream().toList();
    }

    @Override
    public Optional<Album> getById(long id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public void create(Album album) {
        Album newAlbum = new Album(data.size() + 1, album.name(), album.songs());
        data.put(newAlbum.id(), newAlbum);
    }

    @Override
    public void delete(long id) {
        data.remove(id);
    }

    @Override
    public void update(Album album) {
        data.replace(album.id(), album);
    }
}
