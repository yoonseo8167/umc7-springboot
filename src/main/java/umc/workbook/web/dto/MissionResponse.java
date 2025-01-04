package umc.workbook.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import umc.workbook.domain.Mission;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MissionResponse {

    private Long id;
    private Integer reward;
    private LocalDate deadline;
    private String missionSpec;
    private String storeName;

    public static MissionResponse fromEntity(Mission mission) {
        return new MissionResponse(
                mission.getId(),
                mission.getReward(),
                mission.getDeadline(),
                mission.getMissionSpec(),
                mission.getStore() != null? mission.getStore().getName() : null
        );
    }
}
