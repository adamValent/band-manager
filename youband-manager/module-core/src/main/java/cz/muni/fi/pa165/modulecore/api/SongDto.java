package cz.muni.fi.pa165.modulecore.api;

import jakarta.validation.constraints.NotNull;

import java.time.Duration;
import java.util.Objects;

public class SongDto {
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private Duration duration;

    public SongDto() {
    }

    public SongDto(Long id, String title, Duration duration) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "SongDto{" + "id=" + id + ", title='" + title + '\'' + ", duration=" + duration + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SongDto songDto = (SongDto) o;
        return Objects.equals(id, songDto.id) && title.equals(songDto.title) && duration.equals(songDto.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, duration);
    }
}
