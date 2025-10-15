package com.example.screenmatch;

import com.example.screenmatch.model.SeriesData;
import com.example.screenmatch.service.ApiConsumption;
import com.example.screenmatch.service.DataConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursoAluraSpringApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CursoAluraSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var consumoApi = new ApiConsumption();
        var json = consumoApi.getData("https://www.omdbapi.com/?t=the+big+bang+theory&apikey=f4db69c5");
        System.out.println(json);

        DataConverter converter = new DataConverter();
        SeriesData seriesData = converter.getData(json, SeriesData.class);
        System.out.println(seriesData);
    }
}
