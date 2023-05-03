package cz.muni.fi.pa165.modulecore.data.repository;

import cz.muni.fi.pa165.modulecore.data.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT u FROM User u where u.memberOfBand is null")
    List<User> getAllUsersWithoutBand();

    @Query(value = "SELECT u FROM User u inner join Band band inner join Album album inner join Song song where song.id = :id and band.id = u.memberOfBand.id")
    List<User> getUsersFromBandBySongId(Long id);


}
