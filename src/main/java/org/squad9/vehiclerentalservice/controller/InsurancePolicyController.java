package org.squad9.vehiclerentalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.model.InsurancePolicyModel;
import org.squad9.vehiclerentalservice.service.InsurancePolicyServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/apolices-seguro")
@RequiredArgsConstructor
public class InsurancePolicyController {
    private InsurancePolicyServiceImpl insurancePolicyService;

    @GetMapping
    public ResponseEntity<List<InsurancePolicyModel>> findAll(){
        return ResponseEntity.ok(insurancePolicyService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<InsurancePolicyModel> findById(@PathVariable UUID id) {
        InsurancePolicyModel insurancePolicy = insurancePolicyService.findById(id);
        return ResponseEntity.ok(insurancePolicy);
    }

    @PostMapping
    public ResponseEntity<InsurancePolicyModel> create(@RequestBody InsurancePolicyModel insurancePolicy) {
        InsurancePolicyModel newInsurancePolicy = insurancePolicyService.save(insurancePolicy);
        return ResponseEntity.ok(newInsurancePolicy);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        insurancePolicyService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
