package cz.muni.fi.pa165.modulecore.api;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Bag;

import java.time.Duration;
import java.util.Objects;

@JsonIdentityInfo(
        scope = SongDto.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "title")
public class SongDto {
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private Duration duration;
    @NotNull
    private AlbumDto album;

    public SongDto() {
    }

    public SongDto(Long id, String title, Duration duration, AlbumDto album) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.album = album;
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

    public AlbumDto getAlbum() {
        return album;
    }

    public void setAlbum(AlbumDto album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "SongDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", album=" + album +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SongDto songDto = (SongDto) o;
        return Objects.equals(id, songDto.id)
                && Objects.equals(title, songDto.title)
                && Objects.equals(duration, songDto.duration)
                && Objects.equals(album, songDto.album);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, duration, album);
    }
}
