package org.squad9.vehiclerentalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final InsurancePolicyServiceImpl insurancePolicyService;

    @GetMapping
    ResponseEntity<List<InsurancePolicyModel>> findAll(){
        return ResponseEntity.ok(insurancePolicyService.findAll());
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<InsurancePolicyModel> findById(@PathVariable UUID id) {
        InsurancePolicyModel insurancePolicy = insurancePolicyService.findById(id);
        return ResponseEntity.ok(insurancePolicy);
    }

    @PostMapping
    ResponseEntity<InsurancePolicyModel> create(@RequestBody InsurancePolicyModel insurancePolicy) {
        InsurancePolicyModel newInsurancePolicy = insurancePolicyService.save(insurancePolicy);
        return ResponseEntity.status(HttpStatus.CREATED).body(newInsurancePolicy);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id){
        insurancePolicyService.remove(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<InsurancePolicyModel> update(@PathVariable UUID id, @RequestBody InsurancePolicyModel insurancePolicy) {
        InsurancePolicyModel insurancePolicyModel = insurancePolicyService.update(id, insurancePolicy);
        return ResponseEntity.ok(insurancePolicyModel);
    }
}
