package cz.muni.fi.pa165.modulepdf.mapper;

import cz.muni.fi.pa165.librarymodel.api.SongDto;
import cz.muni.fi.pa165.modulepdf.data.model.Song;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SongMapper {
    SongDto mapToDto(Song song);

    Song mapFromDto(SongDto songDto);

    List<SongDto> mapToList(List<Song> songs);
}
