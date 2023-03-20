package cz.muni.fi.pa165.moduledataaccess.service;

import cz.muni.fi.pa165.moduledataaccess.model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceMock implements UserService {
    private final Map<Long, User> data = new HashMap<>(Map.ofEntries(
            new AbstractMap.SimpleEntry<>(1L, new User(1, "Al Al")),
            new AbstractMap.SimpleEntry<>(2L, new User(2, "AI reckoning")),
            new AbstractMap.SimpleEntry<>(3L, new User(3, "That one"))));

    @Override
    public List<User> getAll() {
        return data.values().stream().toList();
    }

    @Override
    public Optional<User> getById(long id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public void create(User user) {
        User newUser = new User(data.size() + 1, user.username());
        data.put(newUser.id(), newUser);
    }

    @Override
    public void delete(long id) {
        data.remove(id);
    }

    @Override
    public void update(User user) {
        data.replace(user.id(), user);
    }
}
