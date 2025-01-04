package umc.workbook.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc.workbook.domain.Review;
import umc.workbook.repository.ReviewRepository;
import umc.workbook.web.dto.ReviewListResponse;

@Service
@RequiredArgsConstructor
public class ReviewListService {

    private final ReviewRepository reviewRepository;

    // 리뷰 목록 조회
    public Page<ReviewListResponse> getReviews(Long storeId, Pageable pageable) {
        Page<Review> reviews;

        // 특정 가게의 리뷰를 조회하거나 전체 리뷰를 조회
        if(storeId != null) {
            reviews = reviewRepository.findByStoreId(storeId, pageable);
        } else {
            reviews = reviewRepository.findAll(pageable);
        }

        // 엔티티를 DTO로 변환
        return reviews.map(ReviewListResponse::fromEntity);
    }
}
