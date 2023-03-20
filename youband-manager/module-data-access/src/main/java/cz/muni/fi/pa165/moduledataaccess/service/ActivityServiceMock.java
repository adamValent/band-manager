package cz.muni.fi.pa165.moduledataaccess.service;

import cz.muni.fi.pa165.moduledataaccess.model.Activity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ActivityServiceMock implements ActivityService {
    private final Map<Long, Activity> data = new HashMap<>(Map.ofEntries(
            new AbstractMap.SimpleEntry<>(1L, new Activity(1, "Al Al")),
            new AbstractMap.SimpleEntry<>(2L, new Activity(2, "AI reckoning")),
            new AbstractMap.SimpleEntry<>(3L, new Activity(3, "That one"))));

    @Override
    public List<Activity> getAll() {
        return data.values().stream().toList();
    }

    @Override
    public Optional<Activity> getById(long id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public void create(Activity activity) {
        Activity newActivity = new Activity(data.size() + 1, activity.name());
        data.put(newActivity.id(), newActivity);
    }

    @Override
    public void delete(long id) {
        data.remove(id);
    }

    @Override
    public void update(Activity activity) {
        data.replace(activity.id(), activity);
    }
}
