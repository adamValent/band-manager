package cz.muni.fi.pa165.moduledataaccess.service;

import cz.muni.fi.pa165.moduledataaccess.model.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivityService {
    List<Activity> getAll();

    Optional<Activity> getById(long id);

    void create(Activity activity);

    void delete(long id);

    void update(Activity activity);
}
