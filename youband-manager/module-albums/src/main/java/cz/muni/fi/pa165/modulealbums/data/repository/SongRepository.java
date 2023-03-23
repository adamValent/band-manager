package cz.muni.fi.pa165.modulealbums.data.repository;

import cz.muni.fi.pa165.modulealbums.data.model.Song;
import cz.muni.fi.pa165.modulealbums.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class SongRepository {
    private final List<Song> songs = new CopyOnWriteArrayList<>();

    public Song findById(Long id) {
        return songs.stream()
                    .filter(song -> song.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("Song was not found."));
    }

    public List<Song> getAll(){
        return songs;
    }

    public Song CreateSong(Song newSong){
        newSong.setId(songs.get(songs.size() -1).getId() + 1);
        songs.add(newSong);

        return newSong;
    }

    public Song updateSong(Long id, Song updated){
        Song song = findById(id);
        song.setTitle(updated.getTitle());
        song.setDuration(updated.getDuration());

        return song;
    }

    public void deleteSong(Long id){
        Song song = findById(id);
        songs.remove(song);
    }
}
