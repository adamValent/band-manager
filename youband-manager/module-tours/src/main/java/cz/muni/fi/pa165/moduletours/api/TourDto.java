package cz.muni.fi.pa165.moduletours.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

public class TourDto {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private List<Long> bandList;
    @Valid
    @NotNull
    private List<TourDateDto> tourDates;

    public TourDto(Long id,
                   @NotNull String name,
                   @NotNull List<Long> bandList,
                   @NotNull List<TourDateDto> tourDates) {
        this.id = id;
        this.name = name;
        this.bandList = bandList;
        this.tourDates = tourDates;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @NotNull List<Long> getBandList() {
        return bandList;
    }

    public void setBandList(@NotNull List<Long> bandList) {
        this.bandList = bandList;
    }

    public @NotNull List<TourDateDto> getTourDates() {
        return tourDates;
    }

    public void setTourDates(@NotNull List<TourDateDto> tourDates) {
        this.tourDates = tourDates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourDto tourDto = (TourDto) o;
        return Objects.equals(id, tourDto.id) && name.equals(tourDto.name) && bandList.equals(tourDto.bandList) && tourDates.equals(tourDto.tourDates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bandList, tourDates);
    }

    @Override
    public String toString() {
        return "Tour{" + "id=" + id +
                ", tourName='" + name + '\'' +
                ", tourDates='" + tourDates.toString() + '\'' +
                ", bandList='" + bandList.toString() + "'}";
    }
}
