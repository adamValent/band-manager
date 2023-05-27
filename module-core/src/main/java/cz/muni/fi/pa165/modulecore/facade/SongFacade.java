package cz.muni.fi.pa165.modulecore.facade;

import cz.muni.fi.pa165.librarymodel.api.SongDto;
import cz.muni.fi.pa165.modulecore.mapper.SongMapper;
import cz.muni.fi.pa165.modulecore.service.SongService;
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

    public SongDto findById(Long id) {
        return songMapper.mapToDto(songService.findById(id));
    }

    public List<SongDto> getAll() {
        return songMapper.mapToList(songService.getAll());
    }

    public SongDto create(SongDto songDto) {
        return songMapper.mapToDto(songService.create(songMapper.mapFromDto(songDto), songDto.getAlbumId()));
    }

    public SongDto update(Long id, SongDto songDto) {
        return songMapper.mapToDto(songService.update(id, songMapper.mapFromDto(songDto)));
    }

    public void delete(Long id) {
        songService.delete(id);
    }
}
