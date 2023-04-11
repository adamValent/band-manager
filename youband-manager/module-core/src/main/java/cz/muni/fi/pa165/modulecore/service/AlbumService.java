package cz.muni.fi.pa165.modulecore.service;

import cz.muni.fi.pa165.modulecore.data.model.Album;
import cz.muni.fi.pa165.modulecore.data.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public Album findById(Long id) {
        return albumRepository.findById(id);
    }

    public List<Album> getAll() {
        return albumRepository.getAll();
    }

    public Album createAlbum(Album album) {
        return albumRepository.createAlbum(album);
    }

    public Album updateAlbum(Long id, Album album) {
        return albumRepository.updateAlbum(id, album);
    }

    public void deleteAlbum(Long id) {
        albumRepository.deleteAlbum(id);
    }
}
