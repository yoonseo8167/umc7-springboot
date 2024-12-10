package umc.workbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.workbook.domain.Mission;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
}
