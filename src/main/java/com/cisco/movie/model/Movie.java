package com.cisco.movie.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Document(collection="movie")
public class Movie {

    @Id
    private String id;

    @NotNull(message = "Movie title cannot be null")
    private String title;

    @NotNull(message = "Movie rating cannot be null")
    private float rating;

    @NotNull(message = "Movie genre cannot be null")
    private String genre;
}
