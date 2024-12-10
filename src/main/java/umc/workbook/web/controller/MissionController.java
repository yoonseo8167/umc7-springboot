package umc.workbook.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.workbook.service.MissionService.MissionService;
import umc.workbook.web.dto.MissionRequest;
import umc.workbook.web.dto.MissionResponse;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/{storeId}/missions")
    public ResponseEntity<MissionResponse> addMissionToStore(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionRequest missionRequest) {
        MissionResponse missionResponse = missionService.addMissionToStore(storeId, missionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(missionResponse);
    }
}
