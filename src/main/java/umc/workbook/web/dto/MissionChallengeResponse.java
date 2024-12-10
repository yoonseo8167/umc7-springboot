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
public class MissionChallengeResponse {
    private Long memberMissionId;
    private String memberName;
    private String missionSpec;
    private MissionStatus status;

    public static MissionChallengeResponse fromEntity(MemberMission memberMission) {
        return new MissionChallengeResponse(
                memberMission.getId(),
                memberMission.getMember() != null ? memberMission.getMember().getName() : null,
                memberMission.getMission() != null ? memberMission.getMission().getMissionSpec() : null,
                memberMission.getStatus()
        );
    }
}
