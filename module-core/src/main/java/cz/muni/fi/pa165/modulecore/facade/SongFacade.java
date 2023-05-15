package cz.muni.fi.pa165.modulecore.facade;

import cz.muni.fi.pa165.modulecore.api.SongDto;
import cz.muni.fi.pa165.modulecore.data.model.Song;
import cz.muni.fi.pa165.modulecore.mapper.CycleAvoidingMappingContext;
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
        return songMapper.mapToDto(songService.findById(id), new CycleAvoidingMappingContext());
    }

    public List<SongDto> getAll() {
        return songMapper.mapToList(songService.getAll(), new CycleAvoidingMappingContext());
    }

    public SongDto create(SongDto songDto) {
        Song song = songService.create(songMapper.mapFromDto(songDto, new CycleAvoidingMappingContext()));
        return songMapper.mapToDto(song, new CycleAvoidingMappingContext());
    }

    public SongDto update(Long id, SongDto songDto) {
        Song update = songService.update(id, songMapper.mapFromDto(songDto, new CycleAvoidingMappingContext()));
        return songMapper.mapToDto(update, new CycleAvoidingMappingContext());
    }

    public void delete(Long id) {
        songService.delete(id);
    }
}
