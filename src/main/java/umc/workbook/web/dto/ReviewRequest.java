package umc.workbook.web.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {

    @NotBlank(message = "Review content is required")
    @Size(max = 500, message = "Review content cannot exceed 500 characters")
    private String content;

    @NotNull(message = "Review score is required")
    @DecimalMin(value = "0.0", message = "Score must be at least 0.0")
    @DecimalMax(value = "5.0", message = "Score cannot exceed 5.0")
    private Float score;
}
