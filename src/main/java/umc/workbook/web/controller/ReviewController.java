package umc.workbook.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.workbook.service.ReviewService.ReviewService;
import umc.workbook.web.dto.ReviewRequest;
import umc.workbook.web.dto.ReviewResponse;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{storeId}/reviews")
    public ResponseEntity<ReviewResponse> addReviewToStore(
            @PathVariable Long storeId,
            @RequestBody @Valid ReviewRequest reviewRequest) {
        ReviewResponse reviewResponse = reviewService.addReviewToStore(storeId, reviewRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewResponse);
    }
}
