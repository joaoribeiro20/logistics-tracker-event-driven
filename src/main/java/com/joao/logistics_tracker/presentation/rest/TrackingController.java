package com.joao.logistics_tracker.presentation.rest;

import com.joao.logistics_tracker.application.dto.RegisterScanRequest;
import com.joao.logistics_tracker.application.dto.RegisterScanResponse;
import com.joao.logistics_tracker.application.usecase.RegisterScanUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/scans")
@Tag(name = "Tracking", description = "Operações de rastreamento de produtos")
public class TrackingController {

    private final RegisterScanUseCase registerScanUseCase;

    public TrackingController(RegisterScanUseCase registerScanUseCase) {
        this.registerScanUseCase = registerScanUseCase;
    }

    @PostMapping
    @Operation(summary = "Registrar um scan", description = "Registra um novo evento de rastreamento para um pedido")
    public ResponseEntity<RegisterScanResponse> registerScan(@RequestBody RegisterScanRequest request) {
        RegisterScanResponse response = registerScanUseCase.execute(request);
        return ResponseEntity.ok(response);
    }
}
