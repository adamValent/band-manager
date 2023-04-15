package cz.muni.fi.pa165.moduleuser.data.repository;

import cz.muni.fi.pa165.moduleuser.data.model.User;
import cz.muni.fi.pa165.moduleuser.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT user FROM User user WHERE user.id = :id")
    Optional<User> findById(@Param("id") Long id);

    @Query("SELECT user FROM User user WHERE user.email = :email")
    User findByEmail(@Param("email") String email);

    @Modifying
    User createUser(User newUser);

    @Modifying
    User updateUserEmail(@Param("id") Long id, @Param("email") String newMail);

    @Modifying
    public User updateUserPassword(@Param("id") Long id, @Param("password") String newPassword);

    @Modifying
    public void deleteUser(@Param("id") Long id);
}
