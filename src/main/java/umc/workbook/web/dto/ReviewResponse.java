package umc.workbook.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import umc.workbook.domain.Review;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {

    private Long id;
    private String content;
    private Float score;
    private String storeName;
    private String memberName;

    public static ReviewResponse fromEntity(Review review) {
        return new ReviewResponse(
                review.getId(),
                review.getContent(),
                review.getScore(),
                review.getStore() != null ? review.getStore().getName() : null,
                review.getMember() != null? review.getMember().getName() : null
        );
    }
}
