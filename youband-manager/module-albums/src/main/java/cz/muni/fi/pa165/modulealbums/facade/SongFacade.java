package cz.muni.fi.pa165.modulealbums.facade;

import cz.muni.fi.pa165.modulealbums.api.SongDto;
import cz.muni.fi.pa165.modulealbums.mappers.SongMapper;
import cz.muni.fi.pa165.modulealbums.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongFacade {
    private final SongService songService;
    private final SongMapper songMapper;

    @Autowired
    public SongFacade(SongService songService, SongMapper songMapper) {
        this.songService = songService;
        this.songMapper = songMapper;
    }

    public SongDto findById(Long id) {return songMapper.mapToDto(songService.findById(id));}

    public List<SongDto> getAll() {return songMapper.mapToList(songService.getAll());}

    public SongDto createSong(SongDto songDto) {
        return songMapper.mapToDto(songService.createSong(songMapper.mapFromDto(songDto)));
    }

    public SongDto updateSong(Long id, SongDto songDto) {
        return songMapper.mapToDto(songService.updateSong(id, songMapper.mapFromDto(songDto)));
    }

    public void deleteSong(Long id) {
        songService.deleteSong(id);
    }
}
