package umc.workbook.service.ReviewService;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.workbook.domain.Review;
import umc.workbook.domain.enums.MissionStatus;
import umc.workbook.domain.mapping.QMemberMission;
import umc.workbook.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final JPAQueryFactory queryFactory;

    @Transactional
    public Review createReview(Long memberId, Long storeId, String body, Float score) {

        // QueryDSL 로 동적 검증 조건 작성 (예: 회원이 특정 미션을 완료했는지 확인)
        QMemberMission memberMission = QMemberMission.memberMission;
        boolean isMissionCompleted = queryFactory
                .selectFrom(memberMission)
                .where(
                        memberMission.member.id.eq(memberId),
                        memberMission.mission.store.id.eq(storeId),
                        memberMission.status.eq(MissionStatus.COMPLETED)
                )
                .fetchFirst() != null;

        if (!isMissionCompleted) {
            throw new IllegalStateException("미션이 완료되지 않아 리뷰를 작성할 수 없습니다.");
        }

        // Review 엔티티 생성 및 저장


        return reviewRepository.save(null);
    }
}
