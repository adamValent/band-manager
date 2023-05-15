package cz.muni.fi.pa165.modulecore.mapper;

import cz.muni.fi.pa165.modulecore.api.AlbumDto;
import cz.muni.fi.pa165.modulecore.data.model.Album;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlbumMapper {
    AlbumDto mapToDto(Album album, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    Album mapFromDto(AlbumDto albumDto, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    List<AlbumDto> mapToList(List<Album> albums, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
}
