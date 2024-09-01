package org.squad9.vehiclerentalservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.dto.request.InsurancePolicyRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.InsurancePolicyResponseDTO;
import org.squad9.vehiclerentalservice.service.InsurancePolicyServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/apolices-seguro")
@RequiredArgsConstructor
public class InsurancePolicyController {
    private final InsurancePolicyServiceImpl insurancePolicyService;

    @GetMapping
    ResponseEntity<List<InsurancePolicyResponseDTO>> findAll() {
        return ResponseEntity.ok(insurancePolicyService.findAll());
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<InsurancePolicyResponseDTO> findById(@PathVariable UUID id) {
        InsurancePolicyResponseDTO response = insurancePolicyService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    ResponseEntity<InsurancePolicyResponseDTO> create(@RequestBody @Valid InsurancePolicyRequestDTO request) {
        InsurancePolicyResponseDTO response = insurancePolicyService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id){
        insurancePolicyService.remove(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<InsurancePolicyResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid InsurancePolicyRequestDTO request) {
        InsurancePolicyResponseDTO response = insurancePolicyService.update(id, request);
        return ResponseEntity.ok(response);
    }
}
