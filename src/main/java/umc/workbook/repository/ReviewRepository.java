package umc.workbook.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.workbook.domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 가게 ID를 기준으로 리뷰 조회
    Page<Review> findByStoreId(Long storeId, Pageable pageable);
}
