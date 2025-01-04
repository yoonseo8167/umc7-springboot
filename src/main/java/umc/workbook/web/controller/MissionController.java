package umc.workbook.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.workbook.domain.mapping.MemberMission;
import umc.workbook.service.MissionService.MissionChallengeService;
import umc.workbook.service.MissionService.MissionService;
import umc.workbook.web.dto.MemberMissionResponse;
import umc.workbook.web.dto.MissionRequest;
import umc.workbook.web.dto.MissionResponse;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;
    private final MissionChallengeService missionChallengeService;

    @PostMapping("/{storeId}/missions")
    public ResponseEntity<MissionResponse> addMissionToStore(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionRequest missionRequest) {
        MissionResponse missionResponse = missionService.addMissionToStore(storeId, missionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(missionResponse);
    }

    // week9 - 2. 특정 가게의 미션 목록 API
    @GetMapping("/store/{storeId}/list")
    public ResponseEntity<List<MissionResponse>> getMissionByStore(@PathVariable Long storeId) {
        List<MissionResponse> missions = missionService.getMissionsByStoreId(storeId);
        return ResponseEntity.ok(missions);
    }

    // week9 - 3. 내가 진행 중인 미션 목록 API
    @GetMapping("/member/{memberId}/progress")
    public ResponseEntity<List<MemberMissionResponse>> getMyProgressingMissions(@PathVariable Long memberId) {
        List<MemberMissionResponse> missions = missionChallengeService.getProgressingMissions(memberId);
        return ResponseEntity.ok(missions);
    }

    // week9 - 4. 진행 중인 미션을 완료로 바꾸기 API
    @PutMapping("/{memberMissionId}/complete")
    public ResponseEntity<MemberMissionResponse> completeMission(@PathVariable Long memberMissionId) {
        MemberMissionResponse completedMission = missionChallengeService.completeMission(memberMissionId);
        return ResponseEntity.ok(completedMission);
    }
}
