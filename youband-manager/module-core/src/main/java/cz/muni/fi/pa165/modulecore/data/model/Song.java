package cz.muni.fi.pa165.modulecore.data.model;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Duration;
import java.util.Objects;

public class Song implements Serializable {
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private Duration duration;

    public Song() {
    }

    public Song(Long id, @NotNull String title, @NotNull Duration duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull String getTitle() {
        return title;
    }

    public void setTitle(@NotNull String title) {
        this.title = title;
    }

    public @NotNull Duration getDuration() {
        return duration;
    }

    public void setDuration(@NotNull Duration duration) {
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
