package umc.workbook.service.MissionService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.workbook.domain.Mission;
import umc.workbook.domain.Store;
import umc.workbook.repository.MissionRepository;
import umc.workbook.repository.StoreRepository.StoreRepository;
import umc.workbook.web.dto.MissionRequest;
import umc.workbook.web.dto.MissionResponse;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Transactional
    public MissionResponse addMissionToStore(Long storeId, MissionRequest missionRequest) {
        // Store 조회
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new EntityNotFoundException("Store not found with ID: " + storeId));

        // Mission 생성
        Mission mission = Mission.builder()
                .reward(missionRequest.getReward())
                .deadline(missionRequest.getDeadline())
                .missionSpec(missionRequest.getMissionSpec())
                .store(store)
                .build();

        Mission savedMission = missionRepository.save(mission);
        return MissionResponse.fromEntity(savedMission);
    }
}
