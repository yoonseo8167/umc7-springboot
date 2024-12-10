package umc.workbook.web.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MissionRequest {

    @NotNull(message = "Reward is required")
    @Min(value = 0, message = "Reward must be least 0")
    private Integer reward;

    @NotNull(message = "Deadline is required")
    @Future(message = "Deadline must be future date")
    private LocalDate deadline;

    @NotBlank(message = "Mission specification is required")
    @Size(max = 255, message = "Mission specification cannot exceed 255 characters")
    private String missionSpec;
}
