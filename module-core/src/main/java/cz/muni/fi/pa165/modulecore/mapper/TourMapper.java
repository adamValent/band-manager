package cz.muni.fi.pa165.modulecore.mapper;

import cz.muni.fi.pa165.modulecore.api.TourDto;
import cz.muni.fi.pa165.modulecore.data.model.Tour;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TourMapper {
    TourDto mapToDto(Tour user);

    Tour mapFromDto(TourDto userDto);

    List<TourDto> mapToList(List<Tour> users);
}
