package cz.muni.fi.pa165.modulecore.data.repository;

import cz.muni.fi.pa165.modulecore.data.model.Tour;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends CrudRepository<Tour, Long> {

    @Query("SELECT t FROM Tour t INNER JOIN t.bandList band WHERE band.id = :bandId")
    List<Tour> findAllByBandId(Long bandId);

}
