package cz.muni.fi.pa165.modulepdf.data.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tour {
    private Long id;
    private String name;
    private List<Band> bandList;
    private List<TourDate> tourDates;

    public Tour() {}

    public Tour(Long id, String name, List<Band> bandList, List<TourDate> tourDates) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Band> getBandList() {
        return bandList;
    }

    public void setBandList(List<Band> bandList) {
        this.bandList = bandList;
    }

    public List<TourDate> getTourDates() {
        return tourDates;
    }

    public void setTourDates(ArrayList<TourDate> tourDates) {
        this.tourDates = tourDates;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Tour tour)) {
            return false;
        }
        return this.getId().equals(tour.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Tour{" + "id=" + id +
                ", tourName='" + name + '\'' +
                ", tourDates='" + tourDates.toString() + '\'' +
                ", bandList='" + bandList.toString() + "'}";
    }
}
