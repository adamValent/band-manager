package cz.muni.fi.pa165.moduleuser.data.repository;

import cz.muni.fi.pa165.moduleuser.data.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
