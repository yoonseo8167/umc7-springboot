package umc.workbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.workbook.domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
