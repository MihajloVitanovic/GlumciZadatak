package com.mvit.mihajlo.glumcizadatak.providers;



import com.mvit.mihajlo.glumcizadatak.models.Actor;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by androiddevelopment on 13.6.17..
 */

public class ActorProvider {

    public static List<String> getActorNames() {

        List<String> names = new ArrayList<>();
        names.add("Brad Pitt");
        names.add("Johnny Depp");
        names.add("Olivia Wilde");
        return names;
    }

    public static List<String> getActorMovies() {

        List<String> names = new ArrayList<>();
        names.add("Movie 1");
        names.add("Movie 2");
        names.add("Movie 3");
        return names;
    }

    public static Actor getActorById(int id) {

        switch (id){
            case 0:
                return new Actor(0, "bradpitt.png", "Brad", "Pitt", "Brad Pitt Summary", 4, new GregorianCalendar(2017, 6, 30).getTime() , "0");
            case 1:
                return new Actor(1, "johnnydepp.png", "Johnny", "Depp", "Johnny Depp Summary", 4, new GregorianCalendar(2016, 7, 10).getTime(), "1");
            case 2:
                return new Actor(2, "oliviawilde.png", "Olivia", "Wilde", "Olivia Wilde Summary", 4, new Date(2015, 5, 20), "2");
            default:
                return null;
        }
    }

}

