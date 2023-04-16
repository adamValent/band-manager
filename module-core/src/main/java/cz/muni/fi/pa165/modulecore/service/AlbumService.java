package cz.muni.fi.pa165.modulecore.service;

import com.google.common.collect.Lists;
import cz.muni.fi.pa165.modulecore.data.model.Album;
import cz.muni.fi.pa165.modulecore.data.repository.AlbumRepository;
import cz.muni.fi.pa165.modulecore.exception.ResourceNotFoundException;
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
        return albumRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public List<Album> getAll() {
        return Lists.newArrayList(albumRepository.findAll());
    }

    public Album create(Album album) {
        return albumRepository.save(album);
    }

    public Album update(Long id, Album album) {
        if (!albumRepository.existsById(id))
            throw new ResourceNotFoundException("Album does not exist.");
        return albumRepository.save(album);
    }

    public void delete(Long id) { albumRepository.deleteById(id); }
}
