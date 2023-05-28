package cz.muni.fi.pa165.modulecore.data.repository;

import cz.muni.fi.pa165.modulecore.data.model.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends CrudRepository<Song, Long> {
}
