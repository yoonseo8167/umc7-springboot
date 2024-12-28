package umc.workbook.service.MissionService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Transient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.workbook.domain.Mission;
import umc.workbook.domain.Store;
import umc.workbook.repository.MissionRepository;
import umc.workbook.repository.StoreRepository.StoreRepository;
import umc.workbook.web.dto.MissionRequest;
import umc.workbook.web.dto.MissionResponse;

import java.util.List;

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

    // 특정 가게의 미션 목록 가져오기
    @Transactional(readOnly = true)
    public List<MissionResponse> getMissionsByStoreId(Long storeId) {
        // Store 존재 여부 확인
        storeRepository.findById(storeId)
                .orElseThrow(() -> new EntityNotFoundException("Store not found with ID: " + storeId));

        List<Mission> missions = missionRepository.findByStoreId(storeId);
        return missions.stream()
                .map(MissionResponse::fromEntity)
                .toList();
    }
}
