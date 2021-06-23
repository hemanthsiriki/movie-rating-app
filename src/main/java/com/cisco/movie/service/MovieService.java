package com.cisco.movie.service;

import com.cisco.movie.exception.MovieCollectionException;
import com.cisco.movie.model.Movie;

import javax.validation.ConstraintViolationException;
import java.util.List;

public interface MovieService {

    void createMovie(Movie movie) throws ConstraintViolationException, MovieCollectionException;

    List<Movie> getAllMovies();

    void deleteMovieById(String id) throws MovieCollectionException;

    void updateMovie(String id, Movie newMovie) throws ConstraintViolationException,MovieCollectionException;
}
