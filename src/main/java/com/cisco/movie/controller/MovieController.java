package com.cisco.movie.controller;

import com.cisco.movie.exception.MovieCollectionException;
import com.cisco.movie.model.Movie;
import com.cisco.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping(method = RequestMethod.POST, value = "/movie")
    public ResponseEntity<String> createMove(@RequestBody Movie movie) {
        try {
            movieService.createMovie(movie);
            return new ResponseEntity("Successfully added movie "+movie.getTitle(), HttpStatus.OK);
        } catch(ConstraintViolationException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch(MovieCollectionException e)
        {
            return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/movie")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movieList = movieService.getAllMovies();

        return new ResponseEntity(movieList, movieList.size() > 0 ? HttpStatus.OK: HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method=RequestMethod.DELETE,value="/movie/{id}")
    public ResponseEntity deleteMovieById(@PathVariable("id") String id) {
        try {
            movieService.deleteMovieById(id);
            return new ResponseEntity("Successfully deleted movie with id "+id,HttpStatus.OK);
        } catch (MovieCollectionException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method=RequestMethod.PUT,value="/movie/{id}")
    public ResponseEntity updateById(@PathVariable("id") String id,@RequestBody Movie newMovie) {
        try {
            movieService.updateMovie(id,newMovie);
            return new ResponseEntity("Updated movie with id "+id+"",HttpStatus.OK);
        } catch(ConstraintViolationException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (MovieCollectionException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
