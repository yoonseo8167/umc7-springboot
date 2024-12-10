package umc.workbook.service.MissionService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.workbook.domain.Member;
import umc.workbook.domain.Mission;
import umc.workbook.domain.enums.MissionStatus;
import umc.workbook.domain.mapping.MemberMission;
import umc.workbook.repository.MemberMissionRepository.MemberMissionRepository;
import umc.workbook.repository.MemberRepository;
import umc.workbook.repository.MissionRepository;
import umc.workbook.web.dto.MissionChallengeResponse;

@Service
@RequiredArgsConstructor
public class MissionChallengeService {

    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository missionMissionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Transactional
    public MissionChallengeResponse challengeMission(Long missionId) {
        // Mission 조회
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new EntityNotFoundException("Mission not found with ID: "+missionId));

        // 하드 코딩된 사용자 선택
        Member member = memberRepository.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new EntityNotFoundException("No member available"));

        // MemberMission 생성
        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build();

        MemberMission savedMemberMission = memberMissionRepository.save(memberMission);
        return MissionChallengeResponse.fromEntity(savedMemberMission);
    }
}
