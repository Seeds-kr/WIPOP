package com.example.movie.Controller;

import com.example.movie.Entity.Member;
import com.example.movie.Entity.MovieInfoEntity;
import com.example.movie.Entity.Review;
import com.example.movie.Service.MovieInfoService;
import com.example.movie.Service.MovieRecommendationService;
import com.example.movie.Service.ReviewService;
import com.example.movie.dto.ReviewDto;
import com.example.movie.login.SessionConst;
import com.example.movie.repository.MovieInfoRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final MovieInfoService movieInfoService;
    private final MovieRecommendationService movieRecommendationService;

    @GetMapping("/{movieId}")
    public String getMovie(@PathVariable Long movieId, Model model, HttpServletRequest request){

        List<Review> reviews = reviewService.getReviewsByMovie(movieId);
        MovieInfoEntity movie = movieInfoService.findMovie(movieId);
        Member member = (Member) request.getSession().getAttribute(SessionConst.LOGIN_MEMBER);

        ReviewDto reviewDto = new ReviewDto(movieId);

        model.addAttribute("reviewDto", reviewDto);
        model.addAttribute("reviews", reviews);
        model.addAttribute("movie", movie);
        model.addAttribute("movieId", movieId);
        model.addAttribute("member", member);

        return "review";// 템플릿
    }

    @GetMapping("/{movieId}/add")
    public String ReviewForm(@RequestParam Long movieId, Model model){
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setMovieId(movieId);

        model.addAttribute("reviewDto", reviewDto);
        return "add_comment";
    }

    @PostMapping("/{movieId}/add")
    public String addReview(@PathVariable Long movieId, @ModelAttribute ReviewDto reviewDto, HttpServletRequest request){
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        List<MovieInfoEntity> recommendedMovies = movieRecommendationService.getRecommendedMovies(member);

        log.info("add" + member);
        reviewDto.setMemberId(member.getId());
        reviewService.addReview(reviewDto);

        return "redirect:/review/" + movieId;
    }

    @PostMapping("/{movieId}/{reviewId}")
    public String deleteReview(@PathVariable Long movieId, @PathVariable Long reviewId,
                               HttpServletRequest request, RedirectAttributes redirectAttributes){
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        reviewService.deleteReview(reviewId);
        return "redirect:/review/" + movieId;
    }
}