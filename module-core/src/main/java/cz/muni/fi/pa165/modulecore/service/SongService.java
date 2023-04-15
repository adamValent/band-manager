package cz.muni.fi.pa165.modulecore.service;

import com.google.common.collect.Lists;
import cz.muni.fi.pa165.modulecore.data.model.Song;
import cz.muni.fi.pa165.modulecore.data.repository.SongRepository;
import cz.muni.fi.pa165.modulecore.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    private final SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Song findById(Long id) {
        return songRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public List<Song> getAll() {
        return Lists.newArrayList(songRepository.findAll());
    }

    public Song create(Song song) {
        return songRepository.save(song);
    }

    public Song update(Long id, Song song) {
        if (!songRepository.existsById(id))
            throw new ResourceNotFoundException();
        return songRepository.save(song);
    }

    public void delete(Long id) {
        songRepository.deleteById(id);
    }
}
