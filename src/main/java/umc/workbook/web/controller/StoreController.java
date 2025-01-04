package umc.workbook.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.workbook.service.StoreService.StoreService;
import umc.workbook.web.dto.StoreRequest;
import umc.workbook.web.dto.StoreResponse;

@RestController
@RequestMapping("/regions")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/{regionId}/stores")
    public ResponseEntity<StoreResponse> addStoreToRegion(
            @PathVariable Long regionId,
            @RequestBody @Valid StoreRequest storeRequest) {
        StoreResponse storeResponse = storeService.addStoreToRegion(regionId, storeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(storeResponse);
    }
}
