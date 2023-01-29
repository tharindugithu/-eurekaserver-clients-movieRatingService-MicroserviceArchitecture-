package com.example.demo.resource;



import com.example.demo.model.CatalogItem;
import com.example.demo.model.Movie;
import com.example.demo.model.Rating;
import com.example.demo.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private  WebClient.Builder webclientBuilder;
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
        //RestTemplate   = new RestTemplate();

//        List<Rating> ratings = Arrays.asList(
//                new Rating("1234",4),
//                new Rating("1235",5)
//        );
        UserRating ratings = restTemplate.getForObject("http://moive-rating/ratingsdata/users/"+userId, UserRating.class);
        return  ratings.getUserRating().stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://moive-info-service/movies/"+ rating.getMovieId(), Movie.class);

//            Movie movie = webclientBuilder.build()
//                    .get()
//                    .uri("http://localhost:8081/movies/"+ rating.getMovieId())
//                    .retrieve()
//                    .bodyToMono(Movie.class)
//                    .block();
            return new CatalogItem(movie.getName(),"test",rating.getRating());
        }).collect(Collectors.toList());
    }
}
