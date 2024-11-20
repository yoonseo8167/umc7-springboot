package umc.workbook.repository.MemberMissionRepository;

import org.springframework.data.domain.Pageable;
import umc.workbook.domain.mapping.MemberMission;

import java.util.List;

public interface MemberMissionRepositoryCustom {
    List<MemberMission> findAllMissionsByMemberAndStatus(Long member_mission_id, String status, Pageable pageable);
}
