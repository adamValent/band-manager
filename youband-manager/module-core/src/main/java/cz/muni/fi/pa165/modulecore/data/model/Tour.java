package cz.muni.fi.pa165.modulecore.data.model;

import java.util.ArrayList;
import java.util.Objects;

public class Tour {
    private Long id;
    private String name;
    private ArrayList<Long> bandList;
    private ArrayList<TourDate> tourDates;

    public Tour() {
    }

    public Tour(Long id, String name, ArrayList<Long> bandList, ArrayList<TourDate> tourDates) {
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

    public ArrayList<Long> getBandList() {
        return bandList;
    }

    public void setBandList(ArrayList<Long> bandList) {
        this.bandList = bandList;
    }

    public ArrayList<TourDate> getTourDates() {
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
