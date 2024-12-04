package umc.workbook.service.StoreService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.workbook.domain.Region;
import umc.workbook.domain.Store;
import umc.workbook.repository.RegionRepository;
import umc.workbook.repository.StoreRepository.StoreRepository;
import umc.workbook.web.dto.StoreRequest;
import umc.workbook.web.dto.StoreResponse;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Transactional
    public StoreResponse addStoreToRegion(Long regionId, StoreRequest storeRequest) {
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new EntityNotFoundException("Region not found with ID: "+ regionId));

        Store store = Store.builder()
                .name(storeRequest.getName())
                .address(storeRequest.getAddress())
                .score(storeRequest.getScore())
                .region(region)
                .build();

        Store savedStore = storeRepository.save(store);
        return StoreResponse.fromEntity(savedStore);
    }
}
