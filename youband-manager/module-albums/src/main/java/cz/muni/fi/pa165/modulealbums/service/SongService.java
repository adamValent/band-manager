package cz.muni.fi.pa165.modulealbums.service;

import cz.muni.fi.pa165.modulealbums.data.model.Song;
import cz.muni.fi.pa165.modulealbums.data.repository.SongRepository;
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

    public Song findById(Long id) {return songRepository.findById(id);}

    public List<Song> getAll() {return songRepository.getAll();}

    public Song createSong(Song song) {return songRepository.CreateSong(song);}

    public Song updateSong(Long id, Song song) {return songRepository.updateSong(id, song);}

    public void deleteSong(Long id) {songRepository.deleteSong(id);}

}
