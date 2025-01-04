package umc.workbook.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import umc.workbook.domain.Mission;
import umc.workbook.domain.enums.MissionStatus;
import umc.workbook.domain.mapping.MemberMission;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberMissionResponse {

    private Long id;
    private String missionSpec;
    private Integer reward;
    private String deadline;
    private MissionStatus status;

    public static MemberMissionResponse fromEntity(MemberMission memberMission) {
        Mission mission = memberMission.getMission();
        return new MemberMissionResponse(
                memberMission.getId(),
                mission.getMissionSpec(),
                mission.getReward(),
                mission.getDeadline().toString(),
                memberMission.getStatus()
        );
    }
}
