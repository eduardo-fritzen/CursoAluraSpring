package com.example.screenmatch.main;

import com.example.screenmatch.model.Episode;
import com.example.screenmatch.model.EpisodeData;
import com.example.screenmatch.model.SeasonData;
import com.example.screenmatch.model.SeriesData;
import com.example.screenmatch.service.ApiConsumption;
import com.example.screenmatch.service.DataConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private Scanner scanner = new Scanner(System.in);

    private ApiConsumption consumption = new ApiConsumption();
    private DataConverter converter = new DataConverter();

    private final String ADDRESS = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=f4db69c5";
    private final String SEASON = "&season=";
    public void showMenu() {
        System.out.println("Digite o nome da série para buscar: ");

        var seriesName =  scanner.nextLine();
        var json = consumption.getData(ADDRESS + seriesName.replace(" ", "+") + API_KEY);

        SeriesData seriesData = converter.getData(json, SeriesData.class);
        System.out.println(seriesData);

        List<SeasonData> seasons = new ArrayList<>();

        for (int i = 1; i <= seriesData.totalSeasons(); i++) {
            json = consumption.getData(ADDRESS + seriesName.replace(" ", "+") + SEASON + i + API_KEY);
            SeasonData seasonData = converter.getData(json, SeasonData.class);
            seasons.add(seasonData);
        }
        seasons.forEach(System.out::println);

        seasons.forEach(s -> s.episodes().forEach(e -> System.out.println(e.title())));

        List<String> nomes = Arrays.asList("Nome1", "Nome2");

        nomes.stream()
                .sorted()
                .limit(3)
                .filter(n -> n.startsWith("N"))
                .map(n -> n.toUpperCase())
                .forEach(System.out::println);

        List<EpisodeData> episodes = seasons.stream()
                .flatMap(t -> t.episodes().stream())
                .toList();

        System.out.println("\n TOP 5 Episodes:");
        episodes.stream()
                .filter(e -> !e.imdbRating().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(EpisodeData::imdbRating).reversed())
                .limit(5)
                .forEach(System.out::println);

        System.out.println("\n");
        List<Episode> episode = seasons.stream()
                .flatMap(s -> s.episodes().stream()
                .map(d -> new Episode(s.seasonNumber(), d))
                ).collect(Collectors.toList());

        episode.forEach(System.out::println);

        System.out.println("A partir de que ano você quer ver os episódios? ");
        var year = scanner.nextInt();
        scanner.nextLine();

        LocalDate dateSearch = LocalDate.of(year, 1 ,1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        episode.stream()
                .filter(e -> e.getReleasedDate() != null && e.getReleasedDate().isAfter(dateSearch))
                .forEach(e -> System.out.println(
                        "Season: " + e.getSeason() +
                                " Episode: " + e.getTitle() +
                                " Released Date: " + e.getReleasedDate().format(formatter))
                );
    }
}
