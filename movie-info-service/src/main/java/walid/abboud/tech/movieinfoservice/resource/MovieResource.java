package walid.abboud.tech.movieinfoservice.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import walid.abboud.tech.movieinfoservice.models.Movie;
@RestController
@RequestMapping("/movies")
public class MovieResource {

    @RequestMapping("/{movieId}")
    public Movie getMovieinfo(@PathVariable("movieId") String movieId){

        return new Movie(movieId,"test name");

    }



}


