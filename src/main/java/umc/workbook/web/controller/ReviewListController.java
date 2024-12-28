package umc.workbook.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.workbook.service.ReviewService.ReviewListService;
import umc.workbook.service.ReviewService.ReviewService;
import umc.workbook.web.dto.ReviewListResponse;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewListController {

    private final ReviewListService reviewListService;

    // 리뷰 목록 조회 API
    @GetMapping
    public ResponseEntity<Page<ReviewListResponse>> getAllReviews(
            @RequestParam(value = "storeId", required = false) Long storeId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "createdAt") String sortBy,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction) {
        Pageable pageable = PageRequest.of(
                page, size, Sort.by(Sort.Direction.fromString(direction), sortBy)
        );
        Page<ReviewListResponse> reviews = reviewListService.getReviews(storeId, pageable);
        return ResponseEntity.ok(reviews);
    }
}
