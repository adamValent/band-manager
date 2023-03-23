package cz.muni.fi.pa165.modulealbums.api;


import jakarta.validation.constraints.NotNull;

import java.time.Duration;

public class SongDto {
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private Duration duration;

    public SongDto() {}

    public SongDto(Long id, @NotNull String title, @NotNull Duration duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public @NotNull String getTitle() {
        return title;
    }

    public @NotNull Duration getDuration() {
        return duration;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(@NotNull String title) {
        this.title = title;
    }

    public void setDuration(@NotNull Duration duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "SongDto{" + "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration + '}';
    }
}

