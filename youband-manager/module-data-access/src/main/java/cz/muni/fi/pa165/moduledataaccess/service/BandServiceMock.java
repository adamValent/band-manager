package cz.muni.fi.pa165.moduledataaccess.service;

import cz.muni.fi.pa165.moduledataaccess.model.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BandServiceMock implements BandService {
    private final List<User> users = List.of(new User(0, "John"));
    private final List<Activity> activities = List.of(new Activity(0, "Fun"));
    private final List<Album> albums = List.of(new Album(0, "Bestest",
            List.of(new Song(0, "This", 7678))));

    private final Map<Long, Band> data = new HashMap<>(Map.ofEntries(
            new AbstractMap.SimpleEntry<>(1L, new Band(1, "No 1", users, activities, albums)),
            new AbstractMap.SimpleEntry<>(2L, new Band(1, "No milion", users, activities, albums)),
            new AbstractMap.SimpleEntry<>(3L, new Band(1, "The good one", users, activities, albums))));

    @Override
    public List<Band> getAll() {
        return data.values().stream().toList();
    }

    @Override
    public Optional<Band> getById(long id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public void create(Band band) {
        Band newBand = new Band(
                data.size() + 1,
                band.name(),
                band.members(),
                band.activities(),
                band.albums()
        );
        data.put(newBand.id(), newBand);
    }

    @Override
    public void delete(long id) {
        data.remove(id);
    }

    @Override
    public void update(Band band) {
        data.replace(band.id(), band);
    }
}
