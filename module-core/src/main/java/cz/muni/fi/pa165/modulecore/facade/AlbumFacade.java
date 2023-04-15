package cz.muni.fi.pa165.modulecore.facade;

import cz.muni.fi.pa165.modulecore.api.AlbumDto;
import cz.muni.fi.pa165.modulecore.mapper.AlbumMapper;
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
        return albumMapper.mapToDto(albumService.findById(id));
    }

    public List<AlbumDto> getAll() {
        return albumMapper.mapToList(albumService.getAll());
    }

    public AlbumDto createAlbum(AlbumDto albumDto) {
        return albumMapper.mapToDto(albumService.createAlbum(albumMapper.mapFromDto(albumDto)));
    }

    public AlbumDto updateAlbum(Long id, AlbumDto albumDto) {
        return albumMapper.mapToDto(albumService.updateAlbum(id, albumMapper.mapFromDto(albumDto)));
    }

    public void deleteAlbum(Long id) {
        albumService.deleteAlbum(id);
    }
}
