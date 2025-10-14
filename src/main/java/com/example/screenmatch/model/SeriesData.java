package com.example.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeriesData(
        @JsonAlias("Title") String title,
        @JsonAlias({"TotalSeasons", "totalSeasons"}) Integer totalSeasons,
        @JsonAlias({"ImdbRating", "imdbRating"}) String imdbRating
) {
}
