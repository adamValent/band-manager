package cz.muni.fi.pa165.moduledataaccess.service;

import cz.muni.fi.pa165.moduledataaccess.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();

    Optional<User> getById(long id);

    void create(User user);

    void delete(long id);

    void update(User user);
}
