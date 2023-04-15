package cz.muni.fi.pa165.modulecore.data.repository;

import cz.muni.fi.pa165.modulecore.data.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Query("SELECT album FROM Album album WHERE album.id = :id")
    Optional<Album> findById(@Param("id") Long id);

    @Query("SELECT album FROM Album album")
    List<Album> getAll();

    @Modifying
    Album createAlbum(Album newAlbum);

    @Modifying
    Album updateAlbum(@Param("id") Long id, Album updated);

    @Modifying
    void deleteAlbum(@Param("id") Long id);
}
