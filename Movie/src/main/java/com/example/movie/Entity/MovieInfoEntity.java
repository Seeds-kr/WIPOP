package com.example.movie.Entity;

import com.example.movie.dto.MovieInfoDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie_info_table") //database에 해당 이름의 테이블 생성
public class MovieInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;
    @Column
    private String movieName;
    @Column
    private String movieNameK;
    @Column
    private String posterURL;
    @Column
    private String releaseDate;
    @Column(length = 9999)
    private String movieInfo;
    @Column
    private double rating;
    @Column
    private String genres;

    public static MovieInfoEntity toMovieInfoEntity(MovieInfoDTO movieinfoDTO){
        MovieInfoEntity movieInfoEntity = new MovieInfoEntity();
        movieInfoEntity.setId(movieinfoDTO.getId());
        movieInfoEntity.setMovieName(movieinfoDTO.getMovieName());
        movieInfoEntity.setMovieNameK(movieinfoDTO.getMovieNameK());
        movieInfoEntity.setPosterURL(movieinfoDTO.getPosterURL());
        movieInfoEntity.setReleaseDate(movieinfoDTO.getReleaseDate());
        movieInfoEntity.setMovieInfo(movieinfoDTO.getMovieInfo());
        movieInfoEntity.setRating(movieinfoDTO.getRating());
        movieInfoEntity.setGenres(movieinfoDTO.getGenres());
        return movieInfoEntity;
    }
}
