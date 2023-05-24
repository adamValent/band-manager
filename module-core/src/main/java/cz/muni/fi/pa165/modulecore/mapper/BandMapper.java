package cz.muni.fi.pa165.modulecore.mapper;

import cz.muni.fi.pa165.librarymodel.api.BandDto;
import cz.muni.fi.pa165.modulecore.data.model.Band;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BandMapper {
    BandDto mapToDto(Band band);

    Band mapFromDto(BandDto bandDto);

    List<BandDto> mapToList(List<Band> bands);
}
