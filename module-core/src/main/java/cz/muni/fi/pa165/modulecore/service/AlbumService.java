package cz.muni.fi.pa165.modulecore.service;

import com.google.common.collect.Lists;
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
        return albumRepository.findById(id).get();
    }

    public List<Album> getAll() {
        return Lists.newArrayList(albumRepository.findAll());
    }

    public Album createAlbum(Album album) {
        return albumRepository.save(album);
    }

    public Album updateAlbum(Long id, Album album) {
        return createAlbum(album);
    }

    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }
}
