package cz.muni.fi.pa165.moduletours.api;

import cz.muni.fi.pa165.moduletours.data.model.TourDate;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TourDto {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private ArrayList<String> bandList;
    @NotNull
    private ArrayList<TourDate> tourDates;

    public TourDto() {}

    public TourDto(Long id,
                   @NotNull String name,
                   @NotNull ArrayList<String> bandList,
                   @NotNull ArrayList<TourDate> tourDates) {
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

    public @NotNull ArrayList<String> getBandList() {
        return bandList;
    }

    public void setBandList(@NotNull ArrayList<String> bandList) {
        this.bandList = bandList;
    }

    public @NotNull ArrayList<TourDate> getTourDates() {
        return tourDates;
    }

    public void setTourDates(@NotNull ArrayList<TourDate> tourDates) {
        this.tourDates = tourDates;
    }

    @Override
    public String toString() {
        return "Tour{" + "id=" + id +
                ", tourName='" + name + '\'' +
                ", tourDates='" + tourDates.toString() + '\'' +
                ", bandList='" + bandList.toString() + "'}";
    }
}
