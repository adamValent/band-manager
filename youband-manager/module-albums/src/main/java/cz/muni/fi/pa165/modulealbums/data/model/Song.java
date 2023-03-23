package cz.muni.fi.pa165.modulealbums.data.model;

import java.io.Serializable;
import java.time.Duration;
import java.util.Objects;

public class Song implements Serializable {
    private Long id;
    private String title;
    private Duration duration;

    public Song() {}

    public Song(Long id, String title, Duration duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Song song = (Song) obj;
        return id.equals(song.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Song{" + "id=" + id + "" +
                ", title='" + title + '\'' +
                ", duration=" + duration + '}';
    }
}
