package com.example.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDate;

public class Episode {
    private Integer season;
    private String title;
    private Integer episodeNumber;
    private Double imdbRating;
    private LocalDate releasedDate;

    public Episode(Integer seasonNumber, EpisodeData episodeData) {
        this.season = seasonNumber;
        this.title = episodeData.title();
        this.episodeNumber = episodeData.episodeNumber();

        try {
            this.imdbRating = Double.valueOf(episodeData.imdbRating());
        } catch (NumberFormatException e) {
            this.imdbRating = 0.0;
        }

        try {
            this.releasedDate = LocalDate.parse(episodeData.releasedDate());
        } catch (Exception e) {
            this.releasedDate = null;
        }

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public Double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public LocalDate getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(LocalDate releasedDate) {
        this.releasedDate = releasedDate;
    }

    @Override
    public String toString() {
        return "season=" + season +
                ", title='" + title + '\'' +
                ", episodeNumber=" + episodeNumber +
                ", imdbRating=" + imdbRating +
                ", releasedDate=" + releasedDate;
    }
}
