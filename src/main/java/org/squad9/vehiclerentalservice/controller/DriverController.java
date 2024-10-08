package org.squad9.vehiclerentalservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.dto.request.DriverRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.DriverResponseDTO;
import org.squad9.vehiclerentalservice.model.util.ApiResponse;
import org.squad9.vehiclerentalservice.service.DriverServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/motoristas")
@RequiredArgsConstructor
public class DriverController {
    private final DriverServiceImpl driverService;

    @GetMapping
    ResponseEntity<List<DriverResponseDTO>> findAll() {
        List<DriverResponseDTO> response = driverService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<DriverResponseDTO> findById(@PathVariable UUID id) {
        DriverResponseDTO response = driverService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/email/{email}")
    ResponseEntity<DriverResponseDTO> findByEmail(@PathVariable String email) {
        DriverResponseDTO response = driverService.findByEmail(email);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    ResponseEntity<DriverResponseDTO> create(@RequestBody @Valid DriverRequestDTO request) {
        DriverResponseDTO response = driverService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse> delete(@PathVariable UUID id) {

        driverService.remove(id);
        ApiResponse response = new ApiResponse("ID: " + id + " apagado com sucesso");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    ResponseEntity<DriverResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid DriverRequestDTO request) {
        DriverResponseDTO response = driverService.update(id, request);
        return ResponseEntity.ok(response);
    }
}
