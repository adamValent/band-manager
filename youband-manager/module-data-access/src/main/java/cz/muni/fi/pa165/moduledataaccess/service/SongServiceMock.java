package cz.muni.fi.pa165.moduledataaccess.service;

import cz.muni.fi.pa165.moduledataaccess.model.Song;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SongServiceMock implements SongService {
    private final Map<Long, Song> data = new HashMap<>(Map.ofEntries(
            new AbstractMap.SimpleEntry<>(1L, new Song(1, "song 1", 2123)),
            new AbstractMap.SimpleEntry<>(2L, new Song(2, "hello world", 1)),
            new AbstractMap.SimpleEntry<>(3L, new Song(3, "temporal field", 69420))));

    @Override
    public List<Song> getAll() {
        return data.values().stream().toList();
    }

    @Override
    public Optional<Song> getById(long id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public void create(Song song) {
        Song newSong = new Song(data.size() + 1, song.name(), song.duration());
        data.put(newSong.id(), newSong);
    }

    @Override
    public void delete(long id) {
        data.remove(id);
    }

    @Override
    public void update(Song song) {
        data.replace(song.id(), song);
    }
}
