package umc.workbook.repository.MemberMissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.workbook.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long>, MemberMissionRepositoryCustom {

}
