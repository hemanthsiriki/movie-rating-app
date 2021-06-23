package com.cisco.movie.service.impl;

import com.cisco.movie.exception.MovieCollectionException;
import com.cisco.movie.model.Movie;
import com.cisco.movie.repository.MovieRepository;
import com.cisco.movie.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {


    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void createMovie(Movie movie) throws ConstraintViolationException, MovieCollectionException {
        Optional<Movie> movieOptional = movieRepository.findByTitle(movie.getTitle());
        if(movieOptional.isPresent()) {
            log.info( "{} already exists!", movieOptional.get());
            throw new MovieCollectionException(MovieCollectionException.TitleAlreadyExists());
        } else {
            movieRepository.save(movie);
        }
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movieList = movieRepository.findAll();
        log.info( "Fetched {} movies!", movieList.size());
        return movieList;
    }

    @Override
    public void deleteMovieById(String id) throws MovieCollectionException {
        Optional<Movie> movieOptional = movieRepository.findById(id);

        if(movieOptional.isPresent()) {
            movieRepository.deleteById(id);

        }
        else {
            throw new MovieCollectionException(MovieCollectionException.NotFoundException(id));
        }
    }

    @Override
    public void updateMovie(String id, Movie newMovie) throws ConstraintViolationException, MovieCollectionException {

        Optional<Movie> movieWithId = movieRepository.findById(id);
        Optional<Movie> movieWithSameTitle = movieRepository.findByTitle(newMovie.getTitle());

        if(movieWithId.isPresent()) {

            if(movieWithSameTitle.isPresent() && !movieWithSameTitle.get().getId().equals(id)) {
                throw new MovieCollectionException(MovieCollectionException.TitleAlreadyExists());
            }

            Movie movieToUpdate = movieWithId.get();
            BeanUtils.copyProperties(newMovie,movieToUpdate);

            // To make sure that newMovie doesn't get added as a new document
            movieToUpdate.setId(id);
            movieRepository.save(movieToUpdate);
            log.info( "Successfully updated movie information for {}!", id);
        } else {
            throw new MovieCollectionException(MovieCollectionException.NotFoundException(id));
        }
    }
}
