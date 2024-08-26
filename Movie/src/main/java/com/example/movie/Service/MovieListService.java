package com.example.movie.Service;

import com.example.movie.Entity.MovieInfoEntity;
import com.example.movie.dto.MovieInfoDTO;
import com.example.movie.dto.PageRequestDTO;
import com.example.movie.dto.PageResultDTO;

import java.util.HashMap;
import java.util.Map;

public interface MovieListService {
    Long register(MovieInfoDTO movieInfoDTO);
    MovieInfoDTO getMovie(Long id);
    PageResultDTO<MovieInfoDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
    PageResultDTO<MovieInfoDTO, Object[]> getResult(PageRequestDTO requestDTO, String keyword);

    default Map<String, Object> dtoToEntity(MovieInfoDTO movieInfoDTO){
        //Map타입으로 반환
        Map<String, Object> entityMap= new HashMap<>();

        MovieInfoEntity movie = MovieInfoEntity.builder()
                .id(movieInfoDTO.getId())
                .movieName(movieInfoDTO.getMovieName())
                .rating(movieInfoDTO.getRating())
                .build(); //dto에서 빼낼 수 있는 정보는 빼서

        entityMap.put("movie",movie);  //hashMap으로 만듦.
        return entityMap;
    }

    default MovieInfoDTO entitiesToDTO(Long id,String mn,String mk,String genres,String mi, String url,String rDate,double rating){
        MovieInfoDTO movieInfoDTO = MovieInfoDTO.builder()
                .id(id)
                .movieName(mn)
                .movieNameK(mk)
                .genres(genres)
                .movieInfo(mi)
                .posterURL(url)
                .releaseDate(rDate)
                .rating(rating)
                .build();

        return movieInfoDTO;
    }


}
