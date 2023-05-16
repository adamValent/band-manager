package cz.muni.fi.pa165.modulecore.mapper;

import cz.muni.fi.pa165.modulecore.api.SongDto;
import cz.muni.fi.pa165.modulecore.data.model.Song;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SongMapper {
    @Mapping(target = "albumId", source = "song.album.id")
    SongDto mapToDto(Song song);

    @Mapping(target = "album", expression = "java(new Album(songDto.getAlbumId()))")
    Song mapFromDto(SongDto songDto);

    List<SongDto> mapToList(List<Song> songs);
}
