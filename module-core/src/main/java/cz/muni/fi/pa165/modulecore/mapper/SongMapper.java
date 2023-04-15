package cz.muni.fi.pa165.modulecore.mapper;

import cz.muni.fi.pa165.modulecore.api.SongDto;
import cz.muni.fi.pa165.modulecore.data.model.Song;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SongMapper {
    SongDto mapToDto(Song song);

    Song mapFromDto(SongDto songDto);

    List<SongDto> mapToList(List<Song> songs);
}
