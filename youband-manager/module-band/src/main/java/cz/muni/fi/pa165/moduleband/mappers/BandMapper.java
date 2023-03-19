package cz.muni.fi.pa165.moduleband.mappers;

import cz.muni.fi.pa165.moduleband.api.BandDto;
import cz.muni.fi.pa165.moduleband.data.model.Band;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BandMapper {

    BandDto mapToDto(Band band);

    Band mapFromDto(BandDto bandDto);

    List<BandDto> mapToList(List<Band> bands);

}
