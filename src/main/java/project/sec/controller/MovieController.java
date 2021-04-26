package project.sec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import project.sec.service.MemberLoginValidator;
import project.sec.util.NaverMovieSearch;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final NaverMovieSearch naverMovieSearch;
    private final MemberLoginValidator memberloginValidator;

    @GetMapping("/movies/find")
    public String GfindMovie(Model model){
        model.addAttribute("findform", new FindForm());
        return "movies/find";
    }

    @PostMapping("/movies/find")
    public String PfindMovie(MemberLoginForm memberLoginForm,Model model, Errors errors){
        System.out.println(memberLoginForm.getEmail());
        memberloginValidator.validate(memberLoginForm,errors);
        model.addAttribute("findform", new FindForm());
        if(!errors.hasErrors()){
            return "movies/select";
        }
        return "members/login";
    }


    @PostMapping("/movies/movieList")
    public String PfindList(FindForm findForm, Model model){
        System.out.println(findForm.getWord());
        String result = naverMovieSearch.search(findForm.getWord());
        List<MovieForm> movielist = naverMovieSearch.fromJsontoMovies(result);
        model.addAttribute("movielist",movielist);
        return "movies/movieList";
    }


    @GetMapping("/movies/explore")
    public String Gexplore(Model model){
        List<String> list = new ArrayList<>();
        ExploreForm exploreForm = new ExploreForm();
        list = exploreForm.getGenre();
        model.addAttribute("exploreform", list);
        model.addAttribute("genre", "");
        return "movies/explore";
    }


    @PostMapping("/movies/exploreList")
    public String Pexplore(String genre,Model model){
        System.out.println(genre);
        System.out.println(1111);
        String result = naverMovieSearch.search(genre);
        List<MovieForm> movielist = naverMovieSearch.fromJsontoMovies(result);
        model.addAttribute("movielist",movielist);
        return "movies/exploreList";
    }

}
