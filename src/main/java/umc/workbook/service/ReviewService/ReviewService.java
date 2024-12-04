package umc.workbook.service.ReviewService;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.workbook.domain.Member;
import umc.workbook.domain.Review;
import umc.workbook.domain.Store;
import umc.workbook.domain.enums.MissionStatus;
import umc.workbook.domain.mapping.QMemberMission;
import umc.workbook.repository.MemberRepository;
import umc.workbook.repository.ReviewRepository;
import umc.workbook.repository.StoreRepository.StoreRepository;
import umc.workbook.web.dto.ReviewRequest;
import umc.workbook.web.dto.ReviewResponse;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final JPAQueryFactory queryFactory;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

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

    // 가게에 리뷰 추가
    public ReviewResponse addReviewToStore(Long storeId, ReviewRequest reviewRequest) {
        // Store 조회
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new EntityNotFoundException("Store not found wth ID: " + storeId));

        // 하드코딩 사용자 선택
        Member member = memberRepository.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new EntityNotFoundException("No member available"));

        // 리뷰 생성
        Review review = Review.builder()
                .content(reviewRequest.getContent())
                .score(reviewRequest.getScore())
                .store(store)
                .member(member)
                .build();

        Review savedReview = reviewRepository.save(review);
        return ReviewResponse.fromEntity(savedReview);
    }
}
