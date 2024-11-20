package umc.workbook.repository.MemberMissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import umc.workbook.domain.enums.MissionStatus;
import umc.workbook.domain.mapping.MemberMission;
import umc.workbook.domain.mapping.QMemberMission;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberMissionRepositoryImpl implements MemberMissionRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QMemberMission memberMission = QMemberMission.memberMission;

    @Override
    public List<MemberMission> findAllMissionsByMemberAndStatus(Long member_mission_id, String status, Pageable pageable) {
        // BooleanBuilder 를 사용한 동적 필터링
        BooleanBuilder predicate = new BooleanBuilder();
        // 조건: member_mission_id 가 존재하면 추가
        if(member_mission_id != null) {
            predicate.and(memberMission.id.eq(member_mission_id));
        }
        // 조건: status 가 존재하면 추가
        if(status != null && !status.isEmpty()) {
            predicate.and(memberMission.status.eq(Enum.valueOf(MissionStatus.class, status)));
        }

        // QueryDSL 로 쿼리 실행
        return queryFactory
                .select(memberMission)
                .from(memberMission)
                .where(predicate)
                .orderBy(memberMission.updatedAt.desc()) // 최신순 정렬
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
