package project.sec.controller;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter @Setter
public  class ExploreForm {
    List<String> genre =new ArrayList<>();

    public List<String> getGenre() {
        genre.add("드라마");
        genre.add("판타지");
        genre.add("서부");
        genre.add("공포");
        return genre;
    }
}
