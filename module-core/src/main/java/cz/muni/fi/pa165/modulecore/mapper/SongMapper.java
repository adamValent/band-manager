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
    SongDto mapToDto(Song song, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    Song mapFromDto(SongDto songDto, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    List<SongDto> mapToList(List<Song> songs, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
}
