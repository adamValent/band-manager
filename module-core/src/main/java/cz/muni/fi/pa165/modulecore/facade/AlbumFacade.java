package cz.muni.fi.pa165.modulecore.facade;

import cz.muni.fi.pa165.modulecore.api.AlbumDto;
import cz.muni.fi.pa165.modulecore.data.model.Album;
import cz.muni.fi.pa165.modulecore.mapper.AlbumMapper;
import cz.muni.fi.pa165.modulecore.mapper.CycleAvoidingMappingContext;
import cz.muni.fi.pa165.modulecore.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumFacade {
    private final AlbumService albumService;
    private final AlbumMapper albumMapper;

    @Autowired
    public AlbumFacade(AlbumService albumService, AlbumMapper albumMapper) {
        this.albumService = albumService;
        this.albumMapper = albumMapper;
    }

    public AlbumDto findById(Long id) {
        return albumMapper.mapToDto(albumService.findById(id), new CycleAvoidingMappingContext());
    }

    public List<AlbumDto> getAll() {
        return albumMapper.mapToList(albumService.getAll(), new CycleAvoidingMappingContext());
    }

    public AlbumDto createAlbum(AlbumDto albumDto) {
        Album album = albumService.create(albumMapper.mapFromDto(albumDto, new CycleAvoidingMappingContext()));
        return albumMapper.mapToDto(album, new CycleAvoidingMappingContext());
    }

    public AlbumDto updateAlbum(Long id, AlbumDto albumDto) {
        Album update = albumService.update(id, albumMapper.mapFromDto(albumDto, new CycleAvoidingMappingContext()));
        return albumMapper.mapToDto(update, new CycleAvoidingMappingContext());
    }

    public void deleteAlbum(Long id) {
        albumService.delete(id);
    }

    public List<AlbumDto> findAllByBandId(Long bandId) {
        return albumMapper.mapToList(albumService.findAllByBandId(bandId), new CycleAvoidingMappingContext());
    }
}
