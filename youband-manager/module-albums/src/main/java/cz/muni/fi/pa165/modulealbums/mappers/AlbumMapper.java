package cz.muni.fi.pa165.modulealbums.mappers;

import cz.muni.fi.pa165.modulealbums.api.AlbumDto;
import cz.muni.fi.pa165.modulealbums.data.model.Album;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlbumMapper {
    AlbumDto mapToDto(Album album);
    Album mapFromDto(AlbumDto albumDto);

    List<AlbumDto> mapToList(List<Album> albums);
}
