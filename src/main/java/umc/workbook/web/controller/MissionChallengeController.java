package umc.workbook.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.workbook.service.MissionService.MissionChallengeService;
import umc.workbook.web.dto.MissionChallengeResponse;

@RestController
@RequestMapping("/mission")
@RequiredArgsConstructor
public class MissionChallengeController {

    private final MissionChallengeService missionChallengeService;

    @PostMapping("/{missionId}/challenge")
    public ResponseEntity<MissionChallengeResponse> challengeMission(
            @PathVariable Long missionId) {
        MissionChallengeResponse missionChallengeResponse = missionChallengeService.challengeMission(missionId);
        return ResponseEntity.status(HttpStatus.CREATED).body(missionChallengeResponse);
    }
}
