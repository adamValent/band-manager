package cz.muni.fi.pa165.modulepdf.data.model;

import jakarta.validation.constraints.NotNull;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(id, song.id) && Objects.equals(title, song.title) && Objects.equals(duration, song.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, duration);
    }

    @Override
    public String toString() {
        return "Song{" + "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration + '}';
    }
}
