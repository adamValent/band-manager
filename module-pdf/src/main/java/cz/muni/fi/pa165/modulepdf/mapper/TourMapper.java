package cz.muni.fi.pa165.modulepdf.mapper;

import cz.muni.fi.pa165.modulepdf.api.TourDto;
import cz.muni.fi.pa165.modulepdf.data.model.Tour;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TourMapper {
    TourDto mapToDto(Tour tour);

    Tour mapFromDto(TourDto tourDto);
}
