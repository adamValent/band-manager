package cz.muni.fi.pa165.modulecore.service;

import com.google.common.collect.Lists;
import cz.muni.fi.pa165.modulecore.data.model.Album;
import cz.muni.fi.pa165.modulecore.data.model.Song;
import cz.muni.fi.pa165.modulecore.data.repository.AlbumRepository;
import cz.muni.fi.pa165.modulecore.data.repository.SongRepository;
import cz.muni.fi.pa165.modulecore.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SongService {
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    @Autowired
    public SongService(SongRepository songRepository,
                       AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    public Song findById(Long id) {
        return songRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public List<Song> getAll() {
        return Lists.newArrayList(songRepository.findAll());
    }

    @Transactional
    public Song create(Song song, long albumID) {
        Album album = albumRepository.findById(albumID).orElseThrow(ResourceNotFoundException::new);
        song.setAlbum(album);
        Song createdSong = songRepository.save(song);
        album.getSongs().add(createdSong);
        albumRepository.save(album);
        return createdSong;
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
