package cz.muni.fi.pa165.moduleband.data.model;

import cz.muni.fi.pa165.moduleband.data.enums.Style;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;


public class Band implements Serializable {

    private Long id;
    private String name;
    private Style style;
    private Byte[] image;
    private List<Long> membersIds;
    private List<Long> albumsIds;
    private List<Long> activitiesIds;
    private Long managerId;

    public Band() {
    }

    public Band(Long id, String name, Style style, Byte[] image, List<Long> membersIds, List<Long> albumsIds, List<Long> activitiesIds, Long managerId) {
        this.id = id;
        this.name = name;
        this.style = style;
        this.image = image;
        this.membersIds = membersIds;
        this.albumsIds = albumsIds;
        this.activitiesIds = activitiesIds;
        this.managerId = managerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getMembersIds() {
        return membersIds;
    }

    public void setMembersIds(List<Long> membersIds) {
        this.membersIds = membersIds;
    }

    public List<Long> getAlbumsIds() {
        return albumsIds;
    }

    public void setAlbumsIds(List<Long> albumsIds) {
        this.albumsIds = albumsIds;
    }

    public List<Long> getActivitiesIds() {
        return activitiesIds;
    }

    public void setActivitiesIds(List<Long> activitiesIds) {
        this.activitiesIds = activitiesIds;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Band{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", style=" + style +
                ", image=" + Arrays.toString(image) +
                ", membersIds=" + membersIds +
                ", albumsIds=" + albumsIds +
                ", activitiesIds=" + activitiesIds +
                ", managerId=" + managerId +
                '}';
    }
}
