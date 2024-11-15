package umc.workbook.repository.StoreRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.workbook.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {
}
