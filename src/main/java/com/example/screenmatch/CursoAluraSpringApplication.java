package com.example.screenmatch;

import com.example.screenmatch.main.Main;
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
<<<<<<< Updated upstream
        Main main = new Main();
        main.showMenu();
=======
        var consumoApi = new ApiConsumption();
        var json = consumoApi.getData("https://www.omdbapi.com/?t=the+big+bang+theory&apikey=f4db69c5");
        System.out.println(json);

        DataConverter converter = new DataConverter();
        SeriesData seriesData = converter.getData(json, SeriesData.class);
        System.out.println(seriesData);
>>>>>>> Stashed changes
    }
}
