package walid.abboud.tech.moviecatalogservice.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import walid.abboud.tech.moviecatalogservice.models.CatalogItem;
import walid.abboud.tech.moviecatalogservice.models.Movie;
import walid.abboud.tech.moviecatalogservice.models.Rating;
import walid.abboud.tech.moviecatalogservice.models.UserRating;


import java.lang.reflect.ParameterizedType;
import java.util.*;


import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webckientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        //version reactive
        //create a method as @bean and call it as needed ==> was creted on main class
        //autowired that method here






        //create a rest api
        //ici la creation a ete faite avent bean and autowired so pas besoin de l instancier
        //RestTemplate restTemplate=new RestTemplate();
        //call the rest api for each instance of movie a inclure dans boocle
       //Movie movie= restTemplate.getForObject("http://localhost:8082/movies/zab", Movie.class);



                //get all rated movies id
                UserRating ratings =restTemplate.getForObject("http://rating-data-service/ratingsdata/users/"+userId, UserRating.class
                );
                        //ParameterizedType <List<Rating>>



                /* ici ancienne version pour genere des list rating
                Arrays.asList(
                        //new Rating("1234",4),
                        //new Rating("5678",3)

                 */





//for each movie id call movie info services and get detail
                return  ratings.getUserRating().stream().map(rating ->{
                    //this line will be replaced by new builder
                    Movie movie= restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);

                            //bodytoMono== what ever instance u collect convert it to instance of movie
                            //on this workshop still use restTemplate
                            //haw to create a builder reactive
                           /* Movie movie=  webckientBuilder.build()
                                    .get()
                                    .uri("http://localhost:8082/movies/"+rating.getMovieId())
                                    .retrieve()
                                    .bodyToMono(Movie.class)
                                    .block();

                            */

                            //put them all together
                            return  new CatalogItem(movie.getMovieId(),"Description",rating.getRating());
                })
                        .collect(Collectors.toList());




//        return Collections.singletonList(
//                new CatalogItem("transformer","test",4)
//        );

    }
}
/* ************************hard coded version without eureka *********************************
@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webckientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        //version reactive
        //create a method as @bean and call it as needed ==> was creted on main class
        //autowired that method here






        //create a rest api
        //ici la creation a ete faite avent bean and autowired so pas besoin de l instancier
        //RestTemplate restTemplate=new RestTemplate();
        //call the rest api for each instance of movie a inclure dans boocle
       //Movie movie= restTemplate.getForObject("http://localhost:8082/movies/zab", Movie.class);



                //get all rated movies id
                UserRating ratings =restTemplate.getForObject("http://localhost:8083/ratingsdata/users/"+userId, UserRating.class
                );
                        //ParameterizedType <List<Rating>>



                /* ici ancienne version pour genere des list rating
                Arrays.asList(
                        //new Rating("1234",4),
                        //new Rating("5678",3)

                * a fermer comment /





//for each movie id call movie info services and get detail
                return  ratings.getUserRating().stream().map(rating ->{
                        //this line will be replaced by new builder
                        Movie movie= restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);

        //bodytoMono== what ever instance u collect convert it to instance of movie
        //on this workshop still use restTemplate
        //haw to create a builder reactive
                           /* Movie movie=  webckientBuilder.build()
                                    .get()
                                    .uri("http://localhost:8082/movies/"+rating.getMovieId())
                                    .retrieve()
                                    .bodyToMono(Movie.class)
                                    .block();

                           a fermer comment /

        //put them all together
        return  new CatalogItem(movie.getMovieId(),"Description",rating.getRating());
        })
        .collect(Collectors.toList());




//        return Collections.singletonList(
//                new CatalogItem("transformer","test",4)
//        );

        }
        }

 */