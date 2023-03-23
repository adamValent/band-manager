package cz.muni.fi.pa165.modulealbums.data.repository;

import cz.muni.fi.pa165.modulealbums.data.model.Album;
import cz.muni.fi.pa165.modulealbums.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class AlbumRepository {
    private final List<Album> albums = new CopyOnWriteArrayList<>();

    public Album findById(Long id) {
        return albums.stream()
                     .filter(album -> album.getId().equals(id))
                     .findFirst()
                     .orElseThrow(() -> new ResourceNotFoundException("Album was not found."));
    }

    public List<Album> getAll(){ return albums; }

    public Album createAlbum(Album newAlbum){
        newAlbum.setId(albums.get(albums.size()-1).getId() + 1);
        albums.add(newAlbum);

        return newAlbum;
    }

    public Album updateAlbum(Long id, Album updated){
        Album album = findById(id);
        album.setName(updated.getName());
        album.setGenre(updated.getGenre());
        album.setReleaseDate(updated.getReleaseDate());

        return album;
    }

    public void deleteAlbum(Long id){
        Album album = findById(id);
        albums.remove(album);
    }

}
