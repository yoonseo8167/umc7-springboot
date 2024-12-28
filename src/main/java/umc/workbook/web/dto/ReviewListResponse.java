package umc.workbook.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.workbook.domain.Review;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewListResponse {

    private Long reviewId;
    private String content;
    private Float score;
    private String storeName;
    private String memberName;
    private String createdAt;

    // 엔티티 -> DTO 변환 메서드
    public static ReviewListResponse fromEntity(Review review) {
        return ReviewListResponse.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .score(review.getScore())
                .storeName(review.getStore() != null ? review.getStore().getName() : null)
                .memberName(review.getMember() != null ? review.getMember().getName() : null)
                .createdAt(review.getCreatedAt().toString())
                .build();
    }
}
