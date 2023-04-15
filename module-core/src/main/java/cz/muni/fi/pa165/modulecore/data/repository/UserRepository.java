package cz.muni.fi.pa165.modulecore.data.repository;

import cz.muni.fi.pa165.modulecore.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT user FROM User user WHERE user.id = :id")
    Optional<User> findById(@Param("id") Long id);

    @Query("SELECT user FROM User user")
    List<User> getAll();

    @Modifying
    User createUser(User newUser);

    @Modifying
    User updateUser(@Param("id") Long id, User updated);

    @Modifying
    void deleteUser(@Param("id") Long id);
}
