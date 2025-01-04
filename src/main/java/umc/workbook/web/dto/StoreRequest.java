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
public class StoreRequest {

    @NotBlank(message = "Store name is required")
    @Size(max = 50, message = "Store name cannot exceed 5- characters")
    private String name;

    @NotBlank(message = "Store address is required")
    @Size(max = 50, message = "Store address cannot exceed 50 characters")
    private String address;

    @NotNull(message = "Store score is required")
    @DecimalMin(value = "0.0", message = "Score must be at least 0.0")
    @DecimalMax(value = "5.0", message = "Score cannot exceed 5.0")
    private Float score;
}
