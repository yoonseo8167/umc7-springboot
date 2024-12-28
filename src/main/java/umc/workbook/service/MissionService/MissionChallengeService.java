package umc.workbook.service.MissionService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.workbook.domain.Member;
import umc.workbook.domain.Mission;
import umc.workbook.domain.enums.MissionStatus;
import umc.workbook.domain.mapping.MemberMission;
import umc.workbook.repository.MemberMissionRepository.MemberMissionRepository;
import umc.workbook.repository.MemberRepository;
import umc.workbook.repository.MissionRepository;
import umc.workbook.web.dto.MemberMissionResponse;
import umc.workbook.web.dto.MissionChallengeResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionChallengeService {

    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
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

    // 내가 진행 중인 미션 목록 가져오기
    @Transactional(readOnly = true)
    public List<MemberMissionResponse> getProgressingMissions(Long memberId) {
        // Member 존재 여부 확인
        memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with ID: " + memberId));

        List<MemberMission> memberMissions = memberMissionRepository.findByMemberIdAndStatus(memberId, MissionStatus.CHALLENGING);
        return memberMissions.stream()
                .map(MemberMissionResponse::fromEntity)
                .toList();
    }

    // 진행 중인 미션을 완료로 바꾸기
    @Transactional
    public MemberMissionResponse completeMission(Long memberMissionId) {
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new EntityNotFoundException("Member mission not found with ID: " + memberMissionId));

        if(!memberMission.getStatus().equals(MissionStatus.CHALLENGING)) {
            throw new IllegalArgumentException("Mission is not in CHALLENGING state");
        }

        // 상태를 COMPLETE 로 변경
        memberMission.setStatus(MissionStatus.COMPLETED);
        MemberMission updatedMission = memberMissionRepository.save(memberMission);

        return MemberMissionResponse.fromEntity(updatedMission);
    }
}
